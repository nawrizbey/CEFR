package com.imax.cefr.fragments.teacher.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.imax.cefr.data.models.LiveVideoDataClass
import com.imax.cefr.databinding.ItemScheduledLiveStreamBinding

class LiveVideDataCallback : DiffUtil.ItemCallback<LiveVideoDataClass>() {
    override fun areItemsTheSame(
        oldItem: LiveVideoDataClass,
        newItem: LiveVideoDataClass
    ) = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: LiveVideoDataClass,
        newItem: LiveVideoDataClass
    ) = oldItem.id == newItem.id
}

class PinnedLiveStreamsAdapter :
    ListAdapter<LiveVideoDataClass, PinnedLiveStreamsAdapter.ViewHolder>(LiveVideDataCallback()) {

    inner class ViewHolder(private val binding: ItemScheduledLiveStreamBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val data = getItem(position)
            binding.tvAddOne.text = "Tema ati: ${data.id}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemScheduledLiveStreamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)
}
