package com.example.circularprogressbar

import android.content.Context
import androidx.core.content.ContextCompat

class ResourceUtils {
    companion object {
        fun getColor(context: Context, colorId: Int): Int {
            return ContextCompat.getColor(context, colorId)
        }
    }

}
