package com.example.librarymanagementsystem

class Disc(
    override val name: String,
    val discType: DiscType,
    override var isAccessable: Boolean = true,
) : LibraryItem(), Borrowable, Returnable, DigitalMedia {

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

enum class DiscType {
    CD, DVD
}

interface DigitalMedia