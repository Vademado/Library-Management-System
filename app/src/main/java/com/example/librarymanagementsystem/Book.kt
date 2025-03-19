package com.example.librarymanagementsystem

class Book(
    override val name: String,
    var author: String,
    var pageCount: Int,
    override var accessibility: Boolean = true
) : LibraryItem(), Borrowable, ReadableInHall, Returnable {

    override val id = instanceCount

    init {
        ++instanceCount
    }

    override fun takeHome(): String {
        if (accessibility) {
            accessibility = false
            return "Книгу $id взяли в домой"
        }
        return "В данный момент книгa $id не доступна"
    }

    override fun readInHall(): String {
        if (accessibility) {
            accessibility = false
            return "Книгу $id взяли в читальный зал"
        }
        return "В данный момент книгa $id не доступна"
    }

    override fun returnItem(): String {
        if (accessibility) {
            return "Книга $id находится в библиотеке"
        }
        accessibility = true
        return "Книга $id возвращена"
    }

}