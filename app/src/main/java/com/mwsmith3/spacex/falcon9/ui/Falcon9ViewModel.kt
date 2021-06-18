package com.mwsmith3.spacex.falcon9.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mwsmith3.spacex.falcon9.data.Falcon9Repository
import com.mwsmith3.spacex.falcon9.domain.model.Falcon9Launch
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class Falcon9ViewModel @Inject constructor(
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
