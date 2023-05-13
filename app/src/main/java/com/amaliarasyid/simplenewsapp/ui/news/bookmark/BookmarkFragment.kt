package com.amaliarasyid.simplenewsapp.ui.news.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.amaliarasyid.simplenewsapp.data.entities.News
import com.amaliarasyid.simplenewsapp.data.entities.NewsWithSource
import com.amaliarasyid.simplenewsapp.data.remote.response.ArticlesItem
import com.amaliarasyid.simplenewsapp.databinding.FragmentBookmarkBinding
import com.amaliarasyid.simplenewsapp.databinding.FragmentNewsBinding
import com.amaliarasyid.simplenewsapp.databinding.ItemNewsBinding
import com.amaliarasyid.simplenewsapp.ui.NewsWithSource.NewsAdapter
import com.amaliarasyid.simplenewsapp.ui.news.NewsFragmentDirections
import com.amaliarasyid.simplenewsapp.ui.news.NewsViewModel
import com.amaliarasyid.simplenewsapp.utils.convertToNewsWithSourceEntities
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class BookmarkFragment : Fragment() {
    private lateinit var binding: FragmentBookmarkBinding
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
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
                        val toDetail =BookmarkFragmentDirections.actionNavigationBookmarkToDetailFragment(data)
                        findNavController().navigate(toDetail,extras)
                    }
                }
            }
        })
        loadData()
    }

    private fun loadData() {
        viewModel.loadBookmarkedNews().observe(viewLifecycleOwner){result ->
            if(result != null ){
                newsAdapter.updateNewsListItem(result)
                Timber.d("data bookmark:${result.toString()}")
            }
        }
    }

}
