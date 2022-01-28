package com.sam.flowpractice.hilt.usecase

import com.sam.flowpractice.repository.Repository
import kotlinx.coroutines.flow.Flow


class GetMutliInt(private var repository: Repository) : FlowUseCase<Int, List<Int>>() {
    override suspend fun getFlow(params: List<Int>): Flow<Int> {
        return repository.getMutliInt(params)
    }
}