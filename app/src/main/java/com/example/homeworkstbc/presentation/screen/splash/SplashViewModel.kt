package com.example.homeworkstbc.presentation.screen.splash

import androidx.lifecycle.viewModelScope
import com.example.homeworkstbc.domain.use_case.auth.CheckAuthStatusUseCase
import com.example.homeworkstbc.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor(private val checkAuthStatusUseCase: CheckAuthStatusUseCase) :
    BaseViewModel<SplashState, SplashSideEffect>(SplashState()) {
    private var splashJob: Job? = null


    fun onEvent(event: SplashEvent) {
        when (event) {
            SplashEvent.OnStartSplash -> onStartSplash()
            SplashEvent.OnStopSplash -> onStopSplash()
        }
    }

    private fun onStartSplash() {
        splashJob = viewModelScope.launch {
            updateState { it.copy(loader = true) }
            delay(DELAY_SEC)

            val isAuthorized = checkAuthStatusUseCase.invoke()

            if (isAuthorized) {
                emitSideEffect(SplashSideEffect.NavigateToHome)
                updateState { it.copy(loader = false) }
            } else {
                emitSideEffect(SplashSideEffect.NavigateToAuth)
                updateState { it.copy(loader = false) }
            }
        }
    }

    private fun onStopSplash() {
        splashJob?.cancel()
    }

    companion object {
        const val DELAY_SEC = 2000L
    }
}