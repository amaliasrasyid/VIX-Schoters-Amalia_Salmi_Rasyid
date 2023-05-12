package com.amaliasrayid.storyapp.data.network

import com.amaliarasyid.simplenewsapp.data.remote.response.NewsResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("top-headlines?country=id")
    suspend fun topHeadlines(
        @Query("apikey") apikey: String
    ) : Response<NewsResponse>


}