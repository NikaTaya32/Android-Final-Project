package com.example.homeworkstbc.data.source.remote.api_service

import com.example.homeworkstbc.data.source.remote.model.SingleRecipeResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailsApiService {
    @GET("get")
    suspend fun getRecipeDetails(
        @Query("rId") recipeId: String
    ): Response<SingleRecipeResponseDto>
}