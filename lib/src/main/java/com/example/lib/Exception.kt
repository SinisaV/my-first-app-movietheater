package com.example.lib

open class ListIndexOutOfBoundsException(val p0: String?) :Exception(p0) {
    override fun toString(): String {
        return "List Index out uf bounds exception $p0"
    }
}

fun outOfBoundsList(n: Int, list: List<Int>): Int {
    if (n >= list.size) throw ListIndexOutOfBoundsException("List is out of bounds")
    return list[n]
}

open class IllegalArgumentException(val p1: String?) :Exception(p1) {
    override fun toString(): String {
        return "Illegal argument exception $p1"
    }
}

fun illegalAge(age: Int): Int {
    if (age < 0) throw IllegalArgumentException("Age must be greater than 0")
    return age
}

open class ArithmeticException(val p2: String?) :Exception(p2) {
    override fun toString(): String {
        return "Arithmetical exception $p2"
    }
}

fun divideNumbers(number1: Int, number2: Int): Int {
    if (number2 == 0) throw ArithmeticException("Divide by 0")
    return number1 / number2
}

open class PriceException(val p3: String?) :Exception(p3) {
    override fun toString(): String {
        return "Price Exception $p3"
    }
}
