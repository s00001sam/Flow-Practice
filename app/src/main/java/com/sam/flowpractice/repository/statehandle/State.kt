package com.sam.flowpractice.repository.statehandle

sealed class State<out T> {
    object LoadingState : State<Nothing>()
    object NothingState : State<Nothing>()
    data class ErrorState(var exception: Throwable) : State<Nothing>()
    data class DataState<T>(var data: T) : State<T>()
}