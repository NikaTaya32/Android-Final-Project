package com.example.homeworkstbc.presentation.screen.register

import androidx.lifecycle.viewModelScope
import com.example.homeworkstbc.domain.model.Resource
import com.example.homeworkstbc.domain.use_case.auth.RegisterUseCase
import com.example.homeworkstbc.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : BaseViewModel<RegisterState, RegisterSideEffect>(RegisterState()) {


    fun onEvent(event: RegisterEvent) {
        when(event) {
            is RegisterEvent.RegisterUser -> registerUser(event.email, event.password, event.repeatPassword)
        }
    }

    private fun registerUser(email: String, password: String, repeatPassword: String) {
        viewModelScope.launch {
            val result = registerUseCase.invoke(email, password, repeatPassword)

            when (result) {
                is Resource.Error -> {
                    updateState { it.copy(loader = false) }
                    emitSideEffect(RegisterSideEffect.SendError(result.message))
                }
                is Resource.Loader -> updateState { it.copy(loader = true) }
                is Resource.Success -> {
                    updateState { it.copy(loader = false) }
                    emitSideEffect(RegisterSideEffect.NavigateToHome)
                }
            }
        }
    }
}