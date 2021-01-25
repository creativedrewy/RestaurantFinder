package com.creativedrewy.restaurantfinder.api

import retrofit2.http.GET

interface TickerEndpoints {

    @GET("portfolio.json")
    suspend fun loadTickerList(): StockListDto

    @GET("portfolio_malformed.json")
    suspend fun loadMalformedTicker(): StockListDto

    @GET("portfolio_empty.json")
    suspend fun loadEmptyTicker(): StockListDto
}