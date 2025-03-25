package com.example.librarymanagementsystem

class Library(override val name: String,
    private val digitizationCabinet: Digitizationable<ReadableInHall, DigitalMedia> = BooksAndNewspapersDigitizationCD()) : Named {
    private val libraryItems = mutableListOf<LibraryItem>()

    fun addItem(libraryItem: LibraryItem) {
        libraryItems.add(libraryItem)
    }

    fun getBooks(): List<Book> {
        return libraryItems.filterIsInstance<Book>()
    }

    fun getNewspapers(): List<Newspaper> {
        return libraryItems.filterIsInstance<Newspaper>()
    }

    fun getDiscs(): List<Disc> {
        return libraryItems.filterIsInstance<Disc>()
    }

    fun digitization(libraryItem: LibraryItem){
        addItem(digitizationCabinet.digitization(libraryItem))
    }
}