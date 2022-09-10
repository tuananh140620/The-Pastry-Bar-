//package com.example.mock
//
//import androidx.room.TypeConverter
//import gst.trainingcourse.firebasedemo.entity.OrderStatus
//import java.util.*
//
//class Converters {
//    @TypeConverter
//    fun orderStatusToString(status: OrderStatus): String = status.toString()
//
//    @TypeConverter
//    fun stringToOrderStatus(status: String): OrderStatus = OrderStatus.valueOf(status)
//    @TypeConverter fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis
//
//    @TypeConverter fun datestampToCalendar(value: Long): Calendar =
//        Calendar.getInstance().apply { timeInMillis = value }
//}