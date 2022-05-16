package com.sapient.lloyds_android_demo.presentation.moviedetails


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.sapient.lloyds_android_demo.R
import com.sapient.lloyds_android_demo.waitUntilGoneProgressBar
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class MovieDetailsFragmentTest {


    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)
    @Before
    fun setup() {
        hiltAndroidRule.inject()
    }


    @Test
    fun onTextVisible(){

        waitUntilGoneProgressBar()
        onView(withId(R.id.tvDetailTitle)).check(matches(isDisplayed()))
    }
}
