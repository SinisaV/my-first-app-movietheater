package com.example.lib

open class Ticket(val id: Int, val price: Double): Comparable<Ticket> {
    init {
        if (price < 0) throw PriceException("Price must be greater than 0")
    }
    override fun compareTo(other: Ticket): Int {
        return id.compareTo(other.id)
    }

    override fun toString(): String {
        return "ID: $id Price: $price \n"
    }
}

class AdvanceTicket(id: Int, price: Double, val daysAhead: Int) :Ticket(id, price) {
    override fun toString(): String {
        return "ID: $id Price: $price Days Ahead: $daysAhead \n"
    }
}