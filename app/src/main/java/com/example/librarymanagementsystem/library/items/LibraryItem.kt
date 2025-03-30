package com.example.librarymanagementsystem.library.items

abstract class LibraryItem() : Identifiable, Accessible, Named {
    companion object {
        var instanceCount = 0
    }
}

abstract class DigitalMedia : LibraryItem() {}

interface Identifiable {
    val id: Int
}

interface Accessible {
    var isAccessable: Boolean
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