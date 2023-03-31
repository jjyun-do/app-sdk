package healthstack.kit.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

class PreferenceDataStore(val context: Context) {
    companion object {
        val PROFILE = stringPreferencesKey("profile")
    }

    val profile: Flow<String> = context.dataStore.data
        .map {
            it[PROFILE] ?: ""
        }

    suspend fun setProfile(profile: String) {
        context.dataStore.edit {
            it[PROFILE] = profile
        }
    }
}
