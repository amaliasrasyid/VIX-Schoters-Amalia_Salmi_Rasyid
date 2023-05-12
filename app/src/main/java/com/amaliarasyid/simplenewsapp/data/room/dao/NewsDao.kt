package com.amaliarasyid.simplenewsapp.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amaliarasyid.simplenewsapp.data.model.NewsModel

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(data: NewsModel)

    @Query("SELECT * FROM news_table")
    fun getPostList(): List<NewsModel>

    @Query("DELETE FROM news_table")
    suspend fun deleteAllPost()
}