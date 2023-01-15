package healthstack.app.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.temporal.ChronoUnit.DAYS

private const val META_DATA_STORE_NAME = "metaDataStore"

private val Context.metaDataStore: DataStore<Preferences> by preferencesDataStore(
    name = META_DATA_STORE_NAME
)

class MetaDataStore(val context: Context) {
    private val metaDataStore = context.metaDataStore

    suspend fun readLatestSyncTime(healthDataTypeString: String): String {
        return metaDataStore.data.map {
            it[stringPreferencesKey(healthDataTypeString)] ?: Instant.now().truncatedTo(DAYS).toString()
        }.first()
    }

    suspend fun saveLatestSyncTime(healthDataTypeString: String, latestSyncTime: String) {
        metaDataStore.edit {
            it[stringPreferencesKey(healthDataTypeString)] = latestSyncTime
        }
    }

    suspend fun clearDataStore() {
        metaDataStore.edit {
            it.clear()
        }
    }

    // TODO: store & get loginHistory (Boolean) - (to start workManager or not)
}
