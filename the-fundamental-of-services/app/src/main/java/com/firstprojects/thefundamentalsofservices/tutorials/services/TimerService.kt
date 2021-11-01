package com.firstprojects.thefundamentalsofservices.tutorials.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.util.*

class TimerService : Service() {

    private val timer = Timer()
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
       val time = intent?.getDoubleExtra(TIME_EXTRA,0.0)
        if(time != null)
            timer.scheduleAtFixedRate(TimeTask(time),0,10)
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    private inner class TimeTask(
        private var time : Double
    ) : TimerTask(){

        override fun run() {
          val intent = Intent(TIMER_UPDATED)
            time++
            intent.putExtra(TIME_EXTRA,time)
            sendBroadcast(intent)
        }
    }


    companion object {
        const val TIMER_UPDATED = "timerUpdated"
        const val TIME_EXTRA = "timeExtra"
    }



}