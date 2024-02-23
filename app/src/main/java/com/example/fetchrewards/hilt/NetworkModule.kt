package com.example.fetchrewards.hilt

import com.example.fetchrewards.Constants
import com.example.fetchrewards.FetchRewardsService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideCoroutineTPSService(): FetchRewardsService {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constants.BASE_URL)
            .build()
        return retrofit.create(FetchRewardsService::class.java)
    }
}