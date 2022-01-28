package com.sam.flowpractice.repository.datasourse

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class LocalDataSource : DataSource {
    override suspend fun getSingleStr(s: String) = flow {
        delay(1000)
        emit(s)
    }.flowOn(Dispatchers.IO)

    override suspend fun getMutliInt(list: List<Int>): Flow<Int> = flow {
        for (i in list) {
            delay(1000)
            emit(i)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun double(i: Int): Flow<Int> = flow {
        emit(i)
        delay(2000)
        emit(i)
    }.flowOn(Dispatchers.IO)

}