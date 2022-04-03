package com.mvvm.task.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm.task.db.FeedEntity

import com.mvvm.task.db.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FeedViewModel @Inject constructor(private val repository: RoomRepository): ViewModel() {

    lateinit var feeddata: MutableLiveData<List<FeedEntity>>

    init {
        feeddata = MutableLiveData()
        loadRecords()
    }

    fun getRecordsObserver(): MutableLiveData<List<FeedEntity>> {

        return feeddata

    }

    fun loadRecords(){
        val list = repository.getRecords()
        feeddata.postValue(list)


    }

    fun insertRecord(feedentity: FeedEntity) {
        repository.insertRecord(feedentity)
        loadRecords()
    }
    fun updateRecord(feedentity: FeedEntity) {
        repository.updateRecord(feedentity)
        loadRecords()
    }

}