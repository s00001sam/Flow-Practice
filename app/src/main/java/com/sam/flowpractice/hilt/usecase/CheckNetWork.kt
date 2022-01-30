package com.sam.flowpractice.hilt.usecase

import com.sam.flowpractice.repository.Repository
import kotlinx.coroutines.flow.Flow


class CheckNetWork(private var repository: Repository) : FlowUseCase<String, Any?>() {
    override suspend fun getFlow(params: Any?): Flow<String> {
        return repository.checkNetWork()
    }
}