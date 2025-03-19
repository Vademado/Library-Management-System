package com.example.librarymanagementsystem

abstract class LibraryItem : Identifiable, Accessible, Named {
    companion object {
        var instanceCount = 0
    }
}

interface Identifiable {
    val id: Int
}

interface Accessible {
    var accessibility: Boolean
}

interface Named {
    val name: String
}

interface Borrowable {
    fun takeHome(): String
}

interface ReadableInHall {
    fun readInHall(): String
}

interface Returnable {
    fun returnItem(): String
}