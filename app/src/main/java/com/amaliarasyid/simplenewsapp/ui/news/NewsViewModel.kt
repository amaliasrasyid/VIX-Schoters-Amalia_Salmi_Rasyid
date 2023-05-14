package com.amaliarasyid.simplenewsapp.ui.news

import androidx.lifecycle.*
import com.amaliarasyid.simplenewsapp.data.entities.News
import com.amaliarasyid.simplenewsapp.data.entities.NewsWithSource
import com.amaliarasyid.simplenewsapp.data.entities.Source
import com.amaliarasyid.simplenewsapp.data.remote.response.ArticlesItem
import com.amaliarasyid.simplenewsapp.data.repository.NewsRepository
import com.amaliasrayid.storyapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
): ViewModel() {

    fun getTopHeadlines(token: String): LiveData<Resource<List<ArticlesItem>>> = repository.getListTopHeadlines(token).asLiveData()

    fun loadBookmarkedNews(): LiveData<List<NewsWithSource>> = repository.getListBookmarkedNews().asLiveData()

    fun findNews(publishedAt: String): NewsWithSource? = repository.findNews(publishedAt)

    fun addSource(source: Source): LiveData<Long> {
        var returnedId = MutableLiveData<Long>()
        viewModelScope.launch {
            returnedId.value = repository.insertSource(source)
        }
        return returnedId
    }

    fun addNews(news: News){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNews(news)
        }
    }

    fun deleteNewsWithSource(newsId: Int,sourceId: Int) {
        viewModelScope.launch {
            repository.deleteNewsWithSource(newsId, sourceId)
        }
    }
}