package com.assignment.sachikogaming

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.assignment.sachikogaming.utils.NetWorkConnectionCallback
import com.assignment.sachikogaming.utils.NetworkConnections
import com.assignment.sachikogaming.utils.NetworkUtil
import com.assignment.sachikogaming.viewmodel.PostViewModel
import com.rusty.sachikogaming.R
import com.rusty.sachikogaming.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NetWorkConnectionCallback {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        NetworkUtil.mConnectionCallback = this@MainActivity
    }

    override fun onConnectionChanged(connection: NetworkConnections) {
        if (connection == NetworkConnections.CONNECTED){
            showHideInternetStatus(true)
        }else{
            showHideInternetStatus(false)
        }
    }

    override fun onResume() {
        super.onResume()
        if (!NetworkUtil.checkNetworkConnection()) {
            showHideInternetStatus(false)
        }else{
            showHideInternetStatus(true)
        }
    }
    private fun showHideInternetStatus(internetAvailable : Boolean){
        runOnUiThread {
            if (internetAvailable){
                //showPost
            }else{
                //showNoInternetAvailable
            }
        }
    }
}