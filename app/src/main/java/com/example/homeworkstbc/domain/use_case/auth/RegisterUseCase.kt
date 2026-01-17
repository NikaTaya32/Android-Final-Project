package com.example.homeworkstbc.domain.use_case.auth

import com.example.homeworkstbc.domain.model.Resource
import com.example.homeworkstbc.domain.validator.SignUpValidationEnum
import com.example.homeworkstbc.domain.repository.AuthRepository
import com.example.homeworkstbc.domain.validator.EmailValidator
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val emailValidator: EmailValidator
) {
    suspend operator fun invoke(email: String, password: String, repeatPassword: String): Resource<String> {
        if(email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) return Resource.Error(SignUpValidationEnum.EMPTY_FIELDS)
        if(!emailValidator.isValid(email)) return Resource.Error(SignUpValidationEnum.INCORRECT_EMAIL)
        if(password != repeatPassword) return Resource.Error(SignUpValidationEnum.PASSWORD_DO_NOT_MATCH)
        if(password.length < 6) return Resource.Error(SignUpValidationEnum.PASSWORD_TOO_SHORT)

        return authRepository.register(email, password)
    }
}