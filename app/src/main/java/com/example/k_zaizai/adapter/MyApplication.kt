package com.example.k_zaizai.adapter

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import org.litepal.LitePal
import org.xutils.x

class MyApplication: Application(){

        companion object {
            @SuppressLint("StaticFieldLeak")
            lateinit var mcontext: Context
        }
        override fun onCreate() {
            super.onCreate()
            mcontext = applicationContext
            LitePal.initialize(mcontext)
            x.Ext.init(this)
        }

}