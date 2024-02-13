package com.example.myapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapplication.model.domain.GuideModel
import com.example.myapplication.repository.UpcomingGuideRepository
import com.example.myapplication.ui.GuidesViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GuidesViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val upcomingGuideRepository = mockk<UpcomingGuideRepository>()

    private val viewModel = GuidesViewModel(
        upcomingGuideRepository = upcomingGuideRepository,
        dispatcher = UnconfinedTestDispatcher()
    )

    @Test
    fun `given a valid list of guides, ensure that a READY ViewState is returned`() {
        coEvery { upcomingGuideRepository.getUpcomingGuides() } returns listOf(
            GuideModel(
                name = "name",
                startDate = "startDate",
                endDate = "endDate",
                icon = "icon",
                url = "url"
            )
        )
        viewModel.onInput(GuidesViewModel.Input.SCREEN_LAUNCHED)
        val value = viewModel.viewState.value
        assertEquals(GuidesViewModel.ViewState.READY::class.java, value!!.javaClass)
    }

    @Test
    fun `given a invalid list of guides, ensure that a ERROR ViewState is returned`() {
        coEvery { upcomingGuideRepository.getUpcomingGuides() } returns null
        viewModel.onInput(GuidesViewModel.Input.SCREEN_LAUNCHED)
        val value = viewModel.viewState.value
        assertEquals(GuidesViewModel.ViewState.ERROR::class.java, value!!.javaClass)
    }
}