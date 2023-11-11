package com.shoykat.assignmentapp

import java.text.DecimalFormat

fun formatToK(value: Int): String {
    return when {
        value < 1000 -> value.toString()
        value < 1000000 -> DecimalFormat("#.#K").format(value / 1000.0)
        else -> DecimalFormat("#.#M").format(value / 1000000.0)
    }
}