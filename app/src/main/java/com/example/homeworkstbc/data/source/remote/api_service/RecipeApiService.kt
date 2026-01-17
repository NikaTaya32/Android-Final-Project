package com.example.homeworkstbc.data.source.remote.api_service

import com.example.homeworkstbc.data.source.remote.model.RecipeResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApiService {
    @GET("search")
    suspend fun searchRecipes(
        @Query("q") searchQuery: String
    ): Response<RecipeResponseDto>
}