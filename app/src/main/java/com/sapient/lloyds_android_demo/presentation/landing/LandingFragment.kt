package com.sapient.lloyds_android_demo.presentation.landing

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.sapient.lloyds_android_demo.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_landing.*
import kotlinx.android.synthetic.main.layout_error.*

@AndroidEntryPoint
class LandingFragment : Fragment(R.layout.fragment_landing) {

private lateinit var movieAdaptor: MovieAdaptor


private val landingViewModel by viewModels<LandingViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        landingViewModel.trendingMovies
            .observe(viewLifecycleOwner) {
                movieAdaptor.submitData(lifecycle, it)
            }
        movieAdaptor = MovieAdaptor()
        rvMovies.layoutManager = LinearLayoutManager(context)
        rvMovies.adapter = movieAdaptor.withLoadStateFooter(
            MovieFooterStateAdaptor{
                movieAdaptor.retry()
            }
        )

        movieAdaptor.addLoadStateListener {
            loadState ->
            swipeToRefresh.isRefreshing = loadState.source.refresh is LoadState.Loading
            layoutErrorContainer.isVisible = loadState.source.refresh is LoadState.Error
            rvMovies.isVisible = !layoutErrorContainer.isVisible
            if (loadState.source.refresh is LoadState.Error) {
                btnRetry.setOnClickListener {
                    movieAdaptor.retry()
                }

                layoutErrorContainer.isVisible = loadState.source.refresh is LoadState.Error

                val errorMessage = (loadState.source.refresh as LoadState.Error).error.message
                tvErrorMessage.text = errorMessage
            }
        }

        swipeToRefresh.setOnRefreshListener {
            landingViewModel.onRefresh()
        }
    }

}
