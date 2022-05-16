package com.sapient.lloyds_android_demo.presentation.moviedetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.sapient.lloyds_android_demo.R
import com.sapient.lloyds_android_demo.domain.model.Status
import com.sapient.lloyds_android_demo.utils.imageBaseurlOriginal
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_detials.*
import kotlinx.android.synthetic.main.loading.*

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(R.layout.fragment_movie_detials) {

    private  val viewModel: MovieDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ibBack.setOnClickListener {
            it.findNavController().popBackStack()
        }
        viewModel.movie.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.Success->{
                    showLoading(false)
                    val movie = it.data!!
                    Glide.with(ivPosterImg)
                        .load(imageBaseurlOriginal +"${movie.backdropPath}")
                        .into(ivPosterImg)
                    tvDetailOverView.text = movie.overview
                }
                Status.Error->{
                    showLoading(false)
                    it.message?.let { it1 ->
                        Snackbar.make(requireView(),
                            it1, Snackbar.LENGTH_LONG).show()
                    }
                }
                Status.Loading-> showLoading(true)
            }
        })
    }
    private fun showLoading(isShow:Boolean) {
        if (isShow) {
            loadingContainer.visibility = View.VISIBLE
        }else{
            loadingContainer.visibility = View.GONE
        }
    }
}
