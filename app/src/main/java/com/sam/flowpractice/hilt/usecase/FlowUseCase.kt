package com.sam.flowpractice.hilt.usecase

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<out Type, in Params> where Type : Any? {
    abstract suspend fun getFlow(params: Params): Flow<Type>
}