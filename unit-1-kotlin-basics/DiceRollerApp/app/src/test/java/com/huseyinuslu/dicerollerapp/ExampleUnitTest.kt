package com.huseyinuslu.dicerollerapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(5, 2 + 3)
    }

    //this is my first unit test...
    @Test
    fun dice_is_working_correctly() {
        val dice : Dice = Dice()
        assertTrue("The value is not between the numbers 1..6",dice.roll() in 1..6)
    }
}