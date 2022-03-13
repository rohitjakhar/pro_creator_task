package com.rohitjakhar.procreatortask.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import coil.transform.Transformation
import com.rohitjakhar.procreatortask.data.model.MoviesModel
import com.rohitjakhar.procreatortask.databinding.ItemMovieBinding

class MovieListAdapter(private val onMovieClick: (String) -> Unit) :
    ListAdapter<MoviesModel, MovieListAdapter.MovieListVH>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListVH {
        return MovieListVH(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieListVH, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieListVH(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MoviesModel) = binding.apply {
            ivMoviePoster.load(
                data.moviePoster
            ) {
                transformations(RoundedCornersTransformation(16.0F))
            }
            ratingBarMovie.rating = data.movieRating.toFloat() / 2
            tvMovieTitle.text = data.movieName
            root.setOnClickListener {
                onMovieClick.invoke(data.movieId)
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<MoviesModel>() {
            override fun areContentsTheSame(oldItem: MoviesModel, newItem: MoviesModel): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: MoviesModel, newItem: MoviesModel): Boolean {
                return oldItem.movieId == newItem.movieId
            }
        }
    }
}
