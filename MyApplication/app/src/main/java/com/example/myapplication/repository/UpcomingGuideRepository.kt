package com.example.myapplication.repository

import com.example.myapplication.model.domain.GuideModel
import com.example.myapplication.model.mapper.GuideMapper
import com.example.myapplication.service.UpcomingGuideService
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class UpcomingGuideRepository @Inject constructor(
    private val upcomingGuideService: UpcomingGuideService,
    private val guideMapper: GuideMapper
){
    suspend fun getUpcomingGuides(): List<GuideModel>? {
        val upcomingGuides = upcomingGuideService.getUpcomingGuides().body()
        return guideMapper.mapToGuideModel(upcomingGuides)
    }
}