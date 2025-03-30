package com.example.librarymanagementsystem.library.items.newspaper

import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.getAndroidSystemResources
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
            return getAndroidSystemResources().getString(R.string.newspaper_read_in_hall_success, id)
        }
        return getAndroidSystemResources().getString(R.string.newspaper_unavailable, id)
    }

    override fun returnItem(): String {
        if (isAccessable) {
            return getAndroidSystemResources().getString(R.string.newspaper_return_fail, id)
        }
        isAccessable = true
        return getAndroidSystemResources().getString(R.string.newspaper_return_success, id)
    }
}
