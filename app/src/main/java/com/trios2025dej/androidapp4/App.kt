package com.trios2025dej.androidapp4

import android.app.Application
import com.trios2025dej.androidapp4.utils.HadithRepository

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        HadithRepository.init(this)
    }
}
