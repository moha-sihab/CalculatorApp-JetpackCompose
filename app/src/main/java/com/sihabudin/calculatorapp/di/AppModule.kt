package com.sihabudin.calculatorapp.di

import com.sihabudin.calculatorapp.core.Calculator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideCalculator(): Calculator {
        return Calculator()
    }

}