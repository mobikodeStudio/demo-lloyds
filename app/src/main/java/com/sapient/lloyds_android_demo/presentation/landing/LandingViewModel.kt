package com.sapient.lloyds_android_demo.presentation.landing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.sapient.lloyds_android_demo.domain.model.Movie
import com.sapient.lloyds_android_demo.domain.usecase.TrendingMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val trendingMovieUseCase: TrendingMovieUseCase
) : ViewModel() {


    private val _trendingMovies = MutableLiveData<PagingData<Movie>>()
    val trendingMovies: LiveData<PagingData<Movie>> get() = _trendingMovies

    init {
        onGetTrendingMovie()
    }


    fun onRefresh() {
        onGetTrendingMovie()
    }

    private fun onGetTrendingMovie() {
        trendingMovieUseCase.setViewModelScope(viewModelScope)
        trendingMovieUseCase.execute(

            onSuccess = {
                _trendingMovies.value = it
            },
            onError = {
                it.printStackTrace()
            }
        )
    }
}
