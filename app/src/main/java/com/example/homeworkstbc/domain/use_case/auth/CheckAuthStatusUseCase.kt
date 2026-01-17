package com.example.homeworkstbc.domain.use_case.auth

import com.example.homeworkstbc.domain.repository.AuthRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CheckAuthStatusUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): Boolean {
        return authRepository.isUserLoggedIn().first()
    }
}