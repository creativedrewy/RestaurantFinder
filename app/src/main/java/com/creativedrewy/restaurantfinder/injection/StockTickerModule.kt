package com.creativedrewy.restaurantfinder.injection

import com.creativedrewy.restaurantfinder.BuildConfig
import com.creativedrewy.restaurantfinder.api.TickerEndpoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
class StockTickerModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.RESTAURANTS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideTickerEndpoints(retrofit: Retrofit): TickerEndpoints {
        return retrofit.create(TickerEndpoints::class.java)
    }
}