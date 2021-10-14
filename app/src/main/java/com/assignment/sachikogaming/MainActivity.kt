package com.assignment.sachikogaming

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.assignment.sachikogaming.utils.NetWorkConnectionCallback
import com.assignment.sachikogaming.utils.NetworkConnections
import com.assignment.sachikogaming.utils.NetworkUtil
import com.assignment.sachikogaming.viewmodel.PostViewModel
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.rusty.sachikogaming.R
import com.rusty.sachikogaming.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NetWorkConnectionCallback {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var navController: NavController
    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        NetworkUtil.mConnectionCallback = this@MainActivity
        initNavController()
    }

    private fun initNavController(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onConnectionChanged(connection: NetworkConnections) {
        if (connection == NetworkConnections.CONNECTED){
            showHideInternetStatus(true)
            Snackbar.make(mBinding.root,getString(R.string.internet_available), BaseTransientBottomBar.LENGTH_SHORT).show()
        }else{
            showHideInternetStatus(false)
        }
    }

    private fun showHideInternetStatus(internetAvailable : Boolean){
        runOnUiThread {
            if (internetAvailable){
                snackbar?.dismiss()
            }else{
                snackbar = Snackbar.make(
                    mBinding.root,getString(R.string.no_internet_available), BaseTransientBottomBar.LENGTH_INDEFINITE
                )
                snackbar!!.show()
            }
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
}