package com.sam.flowpractice.repository.datasourse

import kotlinx.coroutines.flow.Flow

interface DataSource {
    suspend fun getSingleStr(s : String): Flow<String>

    suspend fun getMutliInt(list: List<Int>): Flow<Int>

    suspend fun double(i: Int): Flow<Int>
}