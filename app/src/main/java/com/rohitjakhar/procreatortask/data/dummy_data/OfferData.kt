package com.rohitjakhar.procreatortask.data.dummy_data

import com.rohitjakhar.procreatortask.data.model.OfferModel

fun getOfferData() = listOf<OfferModel>(
    OfferModel(
        offerId = "1",
        offerPrice = 20000,
        offerTitle = "Nonton Dimana Saja, Kapan Saja",
        offerPerItem = 30,
        offerType = "Klik Disini",
        userName = "Mulai"
    ),
    OfferModel(
        offerId = "2",
        offerPrice = 28000,
        offerTitle = "Nonton Dimana Saja, Kapan Saja",
        offerPerItem = 10,
        offerType = "Klik Disini",
        userName = "Mulai"
    ),
    OfferModel(
        offerId = "3",
        offerPrice = 25000,
        offerTitle = "Nonton Dimana Saja, Kapan Saja",
        offerPerItem = 20,
        offerType = "Klik Disini",
        userName = "Mulai"
    ),
    OfferModel(
        offerId = "4",
        offerPrice = 30000,
        offerTitle = "Nonton Dimana Saja, Kapan Saja",
        offerPerItem = 30,
        offerType = "Klik Disini",
        userName = "Mulai"
    ),
    OfferModel(
        offerId = "5",
        offerPrice = 2000,
        offerTitle = "Nonton Dimana Saja, Kapan Saja",
        offerPerItem = 50,
        offerType = "Klik Disini",
        userName = "Mulai"
    )
)