package pl.edu.uj.reviewexchange.view_models

import android.content.Context
import androidx.lifecycle.ViewModel
import pl.edu.uj.reviewexchange.repository.FirebaseRepository

class SettingsViewModel : ViewModel() {
    private val repository = FirebaseRepository()

    fun updateUserData(data : Map<String, String>, context: Context) = repository.updateUserData(data, context)
//
//    fun changePassword(password : String) =
}