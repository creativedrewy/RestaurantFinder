package com.creativedrewy.restaurantfinder.usecase

import java.math.BigDecimal
import javax.inject.Inject

class CurrencyDisplayUseCase @Inject constructor() {

    fun convertCentsToDollarString(cents: Int): String {
        val dollars = BigDecimal(cents).movePointLeft(2)

        val intValue = dollars.toInt()
        val decimal = dollars - BigDecimal(intValue)

        var intString = intValue.toString()
        if (intString.length > 3) {
            intString = "${ intString.substring(0, intString.length - 3) },${ intString.substring(intString.length - 3, intString.length) }"
        }

        val decString = decimal.toPlainString().substringAfter(".")

        return "$${intString}.$decString"
    }
}