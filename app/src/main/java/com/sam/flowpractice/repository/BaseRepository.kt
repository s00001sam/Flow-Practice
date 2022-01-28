package com.sam.flowpractice.repository

import com.sam.flowpractice.repository.datasourse.DataSource
import kotlinx.coroutines.flow.Flow


class BaseRepository (
        private val localDataSource: DataSource,
) : Repository {
        override suspend fun getSingleStr(s : String): Flow<String> {
                return localDataSource.getSingleStr(s)
        }

        override suspend fun getMutliInt(list: List<Int>): Flow<Int> {
                return localDataSource.getMutliInt(list)
        }

}