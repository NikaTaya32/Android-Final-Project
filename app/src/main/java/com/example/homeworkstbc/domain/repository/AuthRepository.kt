package com.example.homeworkstbc.domain.repository

import com.example.homeworkstbc.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(email: String, password: String): Resource<String>
    suspend fun login(email: String, password: String): Resource<String>
    fun getActiveUserId(): Flow<String?>
    suspend fun logoutUser(): Resource<Unit>
    fun isUserLoggedIn(): Flow<Boolean>
}