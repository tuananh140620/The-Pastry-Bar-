package com.example.mock.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mock.database.DatabaseConstants
import com.example.mock.database.DatabaseConstants.FOOD_ID
import com.example.mock.database.DatabaseConstants.FOOD_TABLE
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = FOOD_TABLE)
data class Food (
    @PrimaryKey @ColumnInfo(name = FOOD_ID) var id:String,
    @ColumnInfo(name = "name")
    var name:String,
    @ColumnInfo(name = "amount")
    val amount:Int,
    @ColumnInfo(name = "description")
    val description:String,
    @ColumnInfo(name = "available")
    val available:Boolean,
    @ColumnInfo(name = "image")
    val image:String,
    @ColumnInfo(name = "discount")
    val discount:Int,
    @ColumnInfo(name = "ordertimes")
    val ordertimes:Int,
    @ColumnInfo(name = "price")
    val price:Double,
    @ColumnInfo(name = "sort")
    val sort:String
)  : Parcelable