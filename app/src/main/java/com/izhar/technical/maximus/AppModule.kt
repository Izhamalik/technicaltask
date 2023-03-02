package com.izhar.technical.maximus

import com.dailydriver.islamicapp.repositories.ResponseRepositoryImp
import com.izhar.technical.maximus.remote.FactRetroService
import com.izhar.technical.maximus.repository.ResponseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient()
        return Retrofit.Builder()
            .baseUrl("https://catfact.ninja/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideFactRetroService(retrofit: Retrofit): FactRetroService {
        return retrofit.create(FactRetroService::class.java)
    }

    @Provides
    @Singleton
    fun provideResponseRepository(
        service: FactRetroService
    ): ResponseRepository {
        return ResponseRepositoryImp(service)
    }


}