package com.example.librarymanagementsystem

interface Digitizationable<in T : ReadableInHall, out R : DigitalMedia> {
    fun digitization(libraryItem: T): R
}

class BooksAndNewspapersDigitizationCD:Digitizationable<ReadableInHall, Disc> {
    override fun digitization(libraryItem: ReadableInHall): Disc {
        libraryItem as LibraryItem
        return Disc(libraryItem.name, DiscType.CD)
    }
}