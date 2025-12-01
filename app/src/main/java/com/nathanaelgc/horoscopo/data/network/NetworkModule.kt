package com.nathanaelgc.horoscopo.data.network

import com.nathanaelgc.horoscopo.BuildConfig.BASE_URL
import com.nathanaelgc.horoscopo.data.RepositoryImpl
import com.nathanaelgc.horoscopo.data.core.interceptors.AuthInterceptor
import com.nathanaelgc.horoscopo.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(okhttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {

        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    fun provideHoroscopeApiService(retrofit: Retrofit): HoroscopoApiService {
        return retrofit.create(HoroscopoApiService::class.java)
    }

    @Provides
    fun provideRepository(apiService: HoroscopoApiService): Repository{
        return RepositoryImpl(apiService)
    }
}