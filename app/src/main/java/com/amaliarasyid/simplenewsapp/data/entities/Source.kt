package com.amaliarasyid.simplenewsapp.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "sources_table")
@Parcelize
data class Source (
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    val id_source: String?,
    val name: String,
) : Parcelable