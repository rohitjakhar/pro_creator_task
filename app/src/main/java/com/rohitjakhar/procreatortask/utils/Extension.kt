package com.rohitjakhar.procreatortask

import android.view.View
import kotlin.properties.Delegates

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun Delegates.nullOnDestroy() {

}