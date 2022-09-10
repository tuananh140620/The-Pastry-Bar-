package com.example.mock.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.mock.database.DatabaseConstants.ACCESSORY_TABLE
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = ACCESSORY_TABLE)
data class Accessory  (
    @ColumnInfo(name = "branchName")
    val branchName:String,
    @ColumnInfo(name = "cell")
    val cell:String,
    @ColumnInfo(name = "address")
    val address:String,
    @ColumnInfo(name = "businessTime")
    val businessTime: String

): Parcelable