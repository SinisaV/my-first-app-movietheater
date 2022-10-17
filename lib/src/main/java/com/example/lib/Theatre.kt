package com.example.lib

class Theatre(val name: String, var address: String) :Sizable {
    override fun toString(): String {
        return "Name: $name Address: $address"
    }

    val listOfMovies: MutableList<Movie> = mutableListOf()
    val listOfTickets: MutableList<Ticket> = mutableListOf()

    val movieName = listOf<String>("Star Wars", "Gladiator", "Hobbit", "Avatar", "Spider Man")
    val descriptions = listOf<String>("SSS", "GGG", "HHH", "AAA", "SSM")
    val ratings = listOf<Double>(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)
    val ides = listOf<Int>(1122, 3341, 5690, 4444, 3020)
    val prices = listOf<Double>(5.99, 6.99, 4.50, 7.80, 5.75)

    fun generateMovies(n: Int) {
        for (i in 1..n) {
            listOfMovies.add(Movie(movieName.random(), descriptions.random(), ratings.random()))
        }
    }

    fun generateTickets(n: Int) {
        for (i in 1..n) {
            listOfTickets.add(Ticket(ides.random(), prices.random()))
        }
    }

    fun print() {
        println("Theatre name: $name")
        println("Sorted Movies \n ${listOfMovies.sorted()}")
        println("Sorted Tickets \n ${listOfTickets.sorted()}")
    }

    fun removeMovies() {
        listOfMovies.clear()
    }

    fun removeTickets() {
        listOfTickets.clear()
    }

    override fun size(): Int {
        return (listOfMovies.size + listOfTickets.size)
    }
}