package com.example.librarymanagementsystem.library.items.disc

import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.getAndroidSystemResources
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
            return getAndroidSystemResources().getString(R.string.disc_take_home_success, id)
        }
        return getAndroidSystemResources().getString(R.string.disc_unavailable, id)
    }

    override fun returnItem(): String {
        if (isAccessable) {
            return getAndroidSystemResources().getString(R.string.disc_return_fail, id)
        }
        isAccessable = true
        return getAndroidSystemResources().getString(R.string.disc_return_success, id)
    }
}