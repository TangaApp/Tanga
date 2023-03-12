package app.books.tanga.common.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import app.books.tanga.common.domain.session.SessionId
import kotlinx.coroutines.flow.*
import javax.inject.Inject

private const val ONBOARDING_PREF_KEY = "onboarding_completed"
private const val SESSION_ID_PREF_KEY = "session_id"

val Context.defaultDataStore: DataStore<Preferences>
        by preferencesDataStore(name = "default_tanga_shared_prefs")

class DefaultPrefDataStoreRepository @Inject constructor(context: Context) {

    private object PreferencesKey {
        val onBoardingCompletionKey = booleanPreferencesKey(name = ONBOARDING_PREF_KEY)

        val sessionIdKey = stringPreferencesKey(name = SESSION_ID_PREF_KEY)
    }

    private val dataStore = context.defaultDataStore

    suspend fun saveOnboardingCompletionState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingCompletionKey] = completed
        }
    }

    suspend fun getOnboardingCompletionState(): Boolean {
        return dataStore.data.map { preferences ->
            preferences[PreferencesKey.onBoardingCompletionKey] ?: false
        }.first()
    }

    suspend fun saveSessionId(sessionId: SessionId) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.sessionIdKey] = sessionId.value
        }
    }

    fun getSessionId(): Flow<SessionId?> {
        return dataStore.data
            .map { preferences ->
                preferences[PreferencesKey.sessionIdKey]
            }
            .map { it?.let { SessionId(it) } }
    }

    suspend fun removeSessionId() {
        dataStore.edit { preferences ->
            preferences.remove(PreferencesKey.sessionIdKey)
        }
    }
}