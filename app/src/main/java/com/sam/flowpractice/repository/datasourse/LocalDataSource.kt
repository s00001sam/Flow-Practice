package com.sam.flowpractice.repository.datasourse

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class LocalDataSource : DataSource {
    override suspend fun getSingleStr(s: String) = flow {
        delay(2000)
        emit(s)
        delay(5000)
        emit(s)
    }.flowOn(Dispatchers.IO)

}