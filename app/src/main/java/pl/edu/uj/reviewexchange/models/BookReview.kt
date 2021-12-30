package pl.edu.uj.reviewexchange.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pl.edu.uj.reviewexchange.repository.FirebaseRepository

// TODO change that all

data class BookReview(var id : String = "",
                      var bookId : String = "",
                      var user_id : String = "",
                      var review : String = "") {
}

class BookReviewViewModel : ViewModel() {
    private val repository = FirebaseRepository()
//    val reviews = repository.getReviewsByBookId("!23")

    fun getReviews(book_id : String) : LiveData<MutableList<BookReview>> {
        return repository.getReviewsByBookId(book_id)
    }
}



//object BookReviewPlaceHolder {
//    val BOOK_REVIEWS : MutableList<BookReview> = ArrayList()
//
//    fun getBookReviews(bookId: Int) : MutableList<BookReview> {
//        val reviews : MutableList<BookReview> = ArrayList()
//        BOOK_REVIEWS.forEach {
//            if(it.bookId == bookId)
//                reviews.add(it)
//        }
//
//        return reviews
//    }
//
//
//    init {
//        BOOK_REVIEWS.add(BookReview().apply {
//            this.userId = "tmp user"
//            this.bookId = 1
//            this.review = "asdkfjhaskjdhfkjasdasdfkjhaskldfhkjasdhfkjahsdkfjhaskjdfhkjsahdfjkashdkfjhaskjdfhaskjdhfkjasdfkjasfjkhsjkdfasjkdf"
//        })
//        for (i in 1..5) {
//            BOOK_REVIEWS.add(BookReview().apply {
//                this.userId = "user_$i"
//                this.bookId = 1
//                this.review = "review of book: 1, from userID: $i"
//            })
//        }
//
//    }
//}