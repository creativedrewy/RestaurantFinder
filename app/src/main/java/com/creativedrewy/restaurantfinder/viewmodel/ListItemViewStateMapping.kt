package com.creativedrewy.restaurantfinder.viewmodel

import android.content.res.Resources
import com.creativedrewy.restaurantfinder.R
import com.creativedrewy.restaurantfinder.api.RestaurantDto
import com.creativedrewy.restaurantfinder.api.RestaurantStatusDto
import javax.inject.Inject

class ListItemViewStateMapping @Inject constructor(
    private val resources: Resources
) {

    fun mapDtoToViewState(dto: RestaurantDto, loading: Boolean = false): RestaurantDetails {
        return RestaurantDetails(
            isLoading = loading,
            displayName = dto.name,
            desc = dto.description,
            imageUrl = dto.coverImgUrl,
            status = getStatusString(dto.status)
        )
    }

    private fun getStatusString(statusDto: RestaurantStatusDto): String {
        return when {
            statusDto.unavailableReason != null -> resources.getString(R.string.store_closed)
            statusDto.asapMinutesRange.size == 1 -> resources.getString(R.string.asap_minutes, statusDto.asapMinutesRange[0])
            statusDto.asapMinutesRange.size == 2 -> {
                val average = ((statusDto.asapMinutesRange[0] + statusDto.asapMinutesRange[1]).toDouble() / 2).toInt()
                resources.getString(R.string.asap_minutes, average)
            }
            else -> ""
        }
    }

}