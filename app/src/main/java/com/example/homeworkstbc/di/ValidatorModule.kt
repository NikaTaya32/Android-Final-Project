package com.example.homeworkstbc.di

import com.example.homeworkstbc.data.validator.EmailValidatorImpl
import com.example.homeworkstbc.domain.validator.EmailValidator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ValidatorModule {

    @Binds
    @Singleton
    abstract fun bindEmailValidator(
        emailValidatorImpl: EmailValidatorImpl
    ): EmailValidator
}