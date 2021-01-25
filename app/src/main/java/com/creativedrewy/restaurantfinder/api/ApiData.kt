package com.creativedrewy.restaurantfinder.api

import com.google.gson.annotations.SerializedName

data class StockListDto(
    val stocks: List<StockInfoDto>
)

data class StockInfoDto(
    val ticker: String,
    val name: String,
    val currency: String,
    @SerializedName("current_price_cents")
    val currentPriceCents: Int,
    val quantity: Int,
    @SerializedName("current_price_timestamp")
    val currentPriceTimestamp: Long
)