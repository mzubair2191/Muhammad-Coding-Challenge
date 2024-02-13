package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityGuideBinding
import com.example.myapplication.model.domain.GuideModel
import com.example.myapplication.ui.adapter.GuidesAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuideActivity : AppCompatActivity() {
    private val viewModel: GuidesViewModel by viewModels()
    private var _binding: ActivityGuideBinding? = null
    private val binding get() = _binding!!
    private val guideAdapter = GuidesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityGuideBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@GuideActivity)
            adapter = guideAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this@GuideActivity,
                    DividerItemDecoration.VERTICAL
                )
            )

        }
        viewModel.onInput(GuidesViewModel.Input.SCREEN_LAUNCHED)
        viewModel.viewState.observe(this) { viewState ->
            handleViewState(viewState = viewState)
        }
    }

    private fun handleViewState(viewState: GuidesViewModel.ViewState) {
        when (viewState) {
            GuidesViewModel.ViewState.ERROR -> displayError()
            GuidesViewModel.ViewState.LOADING -> loading()
            is GuidesViewModel.ViewState.READY -> displayContent(guideModels = viewState.guideModels)
        }
    }

    private fun loading() {
        with(binding) {
            recyclerView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }


    private fun displayContent(guideModels: List<GuideModel>) {
        with(binding) {
            recyclerView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
        guideAdapter.updateAdapter(guideModels)
    }

    private fun displayError() {
        binding.recyclerView.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        Snackbar.make(binding.root, R.string.something_went_wrong, Snackbar.LENGTH_LONG)
            .setAction(R.string.retry) {
                viewModel.onInput(GuidesViewModel.Input.SCREEN_LAUNCHED)
            }
            .show()
    }
}