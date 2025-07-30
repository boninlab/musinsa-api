package com.musinsa.common

import java.text.NumberFormat
import java.util.*

fun Int.toPriceString(): String =
    NumberFormat.getNumberInstance(Locale.KOREA).format(this)
