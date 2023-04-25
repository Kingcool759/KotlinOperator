package com.example.ko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import coil.Coil
import coil.load
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult

class MainActivity : AppCompatActivity() {

    val Tag = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testCoil()
    }

    fun testCoil() {
        val ivTest: ImageView = this.findViewById(R.id.iv_test)
//        ivTest.load(R.drawable.ic_launcher_background)

        val listener = object :ImageRequest.Listener {
            override fun onStart(request: ImageRequest) {
                super.onStart(request)
                logD(Tag, "onStart#")
            }
            override fun onSuccess(request: ImageRequest, result: SuccessResult) {
                super.onSuccess(request, result)
                logD(Tag, "onSuccess#")
            }

        }
        val request =  ImageRequest.Builder(this@MainActivity)
            .data(R.drawable.ic_launcher_background)
            .listener(listener)
            .target(ivTest)
            .build()
        Coil.imageLoader(this).enqueue(request)
    }
}