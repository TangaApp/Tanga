package app.books.tanga.feature.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.books.tanga.navigation.NavigationScreen
import app.books.tanga.common.data.preferences.DefaultPrefDataStoreRepository
import app.books.tanga.common.domain.session.SessionManager
import app.books.tanga.common.domain.session.SessionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: DefaultPrefDataStoreRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _state: MutableState<SplashState> = mutableStateOf(SplashState())
    val state: State<SplashState> = _state

    init {
        viewModelScope.launch {
            val completionState = repository.getOnboardingCompletionState()
            if (completionState) {
                when (sessionManager.sessionState().first()) {
                    SessionState.LoggedOut -> {
                        _state.value = SplashState(false, NavigationScreen.Authentication)
                    }
                    is SessionState.LoggedIn -> {
                        _state.value = SplashState(false, NavigationScreen.Main)
                    }
                }
            } else _state.value = SplashState(false, NavigationScreen.Onboarding)
        }
    }
}