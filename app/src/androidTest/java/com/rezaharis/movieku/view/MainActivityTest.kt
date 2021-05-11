package com.rezaharis.movieku.view

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.rezaharis.movieku.R
import com.rezaharis.movieku.utils.DataDummy
import com.rezaharis.movieku.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test


class MainActivityTest{
    private val movie = DataDummy.getMoviesList()
    private val tvShows = DataDummy.getTvShowsList()

    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun testMovies(){
        onView(withId(R.id.cr_rview))
            .check(ViewAssertions.matches(isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movie.size-1))
    }

    @Test
    fun testClickDetailMovies(){
        onView(withId(R.id.cr_rview))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.tv_movie))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.movie_release))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_des))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun testTvShows(){
        onView(withId(R.id.cr_tvshows))
            .check(ViewAssertions.matches(isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvShows.size-1))
    }

    @Test
    fun testClickDetailTvShows(){
        onView(withId(R.id.cr_tvshows))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.tv_tvshows))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tvshows_release))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_des_tvshows))
            .check(ViewAssertions.matches(isDisplayed()))
    }
}