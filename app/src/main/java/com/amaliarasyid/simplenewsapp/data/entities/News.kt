package com.amaliarasyid.simplenewsapp.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "news_table",
    foreignKeys = [ForeignKey(
        entity = Source::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("sourceId"),
        onDelete = ForeignKey.CASCADE
    )]
)
@Parcelize
data class News(
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    val author: String?,
    val title: String,
    val description: String?,
    val publishedAt: String,
    val urlToImage: String?,
    val url: String,
    var sourceId: Int,
): Parcelable
