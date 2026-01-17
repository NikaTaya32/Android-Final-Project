package com.example.homeworkstbc.data.repository

import com.example.homeworkstbc.data.common.CustomAuthException
import com.example.homeworkstbc.data.common.HandleResponse
import com.example.homeworkstbc.data.source.local.datastore_pref.DataStoreKeys
import com.example.homeworkstbc.data.source.local.datastore_pref.DataStoreManager
import com.example.homeworkstbc.domain.model.Resource
import com.example.homeworkstbc.domain.repository.AuthRepository
import com.example.homeworkstbc.domain.validator.AuthErrorEnum
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val dataStoreManager: DataStoreManager,
    private val handleResponse: HandleResponse
) : AuthRepository {
    private suspend fun saveUserId(userId: String) {
        dataStoreManager.saveValue(DataStoreKeys.USER_ID, userId)
    }

    private suspend fun clearUserId() {
        dataStoreManager.removeKey(DataStoreKeys.USER_ID)
    }

    override suspend fun register(email: String, password: String): Resource<String> =
        handleResponse.safeAuthCall {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val userId = result.user?.uid ?: throw CustomAuthException(AuthErrorEnum.USER_ID_NOT_FOUND)
            saveUserId(userId)
            userId
        }

    override suspend fun login(email: String, password: String): Resource<String> =
        handleResponse.safeAuthCall {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val userId = result.user?.uid ?: throw CustomAuthException(AuthErrorEnum.USER_ID_NOT_FOUND)
            saveUserId(userId)
            userId
        }

    override fun getActiveUserId(): Flow<String?> {
        return dataStoreManager.getValue(DataStoreKeys.USER_ID)
    }

    override suspend fun logoutUser() =
        handleResponse.safeAuthCall {
            firebaseAuth.signOut()
            clearUserId()
        }

    override fun isUserLoggedIn(): Flow<Boolean> {
        return dataStoreManager.isUserAuthorized()
    }
}