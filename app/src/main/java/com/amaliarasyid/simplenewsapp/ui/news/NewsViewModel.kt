package com.amaliarasyid.simplenewsapp.ui.news

import androidx.lifecycle.*
import com.amaliarasyid.simplenewsapp.data.remote.response.ArticlesItem
import com.amaliarasyid.simplenewsapp.data.repository.NewsRepository
import com.amaliasrayid.storyapp.utils.Resource
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
): ViewModel() {

    fun getTopHeadlines(token: String): LiveData<Resource<List<ArticlesItem>>> = repository.getListTopHeadlines(token).asLiveData()

}