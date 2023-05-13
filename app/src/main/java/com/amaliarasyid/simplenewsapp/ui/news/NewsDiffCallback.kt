package com.amaliarasyid.simplenewsapp.ui.NewsWithSource

import androidx.recyclerview.widget.DiffUtil
import com.amaliarasyid.simplenewsapp.data.entities.NewsWithSource

class NewsDiffCallback (
    private val mOldList: ArrayList<NewsWithSource>,
    private val mNewList: List<NewsWithSource>
): DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return mOldList.size
    }

    override fun getNewListSize(): Int {
        return mNewList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition].news.publishedAt == mNewList[newItemPosition].news.publishedAt
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition].news.title == mNewList[newItemPosition].news.title
    }

}