package com.example.librarymanagementsystem

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

enum class ReleaseMonth(val russianName: String) {
    JANUARY("январь"),
    FEBRUARY("февраль"),
    MARCH("март"),
    APRIL("апрель"),
    MAY("май"),
    JUNE("июнь"),
    JULY("июль"),
    AUGUST("август"),
    SEPTEMBER("сентябрь"),
    OCTOBER("октябрь"),
    NOVEMBER("ноябрь"),
    DECEMBER("декабрь");
}