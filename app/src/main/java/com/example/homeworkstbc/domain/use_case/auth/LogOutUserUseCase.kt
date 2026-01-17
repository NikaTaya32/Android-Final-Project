package com.example.homeworkstbc.domain.use_case.auth

import com.example.homeworkstbc.domain.repository.AuthRepository
import javax.inject.Inject

class LogOutUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() {
        authRepository.logoutUser()
    }
}