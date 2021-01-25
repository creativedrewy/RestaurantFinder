package com.creativedrewy.restaurantfinder.injection

import com.creativedrewy.restaurantfinder.BuildConfig
import com.creativedrewy.restaurantfinder.api.RestaurantsEndpoints
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RestaurantFinderModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.RESTAURANTS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideRestaurantsEndpoints(retrofit: Retrofit): RestaurantsEndpoints {
        return retrofit.create(RestaurantsEndpoints::class.java)
    }
}