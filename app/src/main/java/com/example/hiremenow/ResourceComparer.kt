package com.example.hiremenow

import android.content.Context

class ResourceComparer {
    // This function compares a string with the string resource ID passed as parameter
    // It returns true if they are equal, false otherwise

    fun isEqual(context:Context, resId:Int,string: String):Boolean{
        // Get the string from the resources using the provided resource ID
        // Compare the resource string with the string passed as parameter and return the result
        return context.getString(resId) == string
    }
}