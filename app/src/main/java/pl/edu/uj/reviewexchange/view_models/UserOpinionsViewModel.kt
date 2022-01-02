package pl.edu.uj.reviewexchange.view_models

import androidx.lifecycle.ViewModel
import pl.edu.uj.reviewexchange.repository.FirebaseRepository


// TODO only displaying user info
class UserOpinionsViewModel : ViewModel() {

    private val repository = FirebaseRepository()
    val reviews = repository.getUserReviews()

}