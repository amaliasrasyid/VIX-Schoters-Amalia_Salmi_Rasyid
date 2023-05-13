package com.amaliarasyid.simplenewsapp.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.amaliarasyid.simplenewsapp.data.entities.News
import com.amaliarasyid.simplenewsapp.data.entities.NewsWithSource
import com.amaliarasyid.simplenewsapp.data.entities.Source

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(data: News)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSource(data: Source): Long

    @Transaction
    @Query("SELECT * FROM news_table as news, sources_table as sources WHERE news.sourceId = sources.id")
    fun getNewsWithSource(): LiveData<List<NewsWithSource>>

    @Query("DELETE FROM news_table WHERE news_table.id = :newsId ")
    abstract fun deleteNews(newsId: Int)

    @Query("DELETE FROM sources_table WHERE sources_table.id = :sourceId ")
    abstract fun deleteSource(sourceId: Int)

    @Transaction
    @Query("")
    fun deleteNewsWithSource(newsId: Int,sourceId: Int){
        deleteNews(newsId)
        deleteSource(sourceId)
    }
}