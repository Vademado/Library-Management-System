package com.example.librarymanagementsystem.store

import com.example.librarymanagementsystem.library.items.LibraryItem
import com.example.librarymanagementsystem.library.items.book.Book
import com.example.librarymanagementsystem.library.items.disc.Disc
import com.example.librarymanagementsystem.library.items.newspaper.Newspaper

class BookStore(bookStoreItems: MutableList<Book> = mutableListOf()) :
    Store<Book>(bookStoreItems)

class NewspaperStore(newspaperStoreItems: MutableList<Newspaper> = mutableListOf()) :
    Store<Newspaper>(newspaperStoreItems)

class DiscStore(discStoreItems: MutableList<Disc> = mutableListOf()) :
    Store<Disc>(discStoreItems)

abstract class Store<T : LibraryItem>(val storeItems: MutableList<T>) {
    open fun sell(itemIndex: Int): T {
        require(storeItems.isNotEmpty()) { "Cannot sell from empty store" }
        require(itemIndex in 0 until storeItems.size) { "Index $itemIndex out of range must be in 0..${storeItems.size - 1} " }
        return storeItems.removeAt(itemIndex)
    }
}