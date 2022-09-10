package com.example.mock.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mock.*
import com.example.mock.dao.AppDao
import com.example.mock.database.DatabaseConstants.STORE_DB
import com.example.mock.entity.*



@Database(entities = [Accessory::class, Food::class, History::class, Order::class, Restaurant::class, User::class], version = 1 , exportSchema = false)

//@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: AppDao

    companion object {

        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, STORE_DB
            ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
        }
    }
}