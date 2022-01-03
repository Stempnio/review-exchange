package pl.edu.uj.reviewexchange.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pl.edu.uj.reviewexchange.repository.FirebaseRepository


data class BookReview(var id : String = "",
                      var book_id : String = "",
                      var book_name : String = "",
                      var user_id : String = "",
                      var user_email : String = "",
                      var review : String = "")

class BookReviewViewModel : ViewModel() {
    private val repository = FirebaseRepository()

    fun getReviews(book_id : String) : LiveData<MutableList<BookReview>> {
        return repository.getReviewsByBookId(book_id)
    }

    fun bookHasReviews(book_id: String) : LiveData<Boolean> {
        return repository.bookHasReviews(book_id)
    }
}