package com.nikede.vk_sdk_code_template

import android.app.Application
import com.vk.api.sdk.VK

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        VK.initialize(applicationContext)
    }

}