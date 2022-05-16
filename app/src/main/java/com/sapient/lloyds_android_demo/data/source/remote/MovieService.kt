package com.sapient.lloyds_android_demo.data.source.remote

import com.sapient.lloyds_android_demo.domain.model.Movie
import com.sapient.lloyds_android_demo.domain.model.Movies
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieService {
    @GET("trending/all/day")
    fun getTrendingMovie(@Query("page") page: Int) :Single<Movies>

    @GET("movie/{movieId}")
    fun getMovie(@Path("movieId") movieId: Long) :Single<Movie>
}
