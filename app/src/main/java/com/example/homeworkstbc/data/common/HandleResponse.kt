package com.example.homeworkstbc.data.common

import com.example.homeworkstbc.domain.model.Resource
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class HandleResponse @Inject constructor() {
    suspend fun <T> safeAuthCall(call: suspend () -> T): Resource<T> {
        return try {
            val result = call()
            Resource.Success(result)
        } catch (e: FirebaseAuthException) {
            Resource.Error(e.errorCode)
        } catch (e: CustomAuthException) {
            Resource.Error(e.error)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Unknown error occurred.")
        }
    }

    fun <T> safeApiCall(call: suspend () -> Response<T>) = flow {
        emit(Resource.Loader(isLoading = true))
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    emit(Resource.Success(body))
                }
            } else {
                val error = response.errorBody()?.string()!!
                emit(Resource.Error(error))
            }
        } catch (e: IOException) {
            emit(Resource.Error("Network error: ${e.message.orEmpty()}"))
        } catch (e: HttpException) {
            emit(Resource.Error("HTTP error: ${e.message.orEmpty()}"))
        } catch (e: IllegalStateException) {
            emit(Resource.Error(message = e.message.orEmpty()))
        } catch (e: Exception) {
            emit(Resource.Error("An unexpected error occurred: ${e.message.orEmpty()}"))
        }
    }
}
