package com.kinetic.recommendating

import android.os.SystemClock.sleep
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.kinetic.recommendating.TestLoginManager.createTestUser
import com.kinetic.recommendating.TestLoginManager.deleteTestUser
import com.kinetic.recommendating.TestLoginManager.isLoggedIn
import com.kinetic.recommendating.TestLoginManager.signOut
import com.kinetic.recommendating.TestLoginManager.testEmail
import com.kinetic.recommendating.TestLoginManager.testPassword
import org.junit.After
import org.junit.Rule
import org.junit.Test

class LoginActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @After
    fun tearDown() {
        deleteTestUser()
    }

    private fun fillInAccountDetails() {
        onView(withId(R.id.loginEmailField)).perform(typeText(testEmail), closeSoftKeyboard())
        onView(withId(R.id.loginPasswordField)).perform(typeText(testPassword), closeSoftKeyboard())
    }

    @Test
    fun onSignInButtonClickedWithCorrectCredentials_signInAndReturnToMainPage() {
        createTestUser()
        signOut()
        fillInAccountDetails()
        onView(withId(R.id.signInButton)).perform(click())
        check(isLoggedIn())
    }

    @Test
    fun onSignUp() {
    }
}