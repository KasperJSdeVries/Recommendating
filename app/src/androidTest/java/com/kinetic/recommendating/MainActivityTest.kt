package com.kinetic.recommendating

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jakewharton.processphoenix.ProcessPhoenix
import com.kinetic.recommendating.TestLoginManager.createTestUser
import com.kinetic.recommendating.TestLoginManager.deleteTestUser
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        createTestUser()
        ProcessPhoenix.triggerRebirth(MainActivityTest.)
    }

    @After
    fun tearDown() {
        deleteTestUser()
    }

    @Test
    fun onProfileButtonClick_switchActivity() {
        onView(withId(R.id.profileNavButton)).perform(click())
        onView(withId(R.id.signOutButton)).check(matches(isDisplayed()))
    }
}