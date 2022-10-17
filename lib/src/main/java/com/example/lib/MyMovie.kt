package com.example.lib

fun main () {
    val list = Theatre("Maribox", "Maribor")
    list.print()

    println("List size ${list.size()}")

    list.generateMovies(3)
    list.generateTickets(2)
    list.print()
    println("List size ${list.size()}")

    list.removeMovies()
    list.removeTickets()

    list.generateMovies(4)
    list.generateTickets(1)
    list.print()
    println("List size ${list.size()}")

    val listOfNumbers = listOf(1, 2, 3)
    try {
        println("Element in list: ${outOfBoundsList(3, listOfNumbers)}")
    } catch (e1: ListIndexOutOfBoundsException) {
        println(e1)
    }

    val age = -21
    try {
        println("Age: ${illegalAge(age)}")
    } catch (e2: IllegalArgumentException) {
        println(e2)
    }

    try {
        println("Result: ${divideNumbers(3, 0)}")
    } catch (e3: ArithmeticException) {
        println(e3)
    }

    try {
        val ticket1 = Ticket(1249, -19.0)
        print(ticket1.toString())
    } catch (e4: PriceException) {
        println(e4)
    }
}