package com.example.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.GuideCellBinding
import com.example.myapplication.model.domain.GuideModel
import com.squareup.picasso.Picasso

class GuidesAdapter : RecyclerView.Adapter<GuidesAdapter.GuideCellViewHolder>() {
    private val diffUti: AsyncListDiffer<GuideModel> = AsyncListDiffer(this, GuideDiffUtilCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideCellViewHolder {
        val binding = GuideCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuideCellViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GuideCellViewHolder, position: Int) {
        holder.bind(diffUti.currentList[position])
    }

    override fun getItemCount() =
        diffUti.currentList.size

    fun updateAdapter(guideModels: List<GuideModel>) {
        diffUti.submitList(guideModels)
    }

    class GuideCellViewHolder(private val binding: GuideCellBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(guideModel: GuideModel) {
            with(binding) {
                name.text = guideModel.name
                startDate.text = guideModel.startDate
                endDate.text = guideModel.endDate
                Picasso.get()
                    .load(guideModel.icon)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(icon)
            }
        }
    }
}