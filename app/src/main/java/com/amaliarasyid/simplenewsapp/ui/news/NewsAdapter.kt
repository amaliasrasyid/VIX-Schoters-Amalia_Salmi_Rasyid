package com.amaliarasyid.simplenewsapp.ui.news

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amaliarasyid.simplenewsapp.data.remote.response.ArticlesItem
import com.amaliarasyid.simplenewsapp.databinding.ItemNewsBinding

class NewsAdapter(private val context: Context):
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>()
{

    private var onItemCallback: OnItemCallback? = null
    private val data: ArrayList<ArticlesItem> = ArrayList()

    fun updateNewsListItem(newData: List<ArticlesItem>) {
        val diffCallback = NewsDiffCallback(this.data,newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.data.clear()
        this.data.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int { return data.size }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(data.get(position))
    }

    fun setOnItemCallback(onItemCallback: OnItemCallback){  this.onItemCallback = onItemCallback }

    interface OnItemCallback{
        fun onItemClicked(binding:ItemNewsBinding,data: ArticlesItem)
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ArticlesItem) {
            with(binding){
//                tvName.text = data.name
//                tvTimeCreated.convertDate(data.createdAt, context)
//                tvDescription.text = data.description
//                itemView.setOnClickListener{onItemCallback?.onItemClicked(binding,data)
//            }
//
//                //set transition name
//                ViewCompat.setTransitionName(tvName,"author_${data.id}")
//                ViewCompat.setTransitionName(tvTimeCreated,"time_created_${data.id}")
//                ViewCompat.setTransitionName(imgPicture,"image_story_${data.id}")
//                ViewCompat.setTransitionName(tvDescription,"desc_story_${data.id}")
            }
        }

    }
}