package com.assignment.sachikogaming

import android.app.Application
import com.assignment.sachikogaming.utils.NetworkUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        NetworkUtil.configureNetwork(this)
    }

}