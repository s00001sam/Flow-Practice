package com.sam.flowpractice.repository

import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getSingleStr(s : String): Flow<String>
}