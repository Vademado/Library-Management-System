package com.example.librarymanagementsystem

abstract class InfoProvider() : ShortInfoProvider, DetailedInfoProvider {
    override fun getShortInfo(libraryItem: LibraryItem): String {
        return getAndroidSystemResources().getString(R.string.library_item_hort_info, libraryItem.name, if (libraryItem.isAccessable) "Да" else "Нет")
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
        return getAndroidSystemResources().getString(R.string.book_detailed_info, libraryItem.name, libraryItem.pageCount, libraryItem.author, libraryItem.id, if (libraryItem.isAccessable) "Да" else "Нет")
    }
}

class NewspaperInfoProvider() : InfoProvider() {

    override fun getDetailedInfo(libraryItem: LibraryItem): String {
        require(libraryItem is Newspaper) { "Expected a Newspaper type, but got a non-Newspaper: ${libraryItem::class.simpleName}" }
        return getAndroidSystemResources().getString(R.string.newspaper_detailed_info, libraryItem.issueNumber, libraryItem.releaseMonth.russianName, libraryItem.name, libraryItem.id, if (libraryItem.isAccessable) "Да" else "Нет")
    }
}

class DiscInfoProvider() : InfoProvider() {
    override fun getDetailedInfo(libraryItem: LibraryItem): String {
        require(libraryItem is Disc) { "Expected a Disc type, but got a non-Disc: ${libraryItem::class.simpleName}" }
        return getAndroidSystemResources().getString(R.string.disc_detailed_info, libraryItem.discType.name, libraryItem.name, if (libraryItem.isAccessable) "Да" else "Нет")
    }
}

