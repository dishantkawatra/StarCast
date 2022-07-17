package com.example.starcast.utils

import android.view.View

fun View?.clickable(condition: Boolean) {
    this?.isEnabled = condition
    this?.isClickable =condition
}

