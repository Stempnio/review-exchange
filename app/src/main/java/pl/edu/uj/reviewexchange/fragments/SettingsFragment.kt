package pl.edu.uj.reviewexchange.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import pl.edu.uj.reviewexchange.databinding.FragmentSettingsBinding
import pl.edu.uj.reviewexchange.view_models.SettingsViewModel

class SettingsFragment : Fragment() {

    private val auth = FirebaseAuth.getInstance()

    private val settingsVM by viewModels<SettingsViewModel>()

    private var _binding : FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)

        binding.btnSettigsFragmentApplyChanges.setOnClickListener {
            val name = binding.etSettingsFragmentName.text.trim().toString()
            val surname = binding.etSettingsFragmentSurname.text.trim().toString()
            var gender : String = ""
            val rgGenderId = binding.rgSettingsFragmentGender.checkedRadioButtonId
            when(rgGenderId) {
                binding.rbSettingsFragmentMale.id -> gender = "Male"
                binding.rbSettingsFragmentFemale.id -> gender = "Female"
                binding.rbSettingsFragmentAnother.id -> gender = "Another"
            }

            val map = mapOf("name" to name, "surname" to surname, "gender" to gender)
            settingsVM.updateUserData(map, requireContext())
        }

        binding.btnSettingsFragmentChangePassword.setOnClickListener {
            val password = binding.etSettingsFragmentPassword.text.toString()
            val repeatPassword = binding.etSettingsFragmentRepeatPassword.text.toString()

            if(password == repeatPassword && password != "") {
                FirebaseAuth.getInstance().currentUser!!.updatePassword(password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            displayMessageToast("Password changed successfully")
                        } else {
                            displayMessageToast("Password change fail")
                        }
                    }
            }
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}