package com.example.librarymanagementsystem.library.services.library

import com.example.librarymanagementsystem.library.services.items.Info_provider.BookInfoProvider
import com.example.librarymanagementsystem.library.items.Borrowable
import com.example.librarymanagementsystem.library.services.items.Info_provider.DiscInfoProvider
import com.example.librarymanagementsystem.library.services.items.Info_provider.InfoProvider
import com.example.librarymanagementsystem.library.items.LibraryItem
import com.example.librarymanagementsystem.library.services.items.Info_provider.NewspaperInfoProvider
import com.example.librarymanagementsystem.library.items.ReadableInHall
import com.example.librarymanagementsystem.library.items.Returnable
import com.example.librarymanagementsystem.store.Store
import com.example.librarymanagementsystem.library.Library

class LibraryManagementSystem(
    private val library: Library,
    private val bookInfoProvider: InfoProvider = BookInfoProvider(),
    private val newspaperInfoProvider: InfoProvider = NewspaperInfoProvider(),
    private val discInfoProvider: InfoProvider = DiscInfoProvider()
) {

    fun startMaintenance() {
        println("Добро пожаловать в ${library.name}.")
        showMainMenu()
    }

    private fun showMainMenu() {
        while (true) {
            println("###########################################################")
            println(
                """
            Чтобы продолжить, выберите одну из команд:
            1.Показать книги 
            2.Показать газеты 
            3.Показать диски
            4.Менеджер
            5.Завершить обслуживание
        """.trimIndent()
            )

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

                else -> println(">Неверный номер команды, пробуйте снова в диапозоне от 1 до 5")
            }
        }
    }

    private fun showItemsMenu(listOfItems: List<LibraryItem>, infoProvider: InfoProvider) {
        var continueFlag = true
        while (continueFlag) {
            println("###########################################################")
            if (listOfItems.isEmpty()) {
                println(">Библиотека пуста")
                return
            }
            for (i in 0 until listOfItems.size) println(
                "${i + 1}. ${infoProvider.getShortInfo(listOfItems[i])}"
            )
            println("Введите номер позиции для предоставления более подробной информации или нажмите Enter для выхода в главное меню: ")
            val task = readlnOrNull()
            val itemNumber = if (task.isNullOrEmpty()) null else task?.toIntOrNull() ?: -1
            when (itemNumber) {
                in 1..listOfItems.size -> continueFlag =
                    showItemMenu(listOfItems[itemNumber!! - 1], infoProvider)

                null -> return
                else -> println(">Неверный номер позиции, пробуйте снова в диапозоне от 1 до ${listOfItems.size}")
            }
        }
    }

    private fun showItemMenu(libraryItem: LibraryItem, infoProvider: InfoProvider): Boolean {
        while (true) {
            println("###########################################################")
            println(
                """
            Чтобы продолжить, выберите одну из команд:
            1.Взять домой 
            2.Читать в читальном зале 
            3.Показать подробную информацию
            4.Вернуть
            5.Вернуться к списку 
            6.Вернуться в главное меню
        """.trimIndent()
            )
            val task = readlnOrNull()?.toIntOrNull()
            when (task) {
                1 -> {
                    if (libraryItem is Borrowable) {
                        println(">${libraryItem.takeHome()}")
                    } else println(">Услуга не доступна")
                }

                2 -> {
                    if (libraryItem is ReadableInHall) {
                        println(">${libraryItem.readInHall()}")
                    } else println(">Услуга не доступна")
                }

                3 -> println(">${infoProvider.getDetailedInfo(libraryItem)}")
                4 -> {
                    if (libraryItem is Returnable) {
                        println(">${libraryItem.returnItem()}")
                    } else println(">Услуга не доступна")
                }

                5 -> return true
                6 -> return false
                else -> println(">Неверный номер команды, пробуйте снова в диапозоне от 1 до 6")
            }
        }
    }

    private fun showManagerMenu() {
        while (true) {
            println("###########################################################")
            println(
                """
            Чтобы продолжить, выберите одну из команд:
            1.Приобрести книгу 
            2.Приобрести газету 
            3.Приобрести диск
            4.Вернуться в главное меню
        """.trimIndent()
            )
            val task = readlnOrNull()?.toIntOrNull()
            when (task) {
                1 -> showStoreMenu(library.purchaseManager.bookStore, bookInfoProvider)

                2 -> showStoreMenu(library.purchaseManager.newspaperStore, bookInfoProvider)

                3 -> showStoreMenu(library.purchaseManager.discStore, bookInfoProvider)

                4 -> return
            }
        }
    }

    private fun <T : LibraryItem> showStoreMenu(
        store: Store<T>,
        infoProvider: InfoProvider
    ){
        var listOfStoreItems: MutableList<T>
        while (true) {
            println("###########################################################")
            listOfStoreItems = store.storeItems
            if (listOfStoreItems.isEmpty()) {
                println(">Магазин пуст")
                return
            }
            for (i in 0 until listOfStoreItems.size) println(
                "${i + 1}. ${infoProvider.getShortInfo(listOfStoreItems[i])}"
            )
            println("Введите номер позиции для покупки библиоткой экземпляраили или нажмите Enter для возвращения к списку магазинов: ")
            val task = readlnOrNull()
            val itemNumber = if (task.isNullOrEmpty()) null else task?.toIntOrNull() ?: -1
            when (itemNumber) {
                in 1..listOfStoreItems.size -> {
                    val purchasedProduct = library.purchaseManager.buy(store, itemNumber!! - 1)
                    library.addItem(purchasedProduct)
                    println(">Успешно приобретено: ${infoProvider.getShortInfo(purchasedProduct)}")
                }

                null -> return
                else -> println(">Неверный номер позиции, пробуйте снова в диапазоне от 1 до ${listOfStoreItems.size}")
            }
        }
    }
}

