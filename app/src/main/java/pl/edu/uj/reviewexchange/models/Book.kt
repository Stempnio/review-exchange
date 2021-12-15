package pl.edu.uj.reviewexchange.models

//class Book {
//    var id : Int = -1
//    var name : String = ""
//    var author : String = ""
//}

data class Book(var id : Int = -1,
                var name : String = "",
                var author : String = "")

object BookPlaceholder {
    val BOOKS : MutableList<Book> = ArrayList()

    init {
        for (i in 1..25) {
            BOOKS.add(Book(i, "Book $i", "Author $i"))
        }
    }
}