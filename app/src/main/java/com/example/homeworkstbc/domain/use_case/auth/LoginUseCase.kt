package com.example.homeworkstbc.domain.use_case.auth

import com.example.homeworkstbc.domain.model.Resource
import com.example.homeworkstbc.domain.repository.AuthRepository
import com.example.homeworkstbc.domain.validator.EmailValidator
import com.example.homeworkstbc.domain.validator.LoginValidationEnum
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val emailValidator: EmailValidator
) {
    suspend operator fun invoke(email: String, password: String): Resource<String> {
        if(email.isEmpty() || password.isEmpty()) return Resource.Error(LoginValidationEnum.EMPTY_FIELDS)
        if(!emailValidator.isValid(email)) return Resource.Error(LoginValidationEnum.INCORRECT_EMAIL)

        return authRepository.login(email, password)
    }
}