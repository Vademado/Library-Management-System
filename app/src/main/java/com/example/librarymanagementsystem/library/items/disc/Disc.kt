package com.example.librarymanagementsystem.library.items.disc

import com.example.librarymanagementsystem.library.items.Borrowable
import com.example.librarymanagementsystem.library.items.DigitalMedia
import com.example.librarymanagementsystem.library.items.Returnable
import com.example.librarymanagementsystem.utils.DiscType

class Disc(
    override val name: String,
    val discType: DiscType,
    override var isAccessable: Boolean = true,
) : DigitalMedia(), Borrowable, Returnable {

    override val id = instanceCount

    init {
        ++instanceCount
    }

    override fun takeHome(): String {
        if (isAccessable) {
            isAccessable = false
            return "Диск $id взяли в домой"
        }
        return "В данный момент диск $id не доступен"
    }

    override fun returnItem(): String {
        if (isAccessable) {
            return "Диск $id уже находится в библиотеке"
        }
        isAccessable = true
        return "Диск $id возвращен в библиотеку"
    }
}