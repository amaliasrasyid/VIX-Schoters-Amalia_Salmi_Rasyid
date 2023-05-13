package com.amaliarasyid.simplenewsapp.ui.NewsWithSource

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amaliarasyid.simplenewsapp.data.entities.NewsWithSource
import com.amaliarasyid.simplenewsapp.databinding.ItemNewsBinding
import com.amaliarasyid.simplenewsapp.utils.convertDate
import com.amaliarasyid.simplenewsapp.utils.setImage
import com.bumptech.glide.Glide

class NewsAdapter(private val context: Context):
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>()
{

    private var onItemCallback: OnItemCallback? = null
    private val data: ArrayList<NewsWithSource> = ArrayList()

    fun updateNewsListItem(newData: List<NewsWithSource>) {
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
        fun onItemClicked(binding:ItemNewsBinding,data: NewsWithSource)
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NewsWithSource) {
            with(binding){
                with(data.news){
                    tvItemAuthor.text = this.author
                    tvItemTitle.text = this.title
                    tvItemDescription.text = this.description
                    itemView.setOnClickListener{ onItemCallback?.onItemClicked(binding,data) }

                    if(this.urlToImage != null){
                        imgItemPoster.setImage(this.urlToImage)
                    }
                    //set transition name
                    ViewCompat.setTransitionName(tvItemAuthor,"author_${this.publishedAt}")
                    ViewCompat.setTransitionName(tvItemTitle,"title_${this.publishedAt}")
                    ViewCompat.setTransitionName(imgItemPoster,"image_${this.publishedAt}")
                    ViewCompat.setTransitionName(tvItemDescription,"desc_${this.publishedAt}")
                }
            }
        }
    }
}