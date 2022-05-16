package com.sapient.lloyds_android_demo.domain.usecase

import com.sapient.lloyds_android_demo.data.repository.MovieRepositoryImp
import com.sapient.lloyds_android_demo.domain.model.Movie
import com.sapient.lloyds_android_demo.domain.usecase.base.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(
    private val movieRepositoryImp: MovieRepositoryImp
): SingleUseCase<Movie>(){
    private var movieId: Long = 0

    fun saveMovieId(id: Long) {
        movieId = id
    }
    override fun buildUseCaseSingle(): Single<Movie> {
            return movieRepositoryImp.getMovie(movieId)
        }

}
