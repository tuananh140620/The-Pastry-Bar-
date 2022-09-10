package com.example.mock.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.mock.database.DatabaseConstants.ORDER_ID
import com.example.mock.database.DatabaseConstants.ORDER_TABLE

@Entity(tableName = ORDER_TABLE,
    indices = [Index(value = [ORDER_ID], unique = true)])
data class Order (
    @PrimaryKey @ColumnInfo(name = ORDER_ID)val uid: String,
    val foodname : String,
    val time : String,
    val amount : Int,
    val total_price : Float
)
enum class OrderStatus {
    Pending, Confirmed, Shipped
}