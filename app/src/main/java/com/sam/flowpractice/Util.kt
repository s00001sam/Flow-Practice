package com.sam.flowpractice

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

object Util {
    fun String.toast() {
        Toast.makeText(MyApplication.appContext, this, Toast.LENGTH_SHORT).show()
    }

    fun <T> Fragment.collectLastFlowStarted(flow: Flow<T>, action: suspend (value: T) -> Unit) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            flow.collectLatest(action)
        }
    }

    fun <T> Flow<T>.launchWhenStartedOld(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycleScope.launchWhenStarted {
            collect()
        }
    }

    fun <T> Flow<T>.launchWhenStarted(lifecycleOwner: LifecycleOwner)= with(lifecycleOwner) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                try {
                    this@launchWhenStarted.collect()
                }catch (t: Throwable){
                    Log.d("sam","sam00 throwable=${t.localizedMessage}")
                }
            }
        }
    }
}
