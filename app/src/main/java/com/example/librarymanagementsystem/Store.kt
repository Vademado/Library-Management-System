package com.example.librarymanagementsystem


class BookStore: Store<Book> {
    private val bookStoreItems = mutableListOf<Book>()

    override fun sell(): Book {
        TODO("Not yet implemented")
    }
}


class NewspaperStore: Store<Newspaper> {
    private val newspaperStoreItems = mutableListOf<Newspaper>()

    override fun sell(): Newspaper {
        TODO("Not yet implemented")
    }
}

class DiscStore: Store<Disc>{
    private val discStoreItems = mutableListOf<Disc>()

    override fun sell(): Disc {
        TODO("Not yet implemented")
    }

}

interface Store<out T : LibraryItem> {
    fun sell(): T
}