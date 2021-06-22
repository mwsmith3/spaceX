package com.mwsmith3.spacex.falcon9.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mwsmith3.spacex.falcon9.data.FakeFalcon9Repository
import com.mwsmith3.spacex.falcon9.data.Falcon9Repository
import com.mwsmith3.spacex.falcon9.domain.model.Falcon9Launch
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.reactivex.rxjava3.core.Single
import org.joda.time.DateTime
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class Falcon9ActivityTest {

    private lateinit var scenario: ActivityScenario<Falcon9Activity>

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: Falcon9Repository
    private val fakeFalcon9Repository: FakeFalcon9Repository
        get() = repository as FakeFalcon9Repository

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun cleanup() {
        scenario.close()
    }

    @Test
    fun whenRepositoryIsLoading_thenLoadingShown() {
        fakeFalcon9Repository.response = Single.never()
        scenario = launchActivity()

        Falcon9ActivityRobot()
            .checkLoadingShown()
    }

    @Test
    fun whenRepositoryResponseIsError_thenErrorShown() {
        fakeFalcon9Repository.response = Single.error(Exception())
        scenario = launchActivity()

        Falcon9ActivityRobot()
            .checkErrorShown()
    }

    @Test
    fun whenRepositoryReturnsAResponse_thenListOfLaunchesShown() {
        fakeFalcon9Repository.response = Single.just(LAUNCHES)
        scenario = launchActivity()

        Falcon9ActivityRobot()
            .checkRecyclerViewShown()
        // TODO check data
    }

    companion object {
        val LAUNCHES = List(10) {
            createLaunch(it)
        }

        private fun createLaunch(i: Int): Falcon9Launch {
            return Falcon9Launch(
                name = "name-$i",
                launchDate = DateTime.now().minusDays(i),
                success = i % 2 == 0,
                imageUrl = null
            )
        }
    }
}
