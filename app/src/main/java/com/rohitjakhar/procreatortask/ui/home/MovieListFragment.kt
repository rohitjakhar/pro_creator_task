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
import androidx.viewpager2.widget.MarginPageTransformer
import coil.load
import coil.transform.CircleCropTransformation
import com.rohitjakhar.procreatortask.R
import com.rohitjakhar.procreatortask.data.dummy_data.getOfferData
import com.rohitjakhar.procreatortask.data.dummy_data.getVoucherData
import com.rohitjakhar.procreatortask.databinding.FragmentMovieListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

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
            setPageTransformer(MarginPageTransformer(80))
            setPadding(40, 0, 40, 0)
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
                    // TODO: Show Loading View
                } else {
                    if (it.movieList.isNotEmpty()) {
                        // TODO: Submit Item to Adapter
                        movieListAdapter.submitList(it.movieList)
                    } else {
                        // TODO: Show Error
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
