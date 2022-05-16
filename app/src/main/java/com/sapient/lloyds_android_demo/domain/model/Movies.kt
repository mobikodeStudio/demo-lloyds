package com.sapient.lloyds_android_demo.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movies(
    val page: Long,

    @Json(name = "total_pages")
    val totalPage: Long,

    @Json(name = "total_results")
    val totalResults: Long,

    val results: List<Movie>
)

@JsonClass(generateAdapter = true)
data class Movie(

    @Json(name = "original_language")
    val originalLanguage : String?,

    @Json(name = "original_title")
    val originalTitle : String?,

    @Json(name = "poster_path")
    val posterPath : String?,

    @Json(name = "video")
    val isVideo : Boolean?,

    @Json(name = "vote_average")
    val voteAverage : Double?,

    val id : Long?,

    @Json(name = "overview")
    val overview : String?,

    @Json(name = "release_date")
    val releaseDate : String?,

    @Json(name = "vote_count")
    val voteCount : Int?,

    val title : String?,

    @Json(name = "adult")
    val isAdult : Boolean?,

    @Json(name = "backdrop_path")
    val backdropPath : String?,

    val popularity : Double?,

    @Json(name = "media_type")
    val mediaType : String?
)
