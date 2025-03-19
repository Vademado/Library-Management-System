package com.example.librarymanagementsystem

class Disc(
    override val name: String,
    val discType: DiscType,
    override var accessibility: Boolean = true
) : LibraryItem(), Borrowable, Returnable {

    override val id = instanceCount

    init {
        ++instanceCount
    }

    override fun takeHome(): String {
        if (accessibility) {
            accessibility = false
            return "Диск $id взяли в домой"
        }
        return "В данный момент диск $id не доступен"
    }

    override fun returnItem(): String {
        if (accessibility) {
            return "Диск $id находится в библиотеке"
        }
        accessibility = true
        return "Диск $id возвращен"
    }
}

enum class DiscType {
    CD, DVD
}