package com.example.lib

class Theatre(private val name: String, private var address: String) :Sizable {

    var listOfTickets: MutableList<Ticket> = mutableListOf()

    override fun toString(): String {
        return "Name: $name Address: $address"
    }

    companion object {

        fun generateTickets(n: Int): MutableList<Ticket> {
            val ides = listOf<Int>(1122, 3341, 5690, 4444, 3020)
            val prices = listOf<Double>(5.99, 6.99, 4.50, 7.80, 5.75)
            val daysAhead = listOf<Int>(10, 20, 30, 40, 50)

            val list: MutableList<Ticket> = mutableListOf()

            for (i in 0 until n) {
                val tickets = listOf<Ticket>(Ticket(ides.random(), prices.random()),
                    AdvanceTicket(ides.random(), prices.random(), daysAhead.random()))
                list.add(tickets.random())
            }

            return list
        }
    }

    fun filteredListByString(myString: String) :List<Ticket> {
        val list = listOfTickets.filter {
            if (it is AdvanceTicket) {
                it.daysAhead.toString() == myString  ||
                        it.id.toString() == myString ||
                        it.price.toString() == myString
            }
            else {
                it.id.toString() == myString || it.price.toString() == myString
            }
        }
        return list
    }

    fun filteredListNotString(myString: String) :List<Ticket> {
        val list = listOfTickets.filterNot {
            if (it is AdvanceTicket) {
                it.daysAhead.toString() == myString ||
                        it.id.toString() == myString ||
                        it.price.toString() == myString
            }
            else {
                it.id.toString() == myString || it.price.toString() == myString
            }
        }
        return list
    }

    fun calculateTotalPrice() :Double {
        var sumPrice = 0.0

        for (i in 0 until listOfTickets.size) {
            if (listOfTickets[i] is AdvanceTicket) {
                sumPrice += (listOfTickets[i].price - (listOfTickets[i].price * 0.2))
            } else {
                sumPrice += listOfTickets[i].price
            }
        }
        return sumPrice
    }

    fun returnFirst10ElementsWithPriceGreaterThan5():MutableList<Ticket> {
        val list: MutableList<Ticket> = mutableListOf()
        var j = 0
        for (i in 0 until listOfTickets.size) {
            if (listOfTickets[i].price > 5.0) {
                list.add(listOfTickets[i])
                j++
            }
            if (j == 10) {
                return list
            }
        }
        return list
    }

    fun returnNumberOfElementContainsString(str: String):Int {
        var j = 0
        for (i in 0 until listOfTickets.size) {
            if (listOfTickets[i].id.toString() == str) {
                j++
            }
        }
        return j
    }

    /*val listOfMovies: MutableList<Movie> = mutableListOf()
    val movieName = listOf<String>("Star Wars", "Gladiator", "Hobbit", "Avatar", "Spider Man")
    val descriptions = listOf<String>("SSS", "GGG", "HHH", "AAA", "SSM")
    val ratings = listOf<Double>(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)

    fun generateMovies(n: Int) {
        for (i in 1..n) {
            listOfMovies.add(Movie(movieName.random(), descriptions.random(), ratings.random()))
        }
    }*/

    fun print() {
        println("Theatre name: $name")
        //println("Sorted Movies \n ${listOfMovies}}")
        println("Sorted Tickets \n ${listOfTickets.sorted()}")
    }

    fun removeTickets() {
        listOfTickets.clear()
    }

    override fun size(): Int {
        return listOfTickets.size
    }
}