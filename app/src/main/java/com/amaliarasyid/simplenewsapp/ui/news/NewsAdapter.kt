package com.amaliarasyid.simplenewsapp.ui.news

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amaliarasyid.simplenewsapp.data.remote.response.ArticlesItem
import com.amaliarasyid.simplenewsapp.databinding.ItemNewsBinding
import com.amaliarasyid.simplenewsapp.utils.convertDate
import com.amaliarasyid.simplenewsapp.utils.setImage
import com.bumptech.glide.Glide

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
                tvItemAuthor.text = data.author
                tvItemTitle.text = data.title
                tvItemDescription.text = data.description
                itemView.setOnClickListener{ onItemCallback?.onItemClicked(binding,data) }

                if(data.urlToImage != null){
                    imgItemPoster.setImage(data.urlToImage)
                }
                //set transition name
                ViewCompat.setTransitionName(tvItemAuthor,"author_${data.publishedAt}")
                ViewCompat.setTransitionName(tvItemTitle,"title_${data.publishedAt}")
                ViewCompat.setTransitionName(imgItemPoster,"image_${data.publishedAt}")
                ViewCompat.setTransitionName(tvItemDescription,"desc_${data.publishedAt}")


            }
        }
    }
}