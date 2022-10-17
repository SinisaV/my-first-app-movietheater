package com.example.lib

open class Movie(val name: String, val description: String, var rating: Double) :Comparable<Movie> {

    override fun compareTo(other: Movie): Int {
        return rating.compareTo(other.rating)
    }
    override fun toString(): String {
        return "Name: $name Description: $description Rating $rating \n"
    }
}