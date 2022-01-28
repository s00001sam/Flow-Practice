package com.sam.flowpractice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sam.flowpractice.Util.launchWhenStarted
import com.sam.flowpractice.repository.statehandle.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

open class BaseFragment : Fragment() {

    protected fun showLoading() {
        (requireActivity() as MainActivity).showLoading()
    }

    protected fun dismissLoading() {
        (requireActivity() as MainActivity).dismissLoading()
    }

    protected fun <T, K: State<T>> Flow<K>.collectDataState(doSomething:((t: T)-> Unit)) {
        this.onEach {
            when(it) {
                is State.LoadingState -> showLoading()
                is State.DataState<*> -> {
                    dismissLoading()
                    (it.data as? T)?.let { data -> doSomething.invoke(data) }
                }
            }
        }.launchWhenStarted(viewLifecycleOwner)
    }
}