package com.example.librarymanagementsystem.library.services.items.digitization

import com.example.librarymanagementsystem.library.items.DigitalMedia
import com.example.librarymanagementsystem.library.items.LibraryItem
import com.example.librarymanagementsystem.library.items.book.Book
import com.example.librarymanagementsystem.library.items.disc.Disc
import com.example.librarymanagementsystem.library.items.newspaper.Newspaper
import com.example.librarymanagementsystem.utils.DiscType

interface Digitizationable<in T : LibraryItem, out R : DigitalMedia> {
    fun digitization(libraryItem: T): R
}


class BooksDigitizationCD : Digitizationable<Book, Disc> {
    override fun digitization(libraryItem: Book): Disc {
        return Disc(libraryItem.name, DiscType.CD)
    }
}

class NewspapersDigitizationCD : Digitizationable<Newspaper, Disc> {
    override fun digitization(libraryItem: Newspaper): Disc {
        return Disc(libraryItem.name, DiscType.CD)
    }

}

abstract class BooksAndNewspapersDigitization<out T : Digitizationable<Book, DigitalMedia>, out R : Digitizationable<Newspaper, DigitalMedia>>(
    protected val booksDigitization: T,
    protected val newspapersDigitization: R
) {
    abstract fun digitization(libraryItem: LibraryItem) : DigitalMedia
}

open class BooksAndNewspapersDigitizationDisc(
    booksDigitization: Digitizationable<Book, Disc>,
    newspapersDigitization: Digitizationable<Newspaper, Disc>
) : BooksAndNewspapersDigitization<Digitizationable<Book, Disc>, Digitizationable<Newspaper, Disc>>(
    booksDigitization,
    newspapersDigitization
) {
    override fun digitization(libraryItem: LibraryItem): Disc {
        when (libraryItem) {
            is Book -> return booksDigitization.digitization(libraryItem)
            is Newspaper -> return newspapersDigitization.digitization(libraryItem)
            else -> throw IllegalArgumentException("Expected a Book or Newspaper type, but got: ${libraryItem::class.simpleName}")
        }
    }
}

class BooksAndNewspapersDigitizationCD :
    BooksAndNewspapersDigitizationDisc(
        BooksDigitizationCD(),
        NewspapersDigitizationCD()
    )