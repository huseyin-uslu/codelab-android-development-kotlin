package com.huseyinuslu.dicerollerapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val theDice: Dice = Dice()
    private var number: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val roll_button: Button = findViewById(R.id.mainactivity_roll_button)
        val dice_image: ImageView = findViewById(R.id.mainactivity_imageview_dice)
        roll_button.setOnClickListener { roll(dice_image) }
        roll(dice_image)
    }

    private fun roll(dice: ImageView) {
        number = theDice.roll()
        when (number) {
            1 -> dice.setImageResource(R.drawable.dice_1)
            2 -> dice.setImageResource(R.drawable.dice_2)
            3 -> dice.setImageResource(R.drawable.dice_3)
            4 -> dice.setImageResource(R.drawable.dice_4)
            5 -> dice.setImageResource(R.drawable.dice_5)
            6 -> dice.setImageResource(R.drawable.dice_6)
        }
    }
}