package pl.edu.uj.reviewexchange.models

import androidx.lifecycle.ViewModel
import pl.edu.uj.reviewexchange.repository.FirebaseRepository

data class User(var email : String = "",
                var name : String = "",
                var uid : String = "")


class UserViewModel : ViewModel() {
    private val repository = FirebaseRepository()
    val user = repository.getUserData()
}



