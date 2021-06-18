package com.mwsmith3.spacex.falcon9.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.mwsmith3.spacex.R
import com.mwsmith3.spacex.databinding.ActivityFalcon9Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Falcon9Activity : AppCompatActivity() {

    private lateinit var binding: ActivityFalcon9Binding

    private val viewModel: Falcon9ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle()
        binding = ActivityFalcon9Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.recycler.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

        viewModel.state.observe(this) {
            with(binding) {
                recycler.isVisible = it is Falcon9ViewModel.State.Success
                error.isVisible = it is Falcon9ViewModel.State.Error
                loading.isVisible = it is Falcon9ViewModel.State.Loading

                if (it is Falcon9ViewModel.State.Success) {
                    val adapter = Falcon9Adapter(it.launches)
                    recycler.adapter = adapter
                }
            }
        }
    }

    private fun setTitle() {
        supportActionBar?.title = getString(R.string.falcon_9_activity_title)
    }
}
