package com.example.myapplication.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.model.domain.GuideModel

object GuideDiffUtilCallback : DiffUtil.ItemCallback<GuideModel>() {

    override fun areItemsTheSame(
        oldItem: GuideModel,
        newItem: GuideModel
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: GuideModel,
        newItem: GuideModel
    ): Boolean {
        return oldItem == newItem
    }
}