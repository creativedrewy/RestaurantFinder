package com.creativedrewy.restaurantfinder.viewmodel

sealed class TickerViewState

object Loading : TickerViewState() {
    val loadingItems = listOf(
        StockDetails(isLoading = true),
        StockDetails(isLoading = true)
    )
}

object ErrorResult: TickerViewState()

data class StockList(
    val stocks: List<StockDetails>
): TickerViewState()

data class StockDetails(
    val isLoading: Boolean = false,
    val ticker: String = "",
    val displayName: String = "",
    val displayPrice: String = "",
    val quantity: Int = 0
)