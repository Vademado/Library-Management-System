package com.example.librarymanagementsystem

class Newspaper(
    override val name: String,
    val issueNumber: Int,
    override var accessibility: Boolean = true
) : LibraryItem(), ReadableInHall, Returnable {

    override val id = instanceCount

    init {
        ++instanceCount
    }

    override fun readInHall(): String {
        if (accessibility) {
            accessibility = false
            return "Газету $id взяли в читальный зал"
        }
        return "В данный момент газета $id не доступна"
    }

    override fun returnItem(): String {
        if (accessibility) {
            return "Газета $id находится в библиотеке"
        }
        accessibility = true
        return "Газета $id возвращена"
    }
}