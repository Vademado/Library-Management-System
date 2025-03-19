package com.example.librarymanagementsystem

abstract class InfoProvider : ShortInfoProvider, DetailedInfoProvider {
    override fun getShortInfo(libraryItem: LibraryItem): String {
        return "${libraryItem.name} доступна: ${if (libraryItem.accessibility) "Да" else "Нет"}"
    }
}

interface ShortInfoProvider {
    fun getShortInfo(libraryItem: LibraryItem): String
}

interface DetailedInfoProvider {
    fun getDetailedInfo(libraryItem: LibraryItem): String
}

class BookInfoProvider : InfoProvider() {

    override fun getDetailedInfo(libraryItem: LibraryItem): String {
        require(libraryItem is Book) { "Expected a Book type, but got a non-Book: ${libraryItem::class.simpleName}" }
        return "книга: ${libraryItem.name} (${libraryItem.pageCount} стр.) автора: ${libraryItem.author} с id: ${libraryItem.id} доступна: ${if (libraryItem.accessibility) "Да" else "Нет"}"
    }
}

class NewspaperInfoProvider : InfoProvider() {

    override fun getDetailedInfo(libraryItem: LibraryItem): String {
        require(libraryItem is Newspaper) { "Expected a Newspaper type, but got a non-Newspaper: ${libraryItem::class.simpleName}" }
        return "выпуск: ${libraryItem.issueNumber} газеты ${libraryItem.name} с id: ${libraryItem.id}  достуен: ${if (libraryItem.accessibility) "Да" else "Нет"}"
    }
}

class DiscInfoProvider : InfoProvider() {
    override fun getDetailedInfo(libraryItem: LibraryItem): String {
        require(libraryItem is Disc) { "Expected a Disc type, but got a non-Disc: ${libraryItem::class.simpleName}" }
        return "${libraryItem.discType.name} ${libraryItem.name} достуен: ${if (libraryItem.accessibility) "Да" else "Нет"}"
    }
}

