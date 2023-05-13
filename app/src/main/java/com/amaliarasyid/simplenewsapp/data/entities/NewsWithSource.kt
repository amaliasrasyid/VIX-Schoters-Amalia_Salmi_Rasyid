package com.amaliarasyid.simplenewsapp.data.entities

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsWithSource(
    @Embedded val source: Source,
    @Relation(
        parentColumn = "id",
        entityColumn = "sourceId"
    )
    val news: News
): Parcelable
