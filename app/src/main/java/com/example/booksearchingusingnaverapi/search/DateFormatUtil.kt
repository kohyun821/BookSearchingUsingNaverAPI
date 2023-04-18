package com.example.booksearchingusingnaverapi.search

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

object DateFormatUtil {
    private const val FORMAT_MM_DD = "MM.dd"

    @JvmStatic
    @BindingAdapter("android:formattedDate")
    fun setFormattedDate(textView: TextView, timestamp: Long) {
        textView.text = SimpleDateFormat(FORMAT_MM_DD, Locale.getDefault()).format(Date(timestamp))
    }
}
