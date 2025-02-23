package com.tsaiting.irremotecontrol

import android.app.Application
import com.tsaiting.irremotecontrol.di.initKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}