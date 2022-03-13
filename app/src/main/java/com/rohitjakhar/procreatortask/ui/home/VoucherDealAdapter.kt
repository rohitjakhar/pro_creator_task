package com.rohitjakhar.procreatortask.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rohitjakhar.procreatortask.data.model.VoucherDealModel
import com.rohitjakhar.procreatortask.databinding.ItemVoucherDealBinding

class VoucherDealAdapter :
    ListAdapter<VoucherDealModel, VoucherDealAdapter.VoucherDealVH>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoucherDealVH {
        return VoucherDealVH(
            ItemVoucherDealBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VoucherDealVH, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VoucherDealVH(private val binding: ItemVoucherDealBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: VoucherDealModel) = binding.apply {
            tvVoucherTitle.text = data.voucherTitle
            tvUserName.text = data.userName
            tvVoucherDiscount.text = "${data.voucherOffer}%"
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<VoucherDealModel>() {
            override fun areContentsTheSame(
                oldItem: VoucherDealModel,
                newItem: VoucherDealModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(
                oldItem: VoucherDealModel,
                newItem: VoucherDealModel
            ): Boolean {
                return oldItem.voucherId == newItem.voucherId
            }
        }
    }
}
