package com.square.repository.data.di

import com.square.repository.BuildConfig
import com.square.repository.data.network.SquareRepositoryService
import com.square.repository.data.network.SquareRepositoryClient
import com.square.repository.data.network.SquareRepositoryClientImpl
import com.square.repository.data.network.mapper.ApiMapper
import com.square.repository.data.network.mapper.ApiMapperImpl
import com.square.repository.data.repository.SquareRepositoryImpl
import com.square.repository.domain.repository.SquareRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun provideSquareRepositoryService(moshi: Moshi): SquareRepositoryService =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(SquareRepositoryService::class.java)

    @Provides
    @Singleton
    fun provideApiMapper(): ApiMapper = ApiMapperImpl()

    @Provides
    @Singleton
    fun provideClient(
        api: SquareRepositoryService,
        apiMapper: ApiMapper
    ): SquareRepositoryClient {
        return SquareRepositoryClientImpl(api, apiMapper)
    }

    @Provides
    @Singleton
    fun provideSquareRepository(client: SquareRepositoryClient): SquareRepository = SquareRepositoryImpl(client)
}