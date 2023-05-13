package com.amaliarasyid.simplenewsapp.ui.news.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.amaliarasyid.simplenewsapp.databinding.FragmentDetailBinding
import com.amaliarasyid.simplenewsapp.ui.news.NewsViewModel
import com.amaliarasyid.simplenewsapp.utils.convertDate
import com.amaliarasyid.simplenewsapp.utils.convertToNewsEntities
import com.amaliarasyid.simplenewsapp.utils.convertToSourceEntities
import com.amaliarasyid.simplenewsapp.utils.mySnackBar
import com.amaliarasyid.simplenewsapp.utils.setImage
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailFragment : Fragment(),OnClickListener {
    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: NewsViewModel by viewModels()
    private var sourceUrl: String = ""
    private var sourceId = 0
    private var newsId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            with(args.extraNewsSource){
                with(news){
                    if(this.urlToImage != null) imvNews.setImage(this.urlToImage)
                    tvAuthor.text = this.author
                    tvTitle.text = this.title
                    tvDescription.text = this.description
                    tvPublishedDate.convertDate(this.publishedAt!!)

                    ViewCompat.setTransitionName(tvAuthor,"author_${this.publishedAt}")
                    ViewCompat.setTransitionName(tvTitle,"title_${this.publishedAt}")
                    ViewCompat.setTransitionName(imvNews,"image_${this.publishedAt}")
                    ViewCompat.setTransitionName(tvDescription,"desc_${this.publishedAt}")
                    sourceUrl = this.url!!
                    this@DetailFragment.sourceId = this.sourceId
                    newsId = this.id
                }
                tvSource.text = this.source.name

                tvSource.setOnClickListener(this@DetailFragment)
                btnBookmark.setOnClickListener(this@DetailFragment)
            }
        }
    }

    override fun onClick(v: View?) {
        with(binding){
            when(v){
                tvSource -> {
                    val url = sourceUrl
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(url)
                    startActivity(i)
                }
                btnBookmark -> {
//                    insert()
//                    v.mySnackBar("Insert Data Bookmark")
                    delete()
                    v.mySnackBar("Delete Data Bookmark")
                }
                else -> Timber.d("Unknown View Clicked")
            }
        }
    }
    fun insert(){
        with(args.extraNewsSource){
            val source = this.source
            viewModel.addSource(source).observe(viewLifecycleOwner){ id ->
                var news = this.news
                news.sourceId = id.toInt()
                viewModel.addNews(news)
            }

            Timber.d("button bookmark clicked")
        }
    }
    fun delete(){
        viewModel.deleteNewsWithSource(newsId, sourceId)
        Timber.d("News Id: ${newsId}, Source Id: ${sourceId}")
//        viewModel.deleteSource(sourceId)
//        viewModel.deleteNews(newsId)
    }

    fun isExist(){

    }


}