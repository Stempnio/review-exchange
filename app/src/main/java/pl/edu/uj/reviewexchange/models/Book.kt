package pl.edu.uj.reviewexchange.models

//class Book {
//    var id : Int = -1
//    var name : String = ""
//    var author : String = ""
//}

data class Book(var id : Int = -1,
                var name : String = "",
                var author : String = "",
                var description : String = "") {

    class Builder {
        var id : Int = -1
        var name : String = ""
        var author : String = ""
        var description : String = ""

        fun id(id : Int) = apply {
            this.id = id
        }

        fun name(name : String) = apply {
            this.name = name
        }

        fun author(author : String) = apply {
            this.author = author
        }

        fun description(description: String) = apply {
            this.description = description
        }

        fun build() = Book(id, name, author, description)
    }
}




object BookPlaceholder {
    val BOOKS : MutableList<Book> = ArrayList()

    init {
        for (i in 1..25) {
            BOOKS.add(Book.Builder()
                .id(i)
                .name("Book $i")
                .author("Author of book $i")
                .description("Description of book $i")
                .build())

//            BOOKS.add(Book(i, "Book $i", "Author $i"))
        }
    }
}