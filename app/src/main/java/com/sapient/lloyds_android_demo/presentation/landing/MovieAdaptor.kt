package com.sapient.lloyds_android_demo.presentation.landing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sapient.lloyds_android_demo.R
import com.sapient.lloyds_android_demo.domain.model.Movie
import com.sapient.lloyds_android_demo.utils.imageBaseurl
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdaptor: PagingDataAdapter<Movie,MovieAdaptor.MovieViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let { holder.bind(it) }

    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(movie: Movie) {
            itemView.setOnClickListener{
                val direction = LandingFragmentDirections
                    .actionLandingFragmentToMovieDetialsFragment(movie.id!!)
                it.findNavController().navigate(direction)
            }
            itemView.apply {
                Glide.with(ivPoster)
                    .load(imageBaseurl+"${movie.posterPath}")
                    .into(ivPoster)

                tvTitle.text = movie.title
                tvReleaseDate.text = movie.releaseDate.toString()
                tvOverView.text = movie.overview
            }
        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
               val inflater =  LayoutInflater.from(parent.context)
                val itemView = inflater.inflate(R.layout.item_movie, parent, false)
                return MovieViewHolder(itemView)
            }
        }
    }
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }
}
