package com.mwsmith3.spacex.falcon9.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mwsmith3.spacex.falcon9.data.Falcon9Repository
import com.mwsmith3.spacex.falcon9.domain.model.Falcon9Launch
import com.mwsmith3.spacex.schedulers.AppSchedulers
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class Falcon9ViewModel @Inject constructor(
    private val schedulers: AppSchedulers,
    private val falcon9Repository: Falcon9Repository
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _state = MutableLiveData<State>(State.Loading)
    val state: LiveData<State> = _state

    init {
        load()
    }

    private fun load() {
        disposables.add(
            falcon9Repository.getFalcon9Launches()
                .subscribeOn(schedulers.io)
                .observeOn(schedulers.main)
                .subscribe({
                    _state.value = State.Success(it)
                }, {
                    _state.value = State.Error(it.message.toString())
                })
        )
    }

    sealed class State {
        object Loading : State()
        data class Error(
            val message: String
        ) : State()

        data class Success(
            val launches: List<Falcon9Launch>
        ) : State()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
