package com.amaliarasyid.simplenewsapp.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.amaliarasyid.simplenewsapp.R
import com.amaliarasyid.simplenewsapp.data.entities.News
import com.amaliarasyid.simplenewsapp.data.entities.NewsWithSource
import com.amaliarasyid.simplenewsapp.data.remote.response.ArticlesItem
import com.amaliarasyid.simplenewsapp.databinding.FragmentNewsBinding
import com.amaliarasyid.simplenewsapp.databinding.ItemNewsBinding
import com.amaliarasyid.simplenewsapp.ui.NewsWithSource.NewsAdapter
import com.amaliarasyid.simplenewsapp.utils.Constant.API_KEY
import com.amaliarasyid.simplenewsapp.utils.convertToNewsWithSourceEntities
import com.amaliarasyid.simplenewsapp.utils.mySnackBar
import com.amaliasrayid.storyapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareView()
    }

    private fun prepareView() {
        newsAdapter = NewsAdapter(requireContext())

        binding.recyclerview.apply{
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            this.adapter = newsAdapter
        }
        newsAdapter.setOnItemCallback(object: NewsAdapter.OnItemCallback{
            override fun onItemClicked(binding: ItemNewsBinding, data: NewsWithSource) {
                with(binding){
                        with(data.news){
                            val extras = FragmentNavigatorExtras(
                                tvItemAuthor to "author_${this.publishedAt}",
                                tvItemTitle to "title_${this.publishedAt}",
                                imgItemPoster to "image_${this.publishedAt}",
                                tvItemDescription to "desc_${this.publishedAt}"
                            )
                            val toDetail = NewsFragmentDirections.actionNavigationNewsToDetailFragment(data)
                            findNavController().navigate(toDetail,extras)
                        }
                }
            }
        })
        observeData()
    }

    private fun observeData() {
        val token = API_KEY
        viewModel.getTopHeadlines(token).observe(viewLifecycleOwner){ result ->
            Timber.d("STATUS: ${result.status}")
            when(result.status){
                Status.LOADING -> loader(true)
                Status.SUCCESS -> {
                    loader(false)
                    if(result.data != null){
                        newsAdapter.updateNewsListItem(convertToNewsWithSourceEntities(result.data))
                    }else{
                        with(binding.itemMessage){
                            this.root.visibility = View.VISIBLE
                            this.imgMessage.setImageResource(R.drawable.ic_empty_box)
                            this.tvMessage.text = result.message
                        }
                    }
                }
                Status.ERROR -> {
                    loader(false)
                    with(binding.itemMessage){
                        this.root.visibility = View.VISIBLE
                        this.imgMessage.setImageResource(R.drawable.ic_error)
                        this.tvMessage.text = result.message
                    }
                }
            }
        }
    }

    private fun loader(state: Boolean) {
        with(binding) {
            if (state) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }
}
