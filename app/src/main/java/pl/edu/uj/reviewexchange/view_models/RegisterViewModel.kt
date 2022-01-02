package pl.edu.uj.reviewexchange.view_models

import androidx.lifecycle.ViewModel
import pl.edu.uj.reviewexchange.models.User
import pl.edu.uj.reviewexchange.repository.FirebaseRepository

class RegisterViewModel : ViewModel() {
    private val repository = FirebaseRepository()

    fun createNewUser(user : User) {
        repository.createNewUser(user)
    }
}