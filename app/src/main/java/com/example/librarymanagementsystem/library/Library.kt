package com.example.librarymanagementsystem.library

import com.example.librarymanagementsystem.library.items.book.Book
import com.example.librarymanagementsystem.library.services.items.digitization.BooksAndNewspapersDigitization
import com.example.librarymanagementsystem.library.items.DigitalMedia
import com.example.librarymanagementsystem.library.services.items.digitization.Digitizationable
import com.example.librarymanagementsystem.library.items.disc.Disc
import com.example.librarymanagementsystem.library.items.LibraryItem
import com.example.librarymanagementsystem.library.services.library.LibraryPurchaseManager
import com.example.librarymanagementsystem.library.items.Named
import com.example.librarymanagementsystem.library.items.newspaper.Newspaper

class Library(override val name: String, val purchaseManager: LibraryPurchaseManager,
              private val digitizationCabinet: BooksAndNewspapersDigitization<Digitizationable<Book, DigitalMedia>, Digitizationable<Newspaper, DigitalMedia>>
) : Named {
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