package com.sapient.lloyds_android_demo.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sapient.lloyds_android_demo.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
