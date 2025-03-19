package com.example.librarymanagementsystem

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
            4.Завершить обслуживание
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

                4 -> return

                else -> println(">Неверный номер команды, пробуйте снова в диапозоне от 1 до 4")
            }
        }
    }

    private fun showItemsMenu(listOfItems: List<LibraryItem>, infoProvider: InfoProvider) {
        var continueFlag: Boolean = true
        while (continueFlag) {
            println("###########################################################")
            if (listOfItems.isEmpty()) {
                println(">Библиотека пуста")
                return
            }
            for (i in 0 until listOfItems.size) println(
                "${i + 1}. ${infoProvider.getShortInfo(listOfItems[i])}"
            )
            print(
                """
            Введите номер позиции для предоставления более подробной информации или нажмите Enter для выхода в главное меню: 
        """.trimIndent()
            )
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
}

