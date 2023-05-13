package com.amaliarasyid.simplenewsapp.data.repository

import androidx.lifecycle.LiveData
import com.amaliarasyid.simplenewsapp.data.entities.News
import com.amaliarasyid.simplenewsapp.data.entities.NewsWithSource
import com.amaliarasyid.simplenewsapp.data.entities.Source
import com.amaliarasyid.simplenewsapp.data.remote.response.ArticlesItem
import com.amaliarasyid.simplenewsapp.data.room.dao.NewsDao
import com.amaliarasyid.simplenewsapp.data.source.NewsDataSource
import com.amaliarasyid.simplenewsapp.data.source.NewsRemoteDataSource
import com.amaliasrayid.storyapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val dataSource: NewsRemoteDataSource,
    private val newsDao: NewsDao
    ): NewsDataSource {

    override fun getListTopHeadlines(apikey: String): Flow<Resource<List<ArticlesItem>>> = dataSource.getTopHeadlines(apikey)
    override fun getListBookmarkedNews(): LiveData<List<NewsWithSource>> = newsDao.getNewsWithSource()

    override fun insertNews(data: News) = newsDao.insertNews(data)
    override fun insertSource(data: Source): Long = newsDao.insertSource(data)
    override fun deleteNews(newsId: Int) = newsDao.deleteNews(newsId)
    override fun deleteSource(sourceId: Int) = newsDao.deleteSource(sourceId)
    override fun deleteNewsWithSource(newsId: Int, sourceId: Int) = newsDao.deleteNewsWithSource(newsId, sourceId)

}