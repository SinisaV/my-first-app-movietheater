package com.example.lib

fun main () {

    arrayIndexOutOfBoundsTry()
    numberFormatExceptionTry()
    arithmeticalExceptionTry()

    try {
        val ticket1 = Ticket(1249, -19.0)
        print(ticket1.toString())
    } catch (e4: PriceException) {
        println(e4)
    }

    println("\n")

    val list = Theatre("Maribox", "Maribor")
    Theatre.generateTickets(30)

    list.print()

    println("\nFilter list if contain")
    println(list.filteredListByString("30").toString())

    println("\nFilter list not contain")
    println(list.filteredListNotString("4444").toString())

    println("\nList size ${list.size()}")

    println("\nCalculate total Price of tickets")
    println(list.calculateTotalPrice().toString())

    println("\nFilter 10 elements that have price greater than 5")
    println(list.returnFirst10ElementsWithPriceGreaterThan5().toString())

    println("\nFilter number of elements that contains string")
    println(list.returnNumberOfElementContainsString("1122"))
}