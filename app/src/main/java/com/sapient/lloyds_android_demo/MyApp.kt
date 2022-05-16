package com.sapient.lloyds_android_demo

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApp : MultiDexApplication(){
    override fun onCreate() {
        super.onCreate()
        //AndroidThreeTen.init(this);
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}
