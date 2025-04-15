package edu.iesam.meceiot.core.presentation.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toFormatDate(
    toFormat: String = "dd-MM-yyyy HH:mm"
): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(toFormat, Locale.getDefault())
    return formatter.format(date)
}