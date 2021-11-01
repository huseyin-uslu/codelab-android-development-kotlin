package com.firstprojects.thefundamentalsofservices.tutorials.intentservice

import android.app.AlertDialog
import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.android.material.shape.ShapeAppearanceModel.builder
import java.util.stream.IntStream.builder
import java.util.stream.Stream.builder


class MyService : Service() {




    private lateinit var handler : Handler
    private lateinit var runnable : Runnable

   private val TAG = "MyService :"

    companion object {
        const val STRING_DATA = "EXTRA_STRING"
    }
    override fun onBind(intent: Intent?): IBinder? = null
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val dataString: String? = intent!!.getStringExtra(STRING_DATA)
        dataString?.let {
            handler = Handler.createAsync(Looper.getMainLooper())
            runnable = Runnable {
                Log.i(TAG, "onStartCommand: $it")
            }
            runnable.run()

            handler.postDelayed(runnable, 1000L)

        }
        return START_NOT_STICKY
    }
    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate: CALLED..")
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
        Log.d(TAG, "onDestroy: Service is being killed , oh No !!")
    }
}