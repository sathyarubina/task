package com.mvvm.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mvvm.task.R
import com.mvvm.task.databinding.VideoRowBinding
import com.mvvm.task.network.VideoData

class VideoPageAdapter(): PagingDataAdapter<VideoData, VideoPageAdapter.ViewHolder>(DiffUtilCallback()) {

    private lateinit var binding: VideoRowBinding

    override fun onBindViewHolder(holder: VideoPageAdapter.ViewHolder, position: Int) {
       holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.video_row,
            parent,
            false
        )
        return ViewHolder(binding)


    }

    class ViewHolder(private val binding: VideoRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: VideoData) {
            binding.name.text=(data.first_name + data.last_name)
            binding.email.text=(data.email)
            Glide.with(binding.imageView)
                .load(data.avatar)
                .into(binding.imageView)
        }
    }

    class  DiffUtilCallback: DiffUtil.ItemCallback<VideoData>() {
        override fun areItemsTheSame(oldItem: VideoData, newItem: VideoData): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: VideoData, newItem: VideoData): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.id == newItem.id
        }

    }

}