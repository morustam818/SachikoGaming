package com.assignment.sachikogaming.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.assignment.sachikogaming.MyApplication

class NetworkUtil{

    companion object {
        var connectivityManager: ConnectivityManager?=null
        var mConnectionCallback : NetWorkConnectionCallback?= null

        fun configureNetwork(context: MyApplication) {
            if (connectivityManager==null) {
                connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

                val wifiNetworkRequestBuilder = NetworkRequest.Builder()
                wifiNetworkRequestBuilder.addTransportType(NetworkCapabilities.TRANSPORT_WIFI)

                val mobileDataNetworkRequestBuilder = NetworkRequest.Builder()
                mobileDataNetworkRequestBuilder.addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)

                connectivityManager?.registerNetworkCallback(wifiNetworkRequestBuilder.build(), wifiNetworkCallback)
                connectivityManager?.registerNetworkCallback(mobileDataNetworkRequestBuilder.build(), mobileDataNetworkCallback)
            }
        }

        var wifiConnected = false
        var mobileDataConnected = false

        private var wifiNetworkCallback = object : ConnectivityManager.NetworkCallback(){

            override fun onLost(network: Network) {
                super.onLost(network)
                wifiConnected = false
                mConnectionCallback?.let {
                    updateCallback(it)
                }
            }

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                wifiConnected = true
                mConnectionCallback?.let {
                    updateCallback(it)
                }
            }

            override fun onUnavailable() {
                super.onUnavailable()
                wifiConnected = false
                mConnectionCallback?.let {
                    updateCallback(it)
                }
            }
        }

        private fun updateCallback(it: NetWorkConnectionCallback) {
            it.onConnectionChanged(if(checkNetworkConnection()) NetworkConnections.CONNECTED else NetworkConnections.LOST)
        }

        private var mobileDataNetworkCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onLost(network: Network) {
                super.onLost(network)
                mobileDataConnected = false
                mConnectionCallback?.let {
                    updateCallback(it)
                }
            }

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                mobileDataConnected = true
                mConnectionCallback?.let {
                    updateCallback(it)
                }
            }
        }

        fun checkNetworkConnection(): Boolean {
            return wifiConnected || mobileDataConnected
        }

    }
}

interface NetWorkConnectionCallback{
    fun onConnectionChanged(connection : NetworkConnections)
}

enum class NetworkConnections{
    CONNECTED,
    LOST
}