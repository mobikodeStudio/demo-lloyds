package com.sapient.lloyds_android_demo.presentation.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sapient.lloyds_android_demo.domain.model.Movie
import com.sapient.lloyds_android_demo.domain.model.Resource
import com.sapient.lloyds_android_demo.domain.usecase.MovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    movieDetailsUseCase: MovieDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _movie = MutableLiveData<Resource<Movie>>()
    private val compositeDisposable = CompositeDisposable()
    val movie : LiveData<Resource<Movie>> get() = _movie

    init {
        if (savedStateHandle.contains("movieId")) {
            val movieId = savedStateHandle.get<Long>("movieId")
            Timber.i("movieId: $movieId")
            if (movieId != null) {
                movieDetailsUseCase.saveMovieId(movieId)
                movieDetailsUseCase.execute(
                    onSuccess = {
                        _movie.value = Resource.Success(it)
                    },
                    onError = {
                        _movie.value = Resource.Error(null, it.message!!)
                    }
                )
            }else{
                _movie.value = Resource.Error(null,"Something went wrong")
            }

        } else {
            _movie.value = Resource.Error(null,"Something went wrong")
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
