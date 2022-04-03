package com.mvvm.task.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "feed", indices = [Index(value = ["roomname"], unique = true)])
class FeedEntity (

    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int= 0,
    @ColumnInfo(name = "roomname")val roomname: String,
    @ColumnInfo(name = "islive")val islive: Boolean,
    @ColumnInfo(name = "time")val time: Long

    //time in milesecond

)