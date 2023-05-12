package com.amaliarasyid.simplenewsapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "news_table")
@Parcelize
data class NewsModel(
    @PrimaryKey
    val id: String,
    val author: String,
    val title: String,
    val description: String? = null,
    val publishedAt: String,
): Parcelable
