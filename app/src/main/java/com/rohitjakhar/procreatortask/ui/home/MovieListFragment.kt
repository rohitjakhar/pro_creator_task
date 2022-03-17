package com.rohitjakhar.procreatortask.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import coil.load
import coil.transform.CircleCropTransformation
import com.rohitjakhar.procreatortask.R
import com.rohitjakhar.procreatortask.data.dummy_data.getOfferData
import com.rohitjakhar.procreatortask.data.dummy_data.getVoucherData
import com.rohitjakhar.procreatortask.databinding.FragmentMovieListBinding
import com.rohitjakhar.procreatortask.hide
import com.rohitjakhar.procreatortask.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlin.math.abs

@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieListViewModel by viewModels()
    private val voucherDealAdapter by lazy { VoucherDealAdapter() }
    private val movieListAdapter by lazy {
        MovieListAdapter() { movieId ->
            findNavController().navigate(
                MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(
                    movieId
                )
            )
        }
    }
    private val offerPagerAdapter by lazy {
        OfferPagerAdapter() {
            Toast.makeText(requireContext(), "Offer Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(layoutInflater, container, false)
        viewModel.getMovieList()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initVoucherDealRV()
        initOfferPager()
        initMovieListRV()
        collectUiState()
    }

    private fun initView() = binding.apply {
        ivUserIcon.load(R.drawable.user_image) {
            transformations(CircleCropTransformation())
        }
    }

    private fun initOfferPager() {
        binding.viewPagerOffer.apply {
            adapter = offerPagerAdapter
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            setPageTransformer(
                CompositePageTransformer().apply {
                    addTransformer(MarginPageTransformer(40))
                    addTransformer { page, position ->
                        val r: Float = 1 - abs(position)
                        page.scaleY = 0.85f + r * 0.15f
                    }
                }
            )
        }
        offerPagerAdapter.submitList(getOfferData())
    }

    private fun initMovieListRV() {
        binding.rvMovieList.apply {
            adapter = movieListAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
        }
    }

    private fun initVoucherDealRV() = binding.apply {
        rvVoucherDetails.apply {
            adapter = voucherDealAdapter
        }
        voucherDealAdapter.submitList(
            getVoucherData()
        )
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.movieListState.collectLatest {
                if (it.isLoading) {
                    binding.rvMovieList.hide()
                    binding.progressMovieLoad.show()
                } else {
                    binding.progressMovieLoad.hide()
                    if (it.movieList.isNotEmpty()) {
                        binding.rvMovieList.show()
                        movieListAdapter.submitList(it.movieList)
                    } else {
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
