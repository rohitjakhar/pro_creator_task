package com.rohitjakhar.procreatortask.ui.movie_details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.RoundedCornersTransformation
import com.rohitjakhar.procreatortask.data.model.MovieDetailsModel
import com.rohitjakhar.procreatortask.databinding.FragmentMovieDetailsBinding
import com.rohitjakhar.procreatortask.hide
import com.rohitjakhar.procreatortask.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieDetailsViewModel by viewModels()
    private val navArgs: MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(layoutInflater, container, false)
        viewModel.getMovieDetails(navArgs.movieId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectUiState()
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.movieDetailState.collectLatest {
                if (it.isLoading) {
                    binding.progressMovieDetailsLoad.show()
                } else {
                    binding.progressMovieDetailsLoad.hide()
                    if (it.movieDetails != null) {
                        updateUI(it.movieDetails)
                    } else {
                        // TODO: Show Error Message
                    }
                }
            }
        }
    }

    private fun updateUI(movieDetails: MovieDetailsModel) = binding.apply {
        ivMovieCover.load(movieDetails.movieImage)
        ivMoviePhoto.load(movieDetails.movieImage) {
            transformations(RoundedCornersTransformation(12F))
        }
        tvMovieTitle.text = movieDetails.movieName
        tvDirectorName.text = movieDetails.movieDirector
        tvWritterName.text = movieDetails.movieWriter
        tvGenreName.text = movieDetails.movieGenre
        tvPhName.text = movieDetails.moviePH
        ratingMovie.rating = movieDetails.movieRating.toFloat() / 2
        tvMovieRating.text = "${movieDetails.movieRating}/10"
        tvMovieDuration.text = "${movieDetails.movieDuration} Min"
        tvMovieOverview.text = movieDetails.movieDescription
        Log.d("test", "genre: ${movieDetails.movieGenre}")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
