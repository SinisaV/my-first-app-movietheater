package com.example.lib

import java.util.UUID

open class Ticket(var id: Int, var price: Double): Comparable<Ticket> {
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

class AdvanceTicket(id: Int, price: Double, var daysAhead: Int, private val uuid:String = UUID.randomUUID().toString().replace("-", "")) :Ticket(id, price) {
    override fun toString(): String {
        return "UUID: $uuid ID: $id Price: $price Days Ahead: $daysAhead \n"
    }
}