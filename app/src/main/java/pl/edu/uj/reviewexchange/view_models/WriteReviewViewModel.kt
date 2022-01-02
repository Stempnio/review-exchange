package pl.edu.uj.reviewexchange.view_models

import androidx.lifecycle.ViewModel
import pl.edu.uj.reviewexchange.repository.FirebaseRepository

class WriteReviewViewModel : ViewModel() {
    val repository = FirebaseRepository()

    fun submitReview(review: String, book_id: String) {
        repository.submitReview(review, book_id)
    }
}
