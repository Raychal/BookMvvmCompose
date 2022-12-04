package com.raychal.book.utils

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    fun formatLongDate(date: String?): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SZ", Locale.getDefault())
        return DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT)
            .format(sdf.parse(date))
    }
}