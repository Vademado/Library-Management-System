package com.example.librarymanagementsystem.library.items.newspaper

import com.example.librarymanagementsystem.library.items.LibraryItem
import com.example.librarymanagementsystem.library.items.ReadableInHall
import com.example.librarymanagementsystem.library.items.Returnable
import com.example.librarymanagementsystem.utils.ReleaseMonth

class Newspaper(
    override val name: String,
    val issueNumber: Int,
    val releaseMonth: ReleaseMonth,
    override var isAccessable: Boolean = true,
) : LibraryItem(), ReadableInHall, Returnable {

    override val id = instanceCount

    init {
        ++instanceCount
    }

    override fun readInHall(): String {
        if (isAccessable) {
            isAccessable = false
            return "Газету $id взяли в читальный зал"
        }
        return "В данный момент газета $id не доступна"
    }

    override fun returnItem(): String {
        if (isAccessable) {
            return "Газета $id уже находится в библиотеке"
        }
        isAccessable = true
        return "Газета $id возвращена в библиотеку"
    }
}
