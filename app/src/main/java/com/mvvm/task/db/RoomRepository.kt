package com.mvvm.task.db

import javax.inject.Inject

class RoomRepository @Inject constructor(private val appDao: AppDao) {

    fun getRecords(): List<FeedEntity> {
        return appDao.getRecords()
    }

    fun insertRecord(feedentity: FeedEntity) {
        appDao.insertRecord(feedentity)
    }
    fun updateRecord(feedentity: FeedEntity) {
        appDao.updateRecord(feedentity)
    }

}