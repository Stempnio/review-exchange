package pl.edu.uj.reviewexchange.models

import android.content.Context
import androidx.lifecycle.ViewModel
import pl.edu.uj.reviewexchange.repository.FirebaseRepository

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

    fun getBookName(book_id : String) = repository.getBookName(book_id)

    fun addBook(book : Book, context: Context) = repository.addBook(book, context)
}