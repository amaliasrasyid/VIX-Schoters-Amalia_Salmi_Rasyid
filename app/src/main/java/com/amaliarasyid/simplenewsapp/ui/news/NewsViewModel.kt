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

    fun loadBookmarkedNews(): LiveData<List<NewsWithSource>> = repository.getListBookmarkedNews()

    fun addNews(news: News){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNews(news)
        }
    }

    fun addSource(source: Source):LiveData<Long>  {
        val result = MutableLiveData<Long>()
        viewModelScope.launch {
            result.value = repository.insertSource(source)
        }
        return result
    }

    fun deleteNews(newsId: Int) =  repository.deleteNews(newsId)
    fun deleteSource(sourceId: Int) =  repository.deleteSource(sourceId)

    fun deleteNewsWithSource(newsId: Int,sourceId: Int) = repository.deleteNewsWithSource(newsId, sourceId)

}