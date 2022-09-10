package com.example.mock.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mock.database.DatabaseConstants.RESTAURANT_ID
import com.example.mock.database.DatabaseConstants.RESTAURANT_TABLE
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = RESTAURANT_TABLE)
data class Restaurant  (
    @PrimaryKey @ColumnInfo(name = RESTAURANT_ID)val id:String,
    @ColumnInfo(name = "term")
    val term:String,
    @ColumnInfo(name = "lat")
    val lat: Double,
    @ColumnInfo(name = "lon")
    val lon: Double
): Parcelable