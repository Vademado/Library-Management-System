package com.example.librarymanagementsystem.library.items.book

import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.getAndroidSystemResources
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
            return  getAndroidSystemResources().getString(R.string.book_take_home_success, id)
        }
        return getAndroidSystemResources().getString(R.string.book_unavailable, id)
    }

    override fun readInHall(): String {
        if (isAccessable) {
            isAccessable = false
            return getAndroidSystemResources().getString(R.string.book_read_in_hall_success)
        }
        return getAndroidSystemResources().getString(R.string.book_unavailable, id)
    }

    override fun returnItem(): String {
        if (isAccessable) {
            return getAndroidSystemResources().getString(R.string.book_return_fail, id)
        }
        isAccessable = true
        return getAndroidSystemResources().getString(R.string.book_return_success, id)
    }

}