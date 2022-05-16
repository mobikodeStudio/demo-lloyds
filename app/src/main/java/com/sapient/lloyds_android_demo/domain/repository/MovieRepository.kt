package com.sapient.lloyds_android_demo.domain.repository

import androidx.paging.PagingData
import com.sapient.lloyds_android_demo.domain.model.Movie
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single


interface MovieRepository {

    fun getTrendingMovie(): Flowable<PagingData<Movie>>

    fun getMovie(movieId: Long): Single<Movie>

}
