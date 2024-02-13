package com.example.myapplication.service

import com.example.myapplication.model.response.UpcomingGuides
import retrofit2.Response
import retrofit2.http.GET

interface UpcomingGuideService {
    @GET("service/v2/upcomingGuides/")
    suspend fun getUpcomingGuides(): Response<UpcomingGuides>
}