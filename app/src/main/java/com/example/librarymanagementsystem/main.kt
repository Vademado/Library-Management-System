package com.example.librarymanagementsystem

fun main() {
    val library = Library("библиотека им. А.П. Чехова")

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
        Newspaper("The New York Times", 12345),
        Newspaper("The Washington Post", 67890),
        Newspaper("The Guardian", 54321),
        Newspaper("Le Monde", 98765),
        Newspaper("Комсомольская правда", 11223),
        Newspaper("Известия", 44556),
        Newspaper("The Times", 77889),
        Newspaper("Financial Times", 33445),
        Newspaper("The Moscow Times", 66778),
        Newspaper("El País", 99001)
    )
    listOfLibraryItems.forEach { library.addItem(it) }

    val libraryManagementSystem = LibraryManagementSystem(library)

    libraryManagementSystem.startMaintenance()
}