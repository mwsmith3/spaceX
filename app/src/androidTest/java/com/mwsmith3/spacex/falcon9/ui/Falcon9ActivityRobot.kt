package com.mwsmith3.spacex.falcon9.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.mwsmith3.spacex.R

class Falcon9ActivityRobot {

    fun checkErrorShown(): Falcon9ActivityRobot {
        onView(withId(R.id.error))
            .check(
                matches(
                    isDisplayed()
                )
            )
        return this
    }

    fun checkLoadingShown(): Falcon9ActivityRobot {
        onView(withId(R.id.loading))
            .check(
                matches(
                    isDisplayed()
                )
            )
        return this
    }

    fun checkRecyclerViewShown(): Falcon9ActivityRobot {
        onView(withId(R.id.recycler))
            .check(
                matches(
                    isDisplayed()
                )
            )
        return this
    }
}
