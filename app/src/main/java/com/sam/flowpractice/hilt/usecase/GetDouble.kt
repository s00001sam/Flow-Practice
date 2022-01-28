package com.sam.flowpractice.hilt.usecase

import com.sam.flowpractice.repository.Repository
import kotlinx.coroutines.flow.Flow


class GetDouble(private var repository: Repository) : FlowUseCase<Int, Int>() {
    override suspend fun getFlow(params: Int): Flow<Int> {
        return repository.double(params)
    }
}