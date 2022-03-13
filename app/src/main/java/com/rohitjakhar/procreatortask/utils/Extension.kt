package com.rohitjakhar.procreatortask

import android.view.View
import kotlin.properties.Delegates

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun String.toImageLink(): String {
    if (this.isNotEmpty()) {
        return "https://image.tmdb.org/t/p/w200/$this"
    } else {
        return "https://via.placeholder.com/200?text=No%20Image"
    }
}