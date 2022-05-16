package com.sapient.lloyds_android_demo.data.repository

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.sapient.lloyds_android_demo.data.source.remote.MovieService
import com.sapient.lloyds_android_demo.domain.model.Movie
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviePagingSource @Inject constructor(private val movieService: MovieService) :
    RxPagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Movie>> {
        val page = params.key?:1
        return movieService.getTrendingMovie(page)
            .subscribeOn(Schedulers.io())
            .map {
                LoadResult.Page(data = it.results,
                prevKey = if (page == 1) null else page -1,
                    nextKey = if (page == it.totalPage.toInt()) null else it.page.toInt() +1
                ) as LoadResult<Int, Movie>
            }
            .onErrorReturn{LoadResult.Error(it)}
    }
}
