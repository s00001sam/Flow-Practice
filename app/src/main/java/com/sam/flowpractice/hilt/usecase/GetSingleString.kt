package com.sam.flowpractice.hilt.usecase

import com.sam.flowpractice.repository.Repository
import kotlinx.coroutines.flow.Flow


class GetSingleString(private var repository: Repository) : FlowUseCase<String, String>() {
    override suspend fun getFlow(params: String): Flow<String> {
        return repository.getSingleStr(params)
    }
}