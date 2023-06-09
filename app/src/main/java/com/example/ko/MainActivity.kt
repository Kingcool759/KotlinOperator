package com.example.ko

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import coil.Coil
import coil.load
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.ko.activity.QueueActivity
import com.example.ko.activity.SingleActivity

class MainActivity : AppCompatActivity() {

    val Tag = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testCoil()
        testOkDownload()
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

    private fun testOkDownload() {
        findViewById<Button>(R.id.go_single).setOnClickListener {
            startActivity(Intent(this, QueueActivity::class.java))
        }
    }
}