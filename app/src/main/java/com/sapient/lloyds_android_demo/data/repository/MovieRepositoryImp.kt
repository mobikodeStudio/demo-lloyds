package com.sapient.lloyds_android_demo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.sapient.lloyds_android_demo.data.source.remote.MovieService
import com.sapient.lloyds_android_demo.domain.model.Movie
import com.sapient.lloyds_android_demo.domain.repository.MovieRepository
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRepositoryImp @Inject constructor(
    private val movieService: MovieService,
    private val moviePagingSource: MoviePagingSource
):MovieRepository{
    override fun getTrendingMovie(): Flowable<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 1
            ),
            pagingSourceFactory = {moviePagingSource}
        ).flowable
    }

    override fun getMovie(movieId: Long): Single<Movie> {
        return  movieService.getMovie(movieId)
    }


}
