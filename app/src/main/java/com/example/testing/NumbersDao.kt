package com.example.testing

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NumbersDao {

    @Insert
    suspend fun insert(number: Numbers)

    @Insert
    suspend fun insert(numbers: List<Numbers>)

    @Query("SELECT * FROM Numbers ORDER BY number ASC")
    fun getNumbers(): PagingSource<Int, Numbers>

    @Query("Delete from Numbers")
    suspend fun deleteNumbers()
}