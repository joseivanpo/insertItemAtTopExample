package com.example.testing

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagingApi::class)
class NumberGeneratorMediator(
    private val numbersDao: NumbersDao
) : RemoteMediator<Int, Numbers>() {

    private var itemAtTopCounter = 1000
    private var itemsCounter = 10001

    fun insertFirstItem() {
        CoroutineScope(IO).launch {
            val newItem = Numbers(number = itemAtTopCounter--)
            numbersDao.insert(newItem)
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Numbers>
    ): MediatorResult {

        var generateNumbers = false

        when (loadType) {
            LoadType.REFRESH -> {
                numbersDao.deleteNumbers()
            }
            LoadType.APPEND -> {
                generateNumbers = true
            }
            else -> return MediatorResult.Success(false)
        }

        if (generateNumbers) {
            val newNumbers = ArrayList<Numbers>().apply {
                for (i in 0 until 30) {
                    add(Numbers(number = itemsCounter++))
                }
            }

            numbersDao.insert(newNumbers)

            return MediatorResult.Success(false)
        }

        return MediatorResult.Success(false)
    }
}