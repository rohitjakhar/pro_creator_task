package com.rohitjakhar.procreatortask.data.model

data class OfferModel(
    val offerId: String,
    val offerPrice: Int,
    val offerTitle: String,
    val offerPerItem: Int,
    val offerType: String,
    val userName: String
)
