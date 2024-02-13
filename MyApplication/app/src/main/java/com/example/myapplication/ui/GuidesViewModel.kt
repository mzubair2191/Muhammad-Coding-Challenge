package com.example.myapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.di.DispatcherModule
import com.example.myapplication.model.domain.GuideModel
import com.example.myapplication.repository.UpcomingGuideRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuidesViewModel @Inject constructor(
    private val upcomingGuideRepository: UpcomingGuideRepository,
    @DispatcherModule.DefaultDispatcher
    private val dispatcher: CoroutineDispatcher
): ViewModel() {
    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    sealed class Input {
        data object SCREEN_LAUNCHED: Input()
    }

    sealed class ViewState {
        data object LOADING : ViewState()
        class READY(val guideModels: List<GuideModel>) : ViewState()
        data object ERROR : ViewState()
    }

    fun onInput(input: Input) {
        when (input) {
            Input.SCREEN_LAUNCHED -> getUpcomingGuides()
        }
    }

    private fun getUpcomingGuides() {
        viewModelScope.launch(dispatcher) {
            _viewState.postValue(ViewState.LOADING)
            try {
                val guide = upcomingGuideRepository.getUpcomingGuides()
                    ?: throw IllegalStateException("List of guides was null")
                _viewState.postValue(ViewState.READY(guide))
            } catch (ex: Exception) {
                _viewState.postValue(ViewState.ERROR)
            }
        }
    }
}