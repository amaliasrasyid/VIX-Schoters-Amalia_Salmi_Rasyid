package com.amaliarasyid.simplenewsapp.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.amaliarasyid.simplenewsapp.data.entities.News
import com.amaliarasyid.simplenewsapp.data.entities.NewsWithSource
import com.amaliarasyid.simplenewsapp.data.entities.Source
import com.amaliarasyid.simplenewsapp.data.remote.response.ArticlesItem
import com.amaliarasyid.simplenewsapp.data.remote.response.SourceResponse
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Locale

object Constant{
    const val API_KEY = "d637aa5e4fc6483fb5e519c46e2dfb4c"
    const val PATTERN_DATE_FULL = "yyyy-MM-dd'T'HH:mm:ss"
    const val PATTERN_DATE_BASIC= "dd MMMM yyyy"
}

fun View.mySnackBar(msg: String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_SHORT).show()
}

fun TextView.convertDate(date: String): String {
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

fun convertToNewsEntities(arg: ArticlesItem,sourceId: Int): News{
    with(arg){
        return News(0,this.author!!,this.title!!,this.description,this.publishedAt!!,this.urlToImage,this.url!!,sourceId )
    }
}

fun Fragment.back(){
    findNavController().navigateUp()
}

fun convertToNewsWithSourceEntities(list: List<ArticlesItem>): List<NewsWithSource>{
    var result = mutableListOf<NewsWithSource>()
    for (data in list){
        val source = data.sourceResponse
        val item = NewsWithSource(
            Source(0,source?.id ,source?.name!!),
            News(0,data.author,data.title!!,data.description,data.publishedAt!!,data.urlToImage,data.url!!,0)
        )
        result.add(item)
    }
    return result
}

fun convertToSourceEntities(sourceResponse: SourceResponse): Source{
    with(sourceResponse){
        return Source(0,this.id,this.name!!)
    }
}