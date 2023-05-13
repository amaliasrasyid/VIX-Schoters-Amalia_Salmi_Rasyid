package com.amaliarasyid.simplenewsapp.ui.news.detail

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.navArgs
import com.amaliarasyid.simplenewsapp.R
import com.amaliarasyid.simplenewsapp.databinding.FragmentDetailBinding
import com.amaliarasyid.simplenewsapp.utils.Constant
import com.amaliarasyid.simplenewsapp.utils.convertDate
import com.amaliarasyid.simplenewsapp.utils.setImage
import java.text.SimpleDateFormat
import java.util.Locale

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()

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
            with(args.extraArticleItem){
                if(this.urlToImage != null) imvNews.setImage(this.urlToImage)
                tvAuthor.text = this.author
                tvTitle.text = this.title
                tvDescription.text = this.description
                tvPublishedDate.convertDate(this.publishedAt!!,requireContext())

                ViewCompat.setTransitionName(tvAuthor,"author_${this.publishedAt}")
                ViewCompat.setTransitionName(tvTitle,"title_${this.publishedAt}")
                ViewCompat.setTransitionName(imvNews,"image_${this.publishedAt}")
                ViewCompat.setTransitionName(tvDescription,"desc_${this.publishedAt}")
            }
        }
    }

}