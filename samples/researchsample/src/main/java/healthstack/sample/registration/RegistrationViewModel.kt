package healthstack.sample.registration

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import healthstack.backend.integration.BackendFacadeHolder
import healthstack.backend.integration.exception.UserAlreadyExistsException
import healthstack.sample.registration.RegistrationState.Failed
import healthstack.sample.registration.RegistrationState.Init
import healthstack.sample.registration.RegistrationState.Loading
import healthstack.sample.registration.RegistrationState.Success
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _state = MutableStateFlow<RegistrationState>(Init)

    val state: StateFlow<RegistrationState> = _state

    fun registerUser(profile: Map<String, Any>) {
        _state.value = Loading
        auth.currentUser?.getIdToken(false)
            ?.addOnSuccessListener { result ->
                result.token?.let { idToken ->
                    viewModelScope.launch {
                        try {
                            BackendFacadeHolder.getInstance()
                                .registerUser(
                                    idToken,
                                    healthstack.backend.integration.registration.User(auth.uid!!, profile)
                                )
                            _state.value = Success
                            // TODO handle specific exception
                        } catch (e: UserAlreadyExistsException) {
                            _state.value = Success
                        } catch (e: Exception) {
                            Log.d(RegistrationViewModel::class.simpleName, "fail to register user")
                            _state.value = Failed
                            e.printStackTrace()
                        }
                    }
                }
            }?.addOnFailureListener {
                Log.d(RegistrationViewModel::class.simpleName, "fail to get id token")
                _state.value = Failed
            }
    }
}
