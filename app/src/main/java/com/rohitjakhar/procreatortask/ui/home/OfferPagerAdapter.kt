package com.rohitjakhar.procreatortask.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rohitjakhar.procreatortask.data.model.OfferModel
import com.rohitjakhar.procreatortask.databinding.ItemOfferBinding

class OfferPagerAdapter(private val offerClick: (String) -> Unit) :
    ListAdapter<OfferModel, OfferPagerAdapter.OfferPagerVH>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferPagerVH {
        return OfferPagerVH(
            ItemOfferBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OfferPagerVH, position: Int) {
        holder.bind(getItem(position))
    }

    inner class OfferPagerVH(private val binding: ItemOfferBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: OfferModel) = binding.apply {
            tvOfferName.text = data.userName
            tvOfferPerItem.text = "/${data.offerPerItem} Hari"
            tvOferRate.text = "Rp.${data.offerPrice}"
            tvOfferType.text = data.offerType
            tvOfferTitle.text = data.offerTitle
            root.setOnClickListener {
                offerClick.invoke(data.offerId)
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<OfferModel>() {
            override fun areContentsTheSame(oldItem: OfferModel, newItem: OfferModel): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: OfferModel, newItem: OfferModel): Boolean {
                return oldItem.offerId == newItem.offerId
            }
        }
    }
}
