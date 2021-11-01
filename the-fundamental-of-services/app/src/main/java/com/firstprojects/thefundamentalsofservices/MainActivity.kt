package com.firstprojects.thefundamentalsofservices

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firstprojects.thefundamentalsofservices.databinding.ActivityMainBinding
import com.firstprojects.thefundamentalsofservices.tutorials.intentservice.MyService
import com.firstprojects.thefundamentalsofservices.tutorials.services.TimerService
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var timeStarted = false
    private lateinit var serviceIntent : Intent
    private var time : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutInflater = layoutInflater
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       checkIfServiceSRunningOrNot()

        binding.StartStopButton.setOnClickListener {
            onStartStopButton()
        }
        binding.resetButton.setOnClickListener {
            onResetButton()
        }

        serviceIntent = Intent(applicationContext, TimerService::class.java)
        registerReceiver(updateTime,IntentFilter(TimerService.TIMER_UPDATED))
    }

    private fun onResetButton() {
        time = 0.0
        stopTimer()
        binding.chronometer.text = getTimeStringFromDouble(time)
    }

    private fun onStartStopButton() {
        if(timeStarted)
            stopTimer()
        else
            startTimer()
    }

    private fun startTimer() {
        serviceIntent.putExtra(TimerService.TIME_EXTRA,time)
       startService(serviceIntent)
       binding.StartStopButton.text = "STOP"
       timeStarted = true
    }

    private fun stopTimer() {
        serviceIntent.putExtra(TimerService.TIME_EXTRA,time)
        stopService(serviceIntent)
        binding.StartStopButton.text = "START"
        timeStarted = false
    }


    private val updateTime : BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
           time = intent?.getDoubleExtra(TimerService.TIME_EXTRA,0.0)!!
            binding.chronometer.text = getTimeStringFromDouble(time)
        }

    }


    private fun getTimeStringFromDouble(time: Double): String {
        val result = time.roundToInt()
        val hours = result % 8640000 / 360000
        val minutes = result % 8640000 % 360000 / 6000
        val seconds = result % 8640000 % 360000 % 6000 / 100
        val milliseconds = result % 8640000 % 360000 % 6000 % 100
        return makingTimeStringFromTimeVariables(hours,minutes,seconds,milliseconds)

    }

    private fun makingTimeStringFromTimeVariables(hours: Int, minutes: Int, seconds: Int,milliSecs : Int)
    : String = String.format("%02d:%02d:%02d:%02d",hours,minutes,seconds,milliSecs)


    private fun checkIfServiceSRunningOrNot() {
//        checkIfServiceSRunningOrNot()
        binding.startButtonService.setOnClickListener {
            Intent(this, MyService::class.java).also {
                startService(it)
                binding.textViewChekingServices.text = "SERVICE IS RUNNING!"
            }
        }

        binding.stopButtonService.setOnClickListener {
            Intent(this,MyService::class.java).also {
                stopService(it)
                binding.textViewChekingServices.text = "SERVICE WAS STOPPED"
            }

            binding.sendDataButtone.setOnClickListener {
                Intent(applicationContext,MyService::class.java).also {
                    val dataString = binding.editTextChekingServices.text.toString()
                    it.putExtra(MyService.STRING_DATA,dataString)
                    startService(it) } } } } }

