package com.example.jetpackcomponentswithretrofit.dependencyinjection

import com.example.jetpackcomponentswithretrofit.repository.JsonPlaceHolderRepository
import com.example.jetpackcomponentswithretrofit.service.PlaceHolderApi
import com.example.jetpackcomponentswithretrofit.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideCryptoRepository(
        api:PlaceHolderApi
    )=JsonPlaceHolderRepository(api)

    @Singleton
    @Provides
    fun provideApi():PlaceHolderApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PlaceHolderApi::class.java)
    }
}