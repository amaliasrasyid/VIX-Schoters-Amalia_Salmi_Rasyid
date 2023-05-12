package com.amaliarasyid.simplenewsapp.data.source

import com.amaliarasyid.simplenewsapp.data.remote.response.ArticlesItem
import com.amaliarasyid.simplenewsapp.data.remote.response.NewsResponse
import com.amaliasrayid.storyapp.data.network.connection.ApiConfig
import com.amaliasrayid.storyapp.utils.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRemoteDataSource {

    fun getTopHeadlines(apikey: String): Flow<Resource<List<ArticlesItem>>> = flow {
        try{
            emit(Resource.loading(null))
            val response = ApiConfig.create().topHeadlines(apikey)

            if(response.isSuccessful && response.body() != null){
                emit(Resource.success(response.body()!!.articles))
            }else if(response.errorBody() != null){
                val errResult = Gson().fromJson(response.errorBody()?.charStream(), NewsResponse::class.java)
                emit(Resource.error(errResult.status,null))
            }else{
                emit(Resource.error(response.message(),null))
            }
        }catch (e: Exception){
            emit(Resource.error(e.message,null))
        }
    }
}
