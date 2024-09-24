package com.square.repository.data.di

import com.square.repository.BuildConfig
import com.square.repository.data.network.SquareRepositoryClient
import com.square.repository.data.network.SquareRepositoryClientImpl
import com.square.repository.data.network.SquareRepositoryService
import com.square.repository.data.network.mapper.RepositoryItemApiMapper
import com.square.repository.data.network.mapper.RepositoryItemApiMapperImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Provides
    @Singleton
    fun provideSquareRepositoryService(moshi: Moshi): SquareRepositoryService =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(SquareRepositoryService::class.java)

    @Provides
    fun provideRepositoryItemMapper(): RepositoryItemApiMapper = RepositoryItemApiMapperImpl()

    @Provides
    @Singleton
    fun provideClient(
        api: SquareRepositoryService,
        apiMapper: RepositoryItemApiMapper
    ): SquareRepositoryClient {
        return SquareRepositoryClientImpl(api, apiMapper)
    }
}