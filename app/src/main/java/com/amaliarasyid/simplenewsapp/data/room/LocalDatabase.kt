package com.amaliarasyid.simplenewsapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amaliarasyid.simplenewsapp.data.model.NewsModel
import com.amaliarasyid.simplenewsapp.data.room.dao.NewsDao

@Database(entities = [NewsModel::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object{
        @Volatile
        private var instance: LocalDatabase? = null

        fun getDatabase(context: Context): LocalDatabase =
            instance ?: synchronized(this){
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, LocalDatabase::class.java, "local.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }

}