package com.example.librarymanagementsystem.library.services.library

import com.example.librarymanagementsystem.library.services.items.Info_provider.BookInfoProvider
import com.example.librarymanagementsystem.library.items.Borrowable
import com.example.librarymanagementsystem.library.services.items.Info_provider.DiscInfoProvider
import com.example.librarymanagementsystem.library.services.items.Info_provider.InfoProvider
import com.example.librarymanagementsystem.library.items.LibraryItem
import com.example.librarymanagementsystem.library.services.items.Info_provider.NewspaperInfoProvider
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.library.items.ReadableInHall
import com.example.librarymanagementsystem.library.items.Returnable
import com.example.librarymanagementsystem.store.Store
import com.example.librarymanagementsystem.getAndroidSystemResources
import com.example.librarymanagementsystem.library.Library

class LibraryManagementSystem(
    private val library: Library,
    private val bookInfoProvider: InfoProvider = BookInfoProvider(),
    private val newspaperInfoProvider: InfoProvider = NewspaperInfoProvider(),
    private val discInfoProvider: InfoProvider = DiscInfoProvider()
) {

    fun startMaintenance() {
        println(getAndroidSystemResources().getString(R.string.greeting, library.name))
        showMainMenu()
    }

    private fun showMainMenu() {
        val listOfMainMenuItems =
            getAndroidSystemResources().getStringArray(R.array.main_menu_items)
        while (true) {
            println(getAndroidSystemResources().getString(R.string.separation))
            println(getAndroidSystemResources().getString(R.string.menu_prompt))
            listOfMainMenuItems.forEach { println(it) }

            val task = readlnOrNull()?.toIntOrNull()
            var listOfItems: List<LibraryItem>
            when (task) {
                1 -> {
                    listOfItems = library.getBooks()
                    showItemsMenu(listOfItems, bookInfoProvider)
                }

                2 -> {
                    listOfItems = library.getNewspapers()
                    showItemsMenu(listOfItems, newspaperInfoProvider)
                }

                3 -> {
                    listOfItems = library.getDiscs()
                    showItemsMenu(listOfItems, discInfoProvider)
                }

                4 -> showManagerMenu()

                5 -> return

                else -> println(
                    getAndroidSystemResources().getString(
                        R.string.menu_invalid_command,
                        listOfMainMenuItems.size
                    )
                )
            }
        }
    }

    private fun showItemsMenu(listOfItems: List<LibraryItem>, infoProvider: InfoProvider) {
        var continueFlag: Boolean = true
        while (continueFlag) {
            println(getAndroidSystemResources().getString(R.string.separation))
            if (listOfItems.isEmpty()) {
                println(getAndroidSystemResources().getString(R.string.library_empty))
                return
            }
            for (i in 0 until listOfItems.size) println(
                "${i + 1}. ${infoProvider.getShortInfo(listOfItems[i])}"
            )
            print(getAndroidSystemResources().getString(R.string.input_item_prompt_library))
            val task = readlnOrNull()
            val itemNumber = if (task.isNullOrEmpty()) null else task?.toIntOrNull() ?: -1
            when (itemNumber) {
                in 1..listOfItems.size -> continueFlag =
                    showItemMenu(listOfItems[itemNumber!! - 1], infoProvider)

                null -> return
                else -> println(
                    getAndroidSystemResources().getString(
                        R.string.invalid_item_number,
                        listOfItems.size
                    )
                )
            }
        }
    }

    private fun showItemMenu(libraryItem: LibraryItem, infoProvider: InfoProvider): Boolean {
        val listOfItemMenuItems =
            getAndroidSystemResources().getStringArray(R.array.item_menu_items)
        while (true) {
            println(getAndroidSystemResources().getString(R.string.separation))
            println(getAndroidSystemResources().getString(R.string.menu_prompt))
            listOfItemMenuItems.forEach { println(it) }
            val task = readlnOrNull()?.toIntOrNull()
            when (task) {
                1 -> {
                    if (libraryItem is Borrowable) {
                        println(">${libraryItem.takeHome()}")
                    } else println(getAndroidSystemResources().getString(R.string.service_not_available))
                }

                2 -> {
                    if (libraryItem is ReadableInHall) {
                        println(">${libraryItem.readInHall()}")
                    } else println(getAndroidSystemResources().getString(R.string.service_not_available))
                }

                3 -> println(">${infoProvider.getDetailedInfo(libraryItem)}")
                4 -> {
                    if (libraryItem is Returnable) {
                        println(">${libraryItem.returnItem()}")
                    } else println(getAndroidSystemResources().getString(R.string.service_not_available))
                }

                5 -> return true
                6 -> return false
                else -> println(
                    getAndroidSystemResources().getString(
                        R.string.menu_invalid_command,
                        listOfItemMenuItems.size
                    )
                )
            }
        }
    }

    private fun showManagerMenu() {
        val listOfManagerMenuItems =
            getAndroidSystemResources().getStringArray(R.array.manager_menu_items)
        var continueFlag: Boolean = true
        while (continueFlag) {
            println(getAndroidSystemResources().getString(R.string.separation))
            println(getAndroidSystemResources().getString(R.string.menu_prompt))
            listOfManagerMenuItems.forEach { println(it) }
            val task = readlnOrNull()?.toIntOrNull()
            when (task) {
                1 -> continueFlag = showStoreMenu(library.purchaseManager.bookStore, bookInfoProvider)
                2 -> continueFlag = showStoreMenu(library.purchaseManager.newspaperStore, bookInfoProvider)
                3 -> continueFlag = showStoreMenu(library.purchaseManager.discStore, bookInfoProvider)
                4 -> return
            }
        }
    }

    private fun <T : LibraryItem> showStoreMenu(
        store: Store<T>,
        infoProvider: InfoProvider
    ): Boolean {
        var listOfStoreItems: MutableList<T>
        while (true) {
            println(getAndroidSystemResources().getString(R.string.separation))
            listOfStoreItems = store.storeItems
            if (listOfStoreItems.isEmpty()) {
                println(getAndroidSystemResources().getString(R.string.store_empty))
                return true
            }
            for (i in 0 until listOfStoreItems.size) println(
                "${i + 1}. ${infoProvider.getShortInfo(listOfStoreItems[i])}"
            )
            print(
                getAndroidSystemResources().getString(
                    R.string.input_item_prompt_store,
                    listOfStoreItems.size + 1,
                    listOfStoreItems.size + 2
                )
            )
            val itemNumber = readlnOrNull()?.toIntOrNull() ?: -1
            when (itemNumber) {
                in 1..listOfStoreItems.size -> {
                    val purchasedProduct = library.purchaseManager.buy(store, itemNumber - 1)
                    library.addItem(purchasedProduct)
                    println(
                        getAndroidSystemResources().getString(
                            R.string.store_purchase_success,
                            infoProvider.getShortInfo(purchasedProduct)
                        )
                    )
                }

                listOfStoreItems.size + 1 -> return true
                listOfStoreItems.size + 2 -> return false
                else -> println(
                    getAndroidSystemResources().getString(
                        R.string.invalid_item_number,
                        listOfStoreItems.size + 2
                    )
                )
            }
        }
    }
}

