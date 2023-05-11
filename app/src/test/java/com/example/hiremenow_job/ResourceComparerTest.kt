package com.example.hiremenow_job

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class ResourceComparerTest {
    private lateinit var resourceComparer: ResourceComparer

    @Before
    fun setup() {
        resourceComparer = ResourceComparer()  // Create an instance of ResourceComparer before each test
    }

    @After
    fun teardown() {
        // Perform any necessary cleanup after each test
    }

    @Test
    fun stringResourceSameAsGivenString_returnsTrue() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        // Obtain the application context for testing purposes

        val result = resourceComparer.isEqual(context, R.string.app_name, "HireMeNow")
        // Compare the string resource with the given string using the ResourceComparer class

        assertThat(result).isTrue()
        // Assert that the result is true, indicating that the string resource and given string are equal
    }

    @Test
    fun stringResourceDifferentAsGivenString_returnsFalse() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        // Obtain the application context for testing purposes

        val result = resourceComparer.isEqual(context, R.string.app_name, "Hello")
        // Compare the string resource with the given string using the ResourceComparer class

        assertThat(result).isFalse()
        // Assert that the result is false, indicating that the string resource and given string are different
    }
}
