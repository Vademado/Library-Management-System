package com.example.librarymanagementsystem.library.manager

import com.example.librarymanagementsystem.library.items.LibraryItem
import com.example.librarymanagementsystem.store.Store

class Manager {
    fun <T : LibraryItem> buy(store: Store<T>, indexItem: Int): T {
        return store.sell(indexItem)
    }
}
