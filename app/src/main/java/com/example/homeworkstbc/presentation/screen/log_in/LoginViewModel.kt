package com.example.homeworkstbc.presentation.screen.log_in

import androidx.lifecycle.viewModelScope
import com.example.homeworkstbc.domain.model.Resource
import com.example.homeworkstbc.domain.use_case.auth.LoginUseCase
import com.example.homeworkstbc.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginState, LoginSideEffect>(LoginState()) {


    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.LoginUser -> loginUser(event.email, event.password)
        }
    }

    private fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            val result = loginUseCase.invoke(email, password)
            when (result) {
                is Resource.Error -> {
                    updateState { it.copy(loader = false) }
                    emitSideEffect(LoginSideEffect.ShowError(result.message))
                }
                is Resource.Loader -> updateState { it.copy(loader = true) }
                is Resource.Success -> {
                    updateState { it.copy(loader = false) }
                    emitSideEffect(LoginSideEffect.NavigateToHome)
                }
            }
        }
    }
}