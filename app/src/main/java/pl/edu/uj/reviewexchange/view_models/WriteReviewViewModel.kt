package pl.edu.uj.reviewexchange.view_models

import android.content.Context
import androidx.lifecycle.ViewModel
import pl.edu.uj.reviewexchange.repository.FirebaseRepository

class WriteReviewViewModel : ViewModel() {
    private val repository = FirebaseRepository()

    fun submitReview(review: String, book_id: String, book_name : String, user_email : String, context: Context) {
        repository.submitReview(review, book_id, book_name, user_email, context)

    }
}
