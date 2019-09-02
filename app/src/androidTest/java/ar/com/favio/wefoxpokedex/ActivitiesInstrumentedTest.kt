package ar.com.favio.wefoxpokedex

import android.app.Activity
import android.content.Context
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.AndroidJUnit4
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import ar.com.favio.wefoxpokedex.screens.HomeActivity
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ActivitiesInstrumentedTest{
    /**
     * Use [ActivityScenario] to create and launch of the activity.
     */
    @Before
    fun launchActivity() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    var currentActivity: Activity? = null
    fun getActivityInstance(): Activity? {
        getInstrumentation().runOnMainSync {
            val resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED)
            if (resumedActivities.iterator().hasNext())
                currentActivity = resumedActivities.iterator().next()
        }
        return currentActivity
    }
    @Test
    fun should_navigate_between_tabs() {
        onView(withId(R.id.backpack_recyclerview)).inRoot(withDecorView(not(currentActivity?.window?.decorView)))
            .check(matches(isDisplayed()))
        onView(withId(R.id.navigation_search)).perform(click())
        onView(withText( getApplicationContext<Context>().getString(R.string.search_pokemon))).inRoot(withDecorView(not(currentActivity?.window?.decorView)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun should_performSearch_and_showResults() {
        var expectedResult = getApplicationContext<Context>().getString(R.string.search_pokemon)
        onView(withId(R.id.navigation_search)).perform(click())
        onView(withId(R.id.button_leave)).perform(click())
        expectedResult = getApplicationContext<Context>().getString(R.string.catch_pokemon)
        onView(withText(expectedResult)).inRoot(withDecorView(not(currentActivity?.window?.decorView)))
            .check(matches(isDisplayed()))
    }

}
