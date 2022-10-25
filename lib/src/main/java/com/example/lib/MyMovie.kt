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

    val theatre = Theatre("Maribox", "Maribor")
    var list1:MutableList<Ticket> = Theatre.generateTickets(30)
    theatre.listOfTickets = list1

    theatre.print()

    println("\nFilter theatre if contain")
    println(theatre.filteredListByString("30").toString())

    println("\nFilter theatre not contain")
    println(theatre.filteredListNotString("4444").toString())

    println("\ntheatre size ${theatre.size()}")

    println("\nCalculate total Price of tickets")
    println(theatre.calculateTotalPrice().toString())

    println("\nFilter 10 elements that have price greater than 5")
    println(theatre.returnFirst10ElementsWithPriceGreaterThan5().toString())

    println("\nFilter number of elements that contains string")
    println(theatre.returnNumberOfElementContainsString("1122"))
}