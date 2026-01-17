package com.example.homeworkstbc.domain.model

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val message: Any) : Resource<Nothing>()
    data class Loader(val isLoading: Boolean) : Resource<Nothing>()
}