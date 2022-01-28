package com.sam.flowpractice.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sam.flowpractice.hilt.usecase.GetMutliInt
import com.sam.flowpractice.hilt.usecase.GetSingleString
import com.sam.flowpractice.repository.statehandle.ErrorUtil
import com.sam.flowpractice.repository.statehandle.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSingleString: GetSingleString,
    private val getMutliInt: GetMutliInt
): ViewModel() {

    private val listInt = listOf(1, 2, 3, 4, 5)

    private val _strResult = MutableStateFlow<State<String>>(State.NothingState)
    val strResult: StateFlow<State<String>> = _strResult

    fun getSingleString() {
        viewModelScope.launch {
            getSingleString.getFlow("hello word!")
                .onStart { _strResult.value = State.LoadingState }
                .catch { e -> _strResult.value = ErrorUtil.resolveError(e) }
                .collect {
                    _strResult.value = State.DataState(it)
                }
        }
    }

    // by single() 只能取得單一結果
    fun getSingleString2() {
        viewModelScope.launch {
            val singleResult = getSingleString.getFlow("123")
                .onStart { _strResult.value = State.LoadingState }
                .catch { e -> _strResult.value = ErrorUtil.resolveError(e) }
                .single()
            _strResult.value = State.DataState(singleResult)
        }
    }

    fun getInts() {
        viewModelScope.launch {
            getMutliInt.getFlow(listInt)
                .onStart { _strResult.value = State.LoadingState }
                .catch { e -> _strResult.value = ErrorUtil.resolveError(e) }
                .collect {
                    _strResult.value = State.DataState(it.toString())
                }
        }
    }

    //collectLatest 有耗時時，且耗時超過下一個值的發射時間，就會取最後一個了，不然等同 collect
    fun getIntsLatest() {
        viewModelScope.launch {
            getMutliInt.getFlow(listInt)
                .onStart { _strResult.value = State.LoadingState }
                .catch { e -> _strResult.value = ErrorUtil.resolveError(e) }
                .collectLatest {
                    delay(1100)
                    _strResult.value = State.DataState(it.toString())
                    Log.d("sam","sam00 int last=$it")
                }
        }
    }

    //buffer() 不須等 collect 完才射出
    fun getIntsBuffer() {
        viewModelScope.launch {
            for(i in 1..8) {
                delay(1000)
                Log.d("sam","sam00 first=$i")
            }
        }
        viewModelScope.launch {
            getMutliInt.getFlow(listInt)
                .buffer()
                .onStart { _strResult.value = State.LoadingState }
                .catch { e -> _strResult.value = ErrorUtil.resolveError(e) }
                .collect {
                    delay(1000)
                    _strResult.value = State.DataState(it.toString())
                    Log.d("sam","sam00 int buffer=$it")
                }
        }
    }

    //conflate() 當 collect() 處理太慢時，會跳過中間值，處理最新的值
    fun getIntsConflate() {
        viewModelScope.launch {
            for(i in 1..8) {
                delay(1000)
                Log.d("sam","sam00 first=$i")
            }
        }
        viewModelScope.launch {
            getMutliInt.getFlow(listInt)
                .conflate()
                .onStart { _strResult.value = State.LoadingState }
                .catch { e -> _strResult.value = ErrorUtil.resolveError(e) }
                .collect {
                    delay(3000)
                    _strResult.value = State.DataState(it.toString())
                    Log.d("sam","sam00 int conflate=$it")
                }
        }
    }

    fun getIntsMap() {
        viewModelScope.launch {
            getMutliInt.getFlow(listInt)
                .map { "hello $it" }
                .onStart { _strResult.value = State.LoadingState }
                .catch { e -> _strResult.value = ErrorUtil.resolveError(e) }
                .collect {
                    _strResult.value = State.DataState(it)
                }
        }
    }

    fun getIntsZip() {
        viewModelScope.launch {
            getMutliInt.getFlow(listInt)
                .zip(getSingleString.getFlow("hello word!")) { i, s ->
                    val result = "$i $s"
                    result
                }
                .onStart { _strResult.value = State.LoadingState }
                .catch { e -> _strResult.value = ErrorUtil.resolveError(e) }
                .collect {
                    Log.d("sam","sam00 int zip=$it")
                    _strResult.value = State.DataState(it)
                }
        }
    }

    fun getIntsCombine() {
        viewModelScope.launch {
            getMutliInt.getFlow(listInt)
                .combine(getSingleString.getFlow("hello word!")) { i, s ->
                    val result = "$i $s"
                    result
                }
                .onStart { _strResult.value = State.LoadingState }
                .catch { e -> _strResult.value = ErrorUtil.resolveError(e) }
                .collect {
                    Log.d("sam","sam00 int combine=$it")
                    _strResult.value = State.DataState(it)
                }
        }
    }

    fun getIntsFilter() {
        viewModelScope.launch {
            getMutliInt.getFlow(listInt)
                .filter { it % 2 == 0 }
                .onStart { _strResult.value = State.LoadingState }
                .catch { e -> _strResult.value = ErrorUtil.resolveError(e) }
                .collect {
                    _strResult.value = State.DataState(it.toString())
                }
        }
    }

    fun getIntsTransform() {
        viewModelScope.launch {
            getMutliInt.getFlow(listInt)
                .transform {
                    if (it % 2 == 0) {
                        emit(it)
                        emit(it)
                    }
                }
                .onStart { _strResult.value = State.LoadingState }
                .catch { e -> _strResult.value = ErrorUtil.resolveError(e) }
                .collect {
                    _strResult.value = State.DataState(it.toString())
                    _strResult.value = State.NothingState
                }
        }
    }

    @FlowPreview
    fun getIntsflatMapConcat() {
        viewModelScope.launch {
            getMutliInt.getFlow(listInt)
                .flatMapConcat { double(it) }
                .onStart { _strResult.value = State.LoadingState }
                .catch { e -> _strResult.value = ErrorUtil.resolveError(e) }
                .collect {
                    Log.d("sam","sam00 flatMapConcat=$it")
                    _strResult.value = State.DataState(it.toString())
                    _strResult.value = State.NothingState
                }
        }
    }

    @FlowPreview
    fun getIntsflatMapMerge() {
        viewModelScope.launch {
            getMutliInt.getFlow(listInt)
                .flatMapMerge { double(it) }
                .onStart { _strResult.value = State.LoadingState }
                .catch { e -> _strResult.value = ErrorUtil.resolveError(e) }
                .collect {
                    Log.d("sam","sam00 flatMapMerge=$it")
                    _strResult.value = State.DataState(it.toString())
                    _strResult.value = State.NothingState
                }
        }
    }

    //reduce() 會結束 flow, accumulator 一開始會是 flow 裡第一個值，而 value 一開始會是 flow 裡第二個值
    fun getIntsReduce() {
        viewModelScope.launch {
            try {
                getMutliInt.getFlow(listInt)
                    .reduce { accumulator, value ->
                        Log.d("sam","sam00 reduce accumulator=$accumulator, value=$value")
                        accumulator + value
                    }
            } catch (e: Exception) {
                _strResult.value = ErrorUtil.resolveError(e)
            }
        }
    }

    //fold() 必須在 initial 參數指定 accumulator 的初始值，所以 accumulator 一開始會是 initial
    fun getIntsFold() {
        viewModelScope.launch {
            try {
                getMutliInt.getFlow(listInt)
                    .fold(100) { accumulator, value ->
                        Log.d("sam","sam00 fold accumulator=$accumulator, value=$value")
                        accumulator + value
                    }
            } catch (e: Exception) {
                _strResult.value = ErrorUtil.resolveError(e)
            }
        }
    }

    fun double(value: Int) = flow {
        emit(value)
        delay(2000)
        emit(value)
    }

}