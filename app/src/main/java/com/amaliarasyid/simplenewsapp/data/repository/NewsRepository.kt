package com.amaliarasyid.simplenewsapp.data.repository

import com.amaliarasyid.simplenewsapp.data.remote.response.ArticlesItem
import com.amaliarasyid.simplenewsapp.data.source.NewsDataSource
import com.amaliarasyid.simplenewsapp.data.source.NewsRemoteDataSource
import com.amaliasrayid.storyapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(private val dataSource: NewsRemoteDataSource): NewsDataSource {

    override fun getListTopHeadlines(apikey: String): Flow<Resource<List<ArticlesItem>>> = dataSource.getTopHeadlines(apikey)

}