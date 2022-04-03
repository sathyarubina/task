package com.mvvm.task.network

import com.google.gson.annotations.SerializedName


data class VideoList(
    val page: Int,
    val total_pages: Int,
    @SerializedName("data") val items: List<VideoData>,
    val item: List<VideoList>
)
data class VideoData(val id: Int?, val email: String?,val first_name: String?,val last_name:String?,val avatar:String?)

