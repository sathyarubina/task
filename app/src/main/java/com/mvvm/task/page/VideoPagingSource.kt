package com.mvvm.task.page


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mvvm.task.network.RetroServiceInstance
import com.mvvm.task.network.VideoData



import retrofit2.HttpException
import java.io.IOException


class VideoPagingSource(val retroServiceInstance: RetroServiceInstance) : PagingSource<Int, VideoData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VideoData> {


        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = retroServiceInstance.getVideoList(nextPage)
            val nextKey = if (response.items.isNotEmpty()) response.page + 1 else null
            LoadResult.Page(data = response.items,
                prevKey =null,
                nextKey = nextKey)


        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }


    }


    override fun getRefreshKey(state: PagingState<Int, VideoData>): Int? {

        return state.anchorPosition

    }
    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}