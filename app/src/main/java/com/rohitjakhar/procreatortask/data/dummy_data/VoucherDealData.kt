package com.rohitjakhar.procreatortask.data.dummy_data

import com.rohitjakhar.procreatortask.data.model.VoucherDealModel

fun getVoucherData() = listOf(
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
