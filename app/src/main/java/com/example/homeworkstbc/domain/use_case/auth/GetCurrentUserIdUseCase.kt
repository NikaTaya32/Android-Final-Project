package com.example.homeworkstbc.domain.use_case.auth

import com.example.homeworkstbc.domain.repository.AuthRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class GetCurrentUserIdUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): String? = authRepository.getActiveUserId().firstOrNull()
}