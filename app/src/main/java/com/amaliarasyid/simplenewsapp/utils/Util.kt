package com.amaliarasyid.simplenewsapp.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.amaliarasyid.simplenewsapp.utils.Constant.PATTERN_DATE_BASIC
import com.amaliarasyid.simplenewsapp.utils.Constant.PATTERN_DATE_FULL
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

object Constant{
    const val API_KEY = "d637aa5e4fc6483fb5e519c46e2dfb4c"
    const val PATTERN_DATE_FULL = "yyyy-MM-dd'T'HH:mm:ss"
    const val PATTERN_DATE_BASIC= "dd MMMM yyyy"
}

fun View.mySnackBar(msg: String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_SHORT).show()
}

fun TextView.convertDate(date: String, context: Context): String {
    var dateFormat = SimpleDateFormat(Constant.PATTERN_DATE_FULL, Locale.getDefault())
    val newDate = dateFormat.parse(date)
    dateFormat = SimpleDateFormat(Constant.PATTERN_DATE_BASIC)
    var result = dateFormat.format(newDate)
    return ", ${result}"
}

fun ImageView.setImage(url: String) =
    Glide.with(this)
        .load(url)
        .into(this)