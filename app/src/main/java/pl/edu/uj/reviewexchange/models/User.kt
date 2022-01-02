package pl.edu.uj.reviewexchange.models

import androidx.lifecycle.ViewModel
import pl.edu.uj.reviewexchange.repository.FirebaseRepository

data class User(var uid : String = "",
                var email : String = "",
                var name : String = "",
                var surname : String = "",
                var gender : String = "") {

    class Builder(private val uid : String,private val email : String) {
        var name : String = ""
        var surname : String = ""
        var gender : String = ""

        fun name(name : String) = apply {
            this.name = name
        }

        fun surname(surname : String) = apply {
            this.surname = surname
        }

        fun gender(gender : String) = apply {
            this.gender = gender
        }

        fun build() = User(uid, email, name, surname, gender)
     }
}


class UserViewModel : ViewModel() {
    private val repository = FirebaseRepository()
    val user = repository.getUserData()
}



