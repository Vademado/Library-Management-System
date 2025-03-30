package com.example.librarymanagementsystem.utils

inline fun <reified T> collectionFilterIsInstance(list: List<*>): List<T> {
    return list.filterIsInstance<T>()
}