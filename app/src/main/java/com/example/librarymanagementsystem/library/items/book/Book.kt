package com.example.librarymanagementsystem.library.items.book

import com.example.librarymanagementsystem.library.items.Borrowable
import com.example.librarymanagementsystem.library.items.LibraryItem
import com.example.librarymanagementsystem.library.items.ReadableInHall
import com.example.librarymanagementsystem.library.items.Returnable

class Book(
    override val name: String,
    var author: String,
    var pageCount: Int,
    override var isAccessable: Boolean = true,
) : LibraryItem(), Borrowable, ReadableInHall, Returnable {

    override val id = instanceCount

    init {
        ++instanceCount
    }

    override fun takeHome(): String {
        if (isAccessable) {
            isAccessable = false
            return "Книгу $id взяли в домой"
        }
        return "В данный момент книгa $id не доступна"
    }

    override fun readInHall(): String {
        if (isAccessable) {
            isAccessable = false
            return "Книгу $id взяли в читальный зал"
        }
        return "В данный момент книгa $id не доступна"
    }

    override fun returnItem(): String {
        if (isAccessable) {
            return "Книга $id уже находится в библиотеке"
        }
        isAccessable = true
        return "Книга $id возвращена в библиотеку"
    }

}