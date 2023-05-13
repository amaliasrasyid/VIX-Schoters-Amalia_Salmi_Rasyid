package com.amaliarasyid.simplenewsapp.di

import android.content.Context
import com.amaliarasyid.simplenewsapp.data.repository.NewsRepository
import com.amaliarasyid.simplenewsapp.data.room.LocalDatabase
import com.amaliarasyid.simplenewsapp.data.room.dao.NewsDao
import com.amaliarasyid.simplenewsapp.data.source.NewsRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context) = LocalDatabase.getDatabase(context)
    @Provides
    @Singleton
    fun provideNewsDao(db: LocalDatabase) = db.newsDao()
    @Provides
    @Singleton
    fun provideNewsRemoteDataSource() = NewsRemoteDataSource()

    @Provides
    @Singleton
    fun provideNewsRepository(dataSource: NewsRemoteDataSource,newsDao: NewsDao) = NewsRepository(dataSource,newsDao)
}