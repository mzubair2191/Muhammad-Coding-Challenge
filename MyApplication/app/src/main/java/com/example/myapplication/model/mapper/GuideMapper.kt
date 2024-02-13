package com.example.myapplication.model.mapper

import com.example.myapplication.model.domain.GuideModel
import com.example.myapplication.model.response.UpcomingGuides
import javax.inject.Inject

class GuideMapper @Inject constructor(){
    fun mapToGuideModel(upcomingGuides: UpcomingGuides?): List<GuideModel>? {
        return upcomingGuides?.data?.filter { data ->
            data.objType == GUIDE
            && data.name != null
            && data.startDate != null
            && data.endDate != null
        }?.map { data ->
            GuideModel(
                name = data.name!!,
                startDate = data.startDate!!,
                endDate = data.endDate!!,
                icon = data.icon,
                url = data.url
            )
        }
    }

    // An optimized solution would have used a converter factory that would map to enum instead, this is a quick implementation
    companion object {
        private const val GUIDE = "guide"
    }
}