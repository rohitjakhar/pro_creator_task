package com.rohitjakhar.procreatortask.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.rohitjakhar.procreatortask.data.model.VoucherDealModel
import com.rohitjakhar.procreatortask.databinding.FragmentMovieListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieListViewModel by viewModels()
    private val voucherDealAdapter by lazy { VoucherDealAdapter() }

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
        initVoucherDealRV()
        collectUiState()
    }

    private fun initVoucherDealRV() = binding.apply {
        rvVoucherDetails.apply {
            adapter = voucherDealAdapter
        }
        voucherDealAdapter.submitList(
            listOf(
                VoucherDealModel(
                    voucherId = "123",
                    voucherTitle = "New Offer",
                    voucherOffer = 20,
                    userName = "Rohit"
                ),
                VoucherDealModel(
                    voucherId = "1234",
                    voucherTitle = "New Offer",
                    voucherOffer = 25,
                    userName = "Rohit"
                ),
                VoucherDealModel(
                    voucherId = "1235",
                    voucherTitle = "New Offer",
                    voucherOffer = 30,
                    userName = "Rohit"
                ),
                VoucherDealModel(
                    voucherId = "1236",
                    voucherTitle = "New Offer",
                    voucherOffer = 10,
                    userName = "Rohit"
                )
            )
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
