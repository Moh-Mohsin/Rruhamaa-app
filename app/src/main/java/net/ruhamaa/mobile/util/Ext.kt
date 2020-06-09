package net.ruhamaa.mobile.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(format: String = "yyyy/MM/dd HH:mm:ss", locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}