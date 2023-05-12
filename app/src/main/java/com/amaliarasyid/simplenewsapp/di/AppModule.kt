package com.amaliarasyid.simplenewsapp.di

import com.amaliarasyid.simplenewsapp.data.repository.NewsRepository
import com.amaliarasyid.simplenewsapp.data.source.NewsRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNewsRemoteDataSource() = NewsRemoteDataSource()

    @Provides
    @Singleton
    fun provideNewsRepository(dataSource: NewsRemoteDataSource) = NewsRepository(dataSource)
}