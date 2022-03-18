package com.example.testing

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1, entities = [
        Numbers::class
    ]
)
abstract class NumbersDB : RoomDatabase() {

    companion object {
        private val DATABASE_NAME = "QueRolloooo!!"
        private var instance: NumbersDB? = null

        fun getInstance(context: Context?): NumbersDB {

            if (instance == null) {
                instance =
                    Room.databaseBuilder(context!!, NumbersDB::class.java, DATABASE_NAME).build()
            }

            return instance!!
        }
    }

    abstract fun getNumbersDao(): NumbersDao
}