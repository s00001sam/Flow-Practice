package com.sam.flowpractice

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

object Util {
    fun <T> Fragment.collectLastFlowStarted(flow: Flow<T>, action: suspend (value: T) -> Unit) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            flow.collectLatest(action)
        }
    }


    fun String.toast() {
        Toast.makeText(MyApplication.appContext, this, Toast.LENGTH_SHORT).show()
    }
}
