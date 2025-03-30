package com.example.librarymanagementsystem.library.services.items.Info_provider

import com.example.librarymanagementsystem.library.items.LibraryItem
import com.example.librarymanagementsystem.library.items.book.Book
import com.example.librarymanagementsystem.library.items.disc.Disc
import com.example.librarymanagementsystem.library.items.newspaper.Newspaper

abstract class InfoProvider() : ShortInfoProvider, DetailedInfoProvider {
    override fun getShortInfo(libraryItem: LibraryItem): String {
        return "${libraryItem.name} доступна: ${if (libraryItem.isAccessable) "Да" else "Нет"}"
    }
}

interface ShortInfoProvider {
    fun getShortInfo(libraryItem: LibraryItem): String
}

interface DetailedInfoProvider {
    fun getDetailedInfo(libraryItem: LibraryItem): String
}

class BookInfoProvider() : InfoProvider() {
    override fun getDetailedInfo(libraryItem: LibraryItem): String {
        require(libraryItem is Book) { "Expected a Book type, but got a non-Book: ${libraryItem::class.simpleName}" }
        return "Книга: ${libraryItem.name} (${libraryItem.pageCount} стр.) автора: ${libraryItem.author} с id: ${libraryItem.id} доступна: ${if (libraryItem.isAccessable) "Да" else "Нет"}"
    }
}

class NewspaperInfoProvider() : InfoProvider() {

    override fun getDetailedInfo(libraryItem: LibraryItem): String {
        require(libraryItem is Newspaper) { "Expected a Newspaper type, but got a non-Newspaper: ${libraryItem::class.simpleName}" }
        return "Выпуск: ${libraryItem.issueNumber} месяц выпуска: ${libraryItem.releaseMonth.russianName} газеты ${libraryItem.name} с id: ${libraryItem.id}  достуен: ${if (libraryItem.isAccessable) "Да" else "Нет"}\""
    }
}

class DiscInfoProvider() : InfoProvider() {
    override fun getDetailedInfo(libraryItem: LibraryItem): String {
        require(libraryItem is Disc) { "Expected a Disc type, but got a non-Disc: ${libraryItem::class.simpleName}" }
        return "${libraryItem.discType.name} ${libraryItem.name} достуен: ${if (libraryItem.isAccessable) "Да" else "Нет"}"
    }
}

