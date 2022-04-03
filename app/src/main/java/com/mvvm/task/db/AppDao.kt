package com.mvvm.task.db

import androidx.room.*

@Dao
interface AppDao {

    @Query("SELECT * FROM feed ORDER BY id DESC")
    fun getRecords(): List<FeedEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecord(feedentity: FeedEntity):Long

    @Update
    fun updateRecord(feedentity: FeedEntity)

}