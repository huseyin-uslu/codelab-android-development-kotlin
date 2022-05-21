package com.huseyinuslu.dicerollerapp

class Dice(var sides : Int = 6) {

    private var diceNumber : Int? = null

    fun roll() : Int {
        diceNumber = (1..sides).random()
        return diceNumber!!
    }

    fun showNumberOfDice() : Int {
        return when(diceNumber){
            null -> {roll()
            diceNumber!!}
            else -> diceNumber!!
        }
    }
}