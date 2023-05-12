package com.amaliarasyid.simplenewsapp.data.source

import com.amaliarasyid.simplenewsapp.data.remote.response.ArticlesItem
import com.amaliasrayid.storyapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsDataSource {
    fun getListTopHeadlines(apikey: String): Flow<Resource<List<ArticlesItem>>>
}