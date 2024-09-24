package com.square.repository.data.di

import com.square.repository.data.network.SquareRepositoryClient
import com.square.repository.data.repository.SquareRepositoryImpl
import com.square.repository.domain.repository.SquareRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSquareRepository(client: SquareRepositoryClient): SquareRepository = SquareRepositoryImpl(client)
}