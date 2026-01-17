package com.example.homeworkstbc.di

import com.example.homeworkstbc.data.repository.AuthRepositoryImpl
import com.example.homeworkstbc.data.repository.FavoritesRepositoryImpl
import com.example.homeworkstbc.data.repository.RecipeRepositoryImpl
import com.example.homeworkstbc.domain.repository.AuthRepository
import com.example.homeworkstbc.domain.repository.FavoritesRepository
import com.example.homeworkstbc.domain.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl) : AuthRepository

    @Binds
    @Singleton
    abstract fun bindRecipeRepository(
        recipeRepositoryImpl: RecipeRepositoryImpl
    ): RecipeRepository

    @Binds
    @Singleton
    abstract fun bindFavoritesRepository(favoritesRepositoryImpl: FavoritesRepositoryImpl) : FavoritesRepository
}