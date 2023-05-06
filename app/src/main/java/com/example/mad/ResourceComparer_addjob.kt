package com.example.mad

import android.content.Context

class ResourceComparer_addjob {

    fun isEqual(context: Context, resId: Int, string: String):Boolean{
        return context.getString(resId) == string
    }
}