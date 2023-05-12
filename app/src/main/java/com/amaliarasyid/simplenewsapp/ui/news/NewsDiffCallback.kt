package com.amaliarasyid.simplenewsapp.ui.news

import androidx.recyclerview.widget.DiffUtil
import com.amaliarasyid.simplenewsapp.data.remote.response.ArticlesItem

class NewsDiffCallback (
    private val mOldList: ArrayList<ArticlesItem>,
    private val mNewList: List<ArticlesItem>
): DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return mOldList.size
    }

    override fun getNewListSize(): Int {
        return mNewList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition].publishedAt == mNewList[newItemPosition].publishedAt
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition].title == mNewList[newItemPosition].title
    }

}