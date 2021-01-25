package com.creativedrewy.restaurantfinder.usecase

import com.creativedrewy.restaurantfinder.api.StockListDto
import com.creativedrewy.restaurantfinder.api.TickerEndpoints
import javax.inject.Inject

class StockTickerUseCase @Inject constructor(
    private val stockEndpoints: TickerEndpoints,
    private val randomizer: EndpointRandomizer
) {

    /**
     * Load the data from the relevant endpoint. NOTE: in the case of handling the "empty" endpoint,
     * we actually manually create a Dto; the empty enpoint provided actually is technically malformed
     * from GSON's perspective. So we create the empty list case "manually"
     */
    suspend fun loadTickerStocks(): StockListDto {
        return when (randomizer.determineRandomEndpoint()) {
            Standard -> stockEndpoints.loadTickerList()
            Malformed -> stockEndpoints.loadMalformedTicker()
            Empty -> StockListDto(listOf())
        }
    }
}