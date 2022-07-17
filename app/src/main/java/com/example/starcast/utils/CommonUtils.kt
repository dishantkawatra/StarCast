@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "DEPRECATION")

package com.example.starcast.utils



object CommonUtils {

    inline fun <reified T> genericCastOrNull(anything: Any): T? {
        return anything as? T
    }



}
