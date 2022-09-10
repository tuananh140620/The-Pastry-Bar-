package com.example.mock.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mock.database.DatabaseConstants.USER_ID
import com.example.mock.database.DatabaseConstants.USER_TABLE
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = USER_TABLE)
data class User(
    @PrimaryKey @ColumnInfo(name = USER_ID) val uid:String,
    @ColumnInfo(name = "username")
    val username:String,
    @ColumnInfo(name = "chipAmount")
    val chipAmount:Int
): Parcelable