package com.sam.flowpractice.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sam.flowpractice.hilt.usecase.GetSingleString
import com.sam.flowpractice.repository.statehandle.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSingleString: GetSingleString
): ViewModel() {

    private val _singleStrResult = MutableStateFlow<State<String>>(State.NothingState)
    val singleStrResult: StateFlow<State<String>> = _singleStrResult

    fun getSingleString() {
        viewModelScope.launch {
            getSingleString.getFlow("hello word!")
                .onStart { _singleStrResult.value = State.LoadingState }
                .collect {
                    Log.d("sam","sam00 it=$it")
                    _singleStrResult.value = State.DataState(it)
                }
        }
    }

}