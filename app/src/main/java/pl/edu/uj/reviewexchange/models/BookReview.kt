package pl.edu.uj.reviewexchange.models

import io.realm.RealmObject

data class BookReview(var userId : String = "", var bookId : Int = -1,
                       var review : String = "") {
}

open class BookReviewRealm() : RealmObject() {
    var userId : String = ""
    var bookId : Int = -1
    var review : String = ""

    // TODO user
}

object BookReviewPlaceHolder {
    val BOOK_REVIEWS : MutableList<BookReview> = ArrayList()

    fun getBookReviews(bookId: Int) : MutableList<BookReview> {
        val reviews : MutableList<BookReview> = ArrayList()
        BOOK_REVIEWS.forEach {
            if(it.bookId == bookId)
                reviews.add(it)
        }

        return reviews
    }


    init {
        BOOK_REVIEWS.add(BookReview().apply {
            this.userId = "tmp user"
            this.bookId = 1
            this.review = "asdkfjhaskjdhfkjasdasdfkjhaskldfhkjasdhfkjahsdkfjhaskjdfhkjsahdfjkashdkfjhaskjdfhaskjdhfkjasdfkjasfjkhsjkdfasjkdf"
        })
        for (i in 1..5) {
            BOOK_REVIEWS.add(BookReview().apply {
                this.userId = "user_$i"
                this.bookId = 1
                this.review = "review of book: 1, from userID: $i"
            })
        }

    }
}