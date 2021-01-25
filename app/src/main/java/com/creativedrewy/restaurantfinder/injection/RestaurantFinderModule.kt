package com.creativedrewy.restaurantfinder.injection

import com.creativedrewy.restaurantfinder.BuildConfig
import com.creativedrewy.restaurantfinder.api.RestaurantsEndpoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
class RestaurantFinderModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.RESTAURANTS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideRestaurantsEndpoints(retrofit: Retrofit): RestaurantsEndpoints {
        return retrofit.create(RestaurantsEndpoints::class.java)
    }
}