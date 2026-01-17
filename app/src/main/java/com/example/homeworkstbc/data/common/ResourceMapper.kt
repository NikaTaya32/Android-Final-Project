package com.example.homeworkstbc.data.common

import com.example.homeworkstbc.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <Domain, Dto> Flow<Resource<Domain>>.asResource(
    onSuccess: (Domain) -> Dto
): Flow<Resource<Dto>> {
    return this.map { resource ->
        when(resource) {
            is Resource.Error -> Resource.Error(message =  resource.message)
            is Resource.Loader -> Resource.Loader(isLoading =  resource.isLoading)
            is Resource.Success -> Resource.Success(data = onSuccess(resource.data))
        }
    }
}