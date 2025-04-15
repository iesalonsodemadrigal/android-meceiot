package edu.iesam.meceiot.core.presentation.extensions

import java.text.SimpleDateFormat
import java.util.Locale

fun String.toDateFormat(
    fromFormat: String = "yyyy-MM-dd HH:mm",
    toFormat: String = "dd-MM-yyyy HH:mm"
): String {
    val from = SimpleDateFormat(fromFormat, Locale.ENGLISH)
    val to = SimpleDateFormat(toFormat, Locale.getDefault())
    return to.format(from.parse(this)!!)

}