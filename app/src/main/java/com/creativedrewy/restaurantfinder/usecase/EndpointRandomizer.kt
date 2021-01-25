package com.creativedrewy.restaurantfinder.usecase

import javax.inject.Inject

sealed class EndpointToUse

object Standard : EndpointToUse()
object Malformed : EndpointToUse()
object Empty: EndpointToUse()

class EndpointRandomizer @Inject constructor() {

    /**
     * Randomly select an endpoint type to use. Weighted to favor the "standard" endpoint
     * over others.
     */
    fun determineRandomEndpoint(): EndpointToUse {
        val random = Math.random() * 9

        return when {
            random < 5 -> Standard
            random >= 5 && random < 7 -> Malformed
            else -> Empty
        }
    }

}