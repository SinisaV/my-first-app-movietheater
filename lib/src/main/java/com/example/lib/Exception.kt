package com.example.lib

open class PriceException(val p3: String?) :Exception(p3) {
    override fun toString(): String {
        return "Price Exception $p3"
    }
}

fun arrayIndexOutOfBoundsTry () {
    val listOfNumbers = listOf(1, 2, 3)
    try {
        println(listOfNumbers[4])
    } catch (e1: ArrayIndexOutOfBoundsException) {
        println(e1)
    }
}

fun numberFormatExceptionTry() {
    try {
        val number = "Test".toInt()
        println(number)
    } catch (e: NumberFormatException) {
        println("Number format exception + $e")
    }
}

fun arithmeticalExceptionTry() {
    try {
        val calculateResult = 10/0
        println(calculateResult)
    } catch (e3: ArithmeticException) {
        println("Number cannot be devide by zero")
    }
}
