package com.example.myapplication.di

import com.example.myapplication.api.TaskApiService
import com.example.myapplication.repository.LocalDataSource
import com.example.myapplication.repository.NetworkDataSource
import com.example.myapplication.repository.TaskRepositoryImpl
import com.example.myapplication.utils.Contstants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()


    }


    @Provides
    fun provideApiDataSource(service: TaskApiService): NetworkDataSource {
        return NetworkDataSource(service)
    }

    @Provides
    fun provideTaskApiService(retrofit: Retrofit): TaskApiService {
        return retrofit.create(TaskApiService::class.java)
    }

    @Provides
    fun provideTaskRepo(localDataSource: LocalDataSource,networkDataSource: NetworkDataSource): TaskRepositoryImpl {
        return TaskRepositoryImpl(localDataSource,networkDataSource)
    }


    @Provides
    fun provideClient():OkHttpClient{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
      return  OkHttpClient.Builder().callTimeout(60,TimeUnit.SECONDS).readTimeout(60,TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)

            .build()

    }

}