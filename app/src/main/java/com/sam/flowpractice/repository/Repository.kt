package com.sam.flowpractice.repository

import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getSingleStr(s : String): Flow<String>

    suspend fun getMutliInt(list: List<Int>): Flow<Int>

    suspend fun double(i: Int): Flow<Int>
}