package com.mwsmith3.spacex.falcon9.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mwsmith3.spacex.falcon9.data.Falcon9Repository
import com.mwsmith3.spacex.falcon9.domain.model.Falcon9Launch
import com.mwsmith3.spacex.schedulers.FakeAppSchedulers
import io.reactivex.rxjava3.core.Single
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.DateTime
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class Falcon9ViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository = mock(Falcon9Repository::class.java)

    @After
    fun tearDown() {
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `when no response from repository, then state is Loading`() {
        `when`(repository.getFalcon9Launches()).thenReturn(Single.never())

        val viewModel = createViewModel()
        verify(repository).getFalcon9Launches()

        val actual = viewModel.state.value
        val expected = Falcon9ViewModel.State.Loading

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when error response from repository, then state is Error`() {
        `when`(repository.getFalcon9Launches()).thenReturn(Single.error(Throwable()))

        val viewModel = createViewModel()
        verify(repository).getFalcon9Launches()

        val actual = viewModel.state.value
        val expected = Falcon9ViewModel.State.Error

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `when success response from repository, then state is Success`() {
        val launches = listOf(
            Falcon9Launch(
                name = "apollo 1",
                launchDate = DateTime.now(),
                success = true,
                imageUrl = null
            )
        )
        `when`(repository.getFalcon9Launches()).thenReturn(Single.just(launches))

        val viewModel = createViewModel()
        verify(repository).getFalcon9Launches()

        val actual = viewModel.state.value
        val expected = Falcon9ViewModel.State.Success(launches)

        assertThat(actual).isEqualTo(expected)
    }

    private fun createViewModel(): Falcon9ViewModel {
        return Falcon9ViewModel(FakeAppSchedulers, repository)
    }
}
