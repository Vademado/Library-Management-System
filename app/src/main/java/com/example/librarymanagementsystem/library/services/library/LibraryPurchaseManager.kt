package com.example.librarymanagementsystem.library.services.library

import com.example.librarymanagementsystem.library.items.book.Book
import com.example.librarymanagementsystem.library.items.disc.Disc
import com.example.librarymanagementsystem.library.items.LibraryItem
import com.example.librarymanagementsystem.library.manager.Manager
import com.example.librarymanagementsystem.library.items.newspaper.Newspaper
import com.example.librarymanagementsystem.store.Store

data class LibraryPurchaseManager(
    private val manager: Manager,
    var bookStore: Store<Book>,
    var newspaperStore: Store<Newspaper>,
    var discStore: Store<Disc>
) {
    fun <T: LibraryItem>buy(store: Store<T>, indexItem: Int): T {
        return manager.buy(store, indexItem)
    }
}