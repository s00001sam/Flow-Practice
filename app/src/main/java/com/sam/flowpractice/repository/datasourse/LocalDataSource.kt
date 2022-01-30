package com.sam.flowpractice.repository.datasourse

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.net.NetworkRequest
import com.sam.flowpractice.MyApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class LocalDataSource : DataSource {
    override suspend fun getSingleStr(s: String) = flow {
        delay(1000)
        emit(s)
    }.flowOn(Dispatchers.IO)

    override suspend fun getMutliInt(list: List<Int>): Flow<Int> = flow {
        for (i in list) {
            delay(1000)
            emit(i)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun double(i: Int): Flow<Int> = flow {
        emit(i)
        delay(2000)
        emit(i)
    }.flowOn(Dispatchers.IO)

    override suspend fun checkNetWork(): Flow<String> = callbackFlow {
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onLost(network: Network) {
                super.onLost(network)
                trySend("NetWork Disconnect")
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                if (networkCapabilities.hasTransport(TRANSPORT_WIFI)) {
                    trySend("WIFI Connect")
                } else {
                    trySend("Net Connect")
                }
            }
        }

        val manager = MyApplication.appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        manager.registerNetworkCallback(
            NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build(), callback
        )
        awaitClose {
            manager.unregisterNetworkCallback(callback)
        }
    }

}