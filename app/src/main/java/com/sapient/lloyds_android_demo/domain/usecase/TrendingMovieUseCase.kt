package com.sapient.lloyds_android_demo.domain.usecase

import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.sapient.lloyds_android_demo.data.repository.MovieRepositoryImp
import com.sapient.lloyds_android_demo.domain.model.Movie
import com.sapient.lloyds_android_demo.domain.usecase.base.FlowableUseCase
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class TrendingMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepositoryImp
): FlowableUseCase<PagingData<Movie>>(){
     private var viewModelScope: CoroutineScope? = null

    fun setViewModelScope(scope: CoroutineScope){
        viewModelScope =scope
    }
        override fun buildUseCaseSingle(): Flowable<PagingData<Movie>> {
            return movieRepository.getTrendingMovie().cachedIn(viewModelScope!!)
        }

}
