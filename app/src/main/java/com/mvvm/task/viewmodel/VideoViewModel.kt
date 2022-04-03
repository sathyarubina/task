package com.mvvm.task.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mvvm.task.network.RetroServiceInstance
import com.mvvm.task.network.VideoData
import com.mvvm.task.page.VideoPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(private val retroServiceInstance: RetroServiceInstance): ViewModel() {

    fun getvideoList(): Flow<PagingData<VideoData>> {
        return Pager (config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = {VideoPagingSource(retroServiceInstance)}).flow.cachedIn(viewModelScope)
    }
}