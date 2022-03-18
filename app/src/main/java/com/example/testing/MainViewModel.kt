package com.example.testing

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData

class MainViewModel : ViewModel() {

    private val database: NumbersDB by lazy { NumbersDB.getInstance(null) }

    private val remoteMediator: NumberGeneratorMediator by lazy { NumberGeneratorMediator(database.getNumbersDao()) }

    @OptIn(ExperimentalPagingApi::class)
    val numbers: LiveData<PagingData<Numbers>> by lazy {
        Pager(
            config = PagingConfig(
                pageSize = 30,
                initialLoadSize = 60,
                enablePlaceholders = false
            ),
            remoteMediator = remoteMediator
        ) {
            database.getNumbersDao().getNumbers()
        }.flow.asLiveData()
    }

    fun insertFirstItem() {
        remoteMediator.insertFirstItem()
    }
}