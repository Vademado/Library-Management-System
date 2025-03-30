package com.example.librarymanagementsystem

import com.example.librarymanagementsystem.library.Library
import com.example.librarymanagementsystem.library.items.book.Book
import com.example.librarymanagementsystem.library.items.disc.Disc
import com.example.librarymanagementsystem.library.items.newspaper.Newspaper
import com.example.librarymanagementsystem.library.manager.Manager
import com.example.librarymanagementsystem.library.services.items.digitization.BooksAndNewspapersDigitizationCD
import com.example.librarymanagementsystem.library.services.library.LibraryManagementSystem
import com.example.librarymanagementsystem.library.services.library.LibraryPurchaseManager
import com.example.librarymanagementsystem.store.BookStore
import com.example.librarymanagementsystem.store.DiscStore
import com.example.librarymanagementsystem.store.NewspaperStore
import com.example.librarymanagementsystem.utils.DiscType
import com.example.librarymanagementsystem.utils.ReleaseMonth

fun main() {
    val libraryPurchaseManager = LibraryPurchaseManager(
        Manager(),
        BookStore(
            mutableListOf(
                Book("Гарри Поттер и философский камень", "Джоан Роулинг", 432),
                Book("Маленький принц", "Антуан де Сент-Экзюпери", 96),
                Book("Три товарища", "Эрих Мария Ремарк", 384),
                Book("Атлант расправил плечи", "Айн Рэнд", 1168),
                Book("Сто лет одиночества", "Габриэль Гарсиа Маркес", 544)
            )
        ),
        NewspaperStore(
            mutableListOf(
                Newspaper("The New York Times", 1254, ReleaseMonth.JANUARY),
                Newspaper("Комсомольская правда", 42, ReleaseMonth.MARCH),
                Newspaper("Le Monde", 789, ReleaseMonth.APRIL),
                Newspaper("The Guardian", 3021, ReleaseMonth.NOVEMBER),
                Newspaper("Ведомости", 156, ReleaseMonth.SEPTEMBER)
            )
        ),
        DiscStore(
            mutableListOf(
                Disc("Тёмная сторона луны", DiscType.CD),
                Disc("Thriller", DiscType.DVD),
                Disc("Назад в СССР", DiscType.CD),
                Disc("Слухи", DiscType.CD),
                Disc("Nevermind", DiscType.DVD)
            )
        )
    )

    val library = Library(
        "библиотека им. А.П. Чехова",
        libraryPurchaseManager,
        BooksAndNewspapersDigitizationCD()
    )

    val listOfLibraryItems = listOf(
        Book("Война и мир", "Лев Толстой", 1225),
        Book("1984", "Джордж Оруэлл", 328),
        Book("Преступление и наказание", "Фёдор Достоевский", 671),
        Book("Мастер и Маргарита", "Михаил Булгаков", 384),
        Book("Гарри Поттер и философский камень", "Дж. К. Роулинг", 320),
        Book("Маленький принц", "Антуан де Сент-Экзюпери", 96),
        Book("Анна Каренина", "Лев Толстой", 864),
        Book("Улисс", "Джеймс Джойс", 732),
        Book("Сто лет одиночества", "Габриэль Гарсиа Маркес", 417),
        Book("Моби Дик", "Герман Мелвилл", 635),
        Disc("Тёмная сторона Луны", DiscType.CD),
        Disc("Триллер", DiscType.CD),
        Disc("Назад в будущее", DiscType.CD),
        Disc("Властелин колец: Братство кольца", DiscType.DVD),
        Disc("Начало", DiscType.DVD),
        Disc("Эбби Роуд", DiscType.CD),
        Disc("Матрица", DiscType.DVD),
        Disc("Богемская рапсодия", DiscType.CD),
        Disc("Интерстеллар", DiscType.DVD),
        Disc("Побег из Шоушенка", DiscType.DVD),
        Newspaper("The New York Times", 12345, ReleaseMonth.MAY),
        Newspaper("The Washington Post", 67890, ReleaseMonth.DECEMBER),
        Newspaper("The Guardian", 54321, ReleaseMonth.JUNE),
        Newspaper("Le Monde", 98765, ReleaseMonth.OCTOBER),
        Newspaper("Комсомольская правда", 11223, ReleaseMonth.APRIL),
        Newspaper("Известия", 44556, ReleaseMonth.MARCH),
        Newspaper("The Times", 77889, ReleaseMonth.JANUARY),
        Newspaper("Financial Times", 33445, ReleaseMonth.AUGUST),
        Newspaper("The Moscow Times", 66778, ReleaseMonth.FEBRUARY),
        Newspaper("El País", 99001, ReleaseMonth.SEPTEMBER)
    )
    listOfLibraryItems.forEach { library.addItem(it) }

    val libraryManagementSystem = LibraryManagementSystem(library)

    libraryManagementSystem.startMaintenance()
}