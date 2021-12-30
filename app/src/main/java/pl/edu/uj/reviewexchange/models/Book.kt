package pl.edu.uj.reviewexchange.models

import androidx.lifecycle.ViewModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import pl.edu.uj.reviewexchange.repository.FirebaseRepository

open class BookRealm : RealmObject() {
    @PrimaryKey
    var id : Int = -1
    var name : String = ""
    var author : String = ""
    var description : String = ""
}


data class Book(var id : String = "",
                var name : String = "",
                var author : String = "",
                var description : String = "") {

    class Builder {
        var id : String = ""
        var name : String = ""
        var author : String = ""
        var description : String = ""

        fun id(id : String) = apply {
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


class BookViewModel : ViewModel() {
    private val repository = FirebaseRepository()
    val books = repository.getBooks()
}

//
//object BookPlaceholder {
//    val BOOKS : MutableList<Book> = ArrayList()
//
//    init {
//        for (i in 1..25) {
//            BOOKS.add(Book.Builder()
//                .id(i)
//                .name("Book $i")
//                .author("Author of book $i")
//                .description("Description of book $i")
//                .build())
//        }
//
//    }
//}