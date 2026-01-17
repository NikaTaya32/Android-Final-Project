package com.example.homeworkstbc.di

import android.content.Context
import androidx.room.Room
import com.example.homeworkstbc.data.source.local.room.AppDatabase
import com.example.homeworkstbc.data.source.local.room.RecipeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "recipe_app_database").build()
    }

    @Provides
    @Singleton
    fun provideFavoriteRecipeDao(appDatabase: AppDatabase): RecipeDao {
        return appDatabase.favoriteRecipeDao()
    }
}