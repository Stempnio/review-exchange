package pl.edu.uj.reviewexchange.registration

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import pl.edu.uj.reviewexchange.databinding.FragmentRegisterBinding
import pl.edu.uj.reviewexchange.fragments.displayMessageToast
import pl.edu.uj.reviewexchange.models.User
import pl.edu.uj.reviewexchange.view_models.RegisterViewModel

class RegisterFragment : Fragment() {

    private val REGISTER_DEBUG = "REGISTER_DEBUG"
    private val fbAuth = FirebaseAuth.getInstance()

    private val registerVM by viewModels<RegisterViewModel>()

    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRegisterOnClick()

        setUpGoBackOnClick()
    }

    private fun setUpGoBackOnClick() {
        binding.btnRegistrationGoBack.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLogInFragment())
        }
    }

    private fun setUpRegisterOnClick() {
        binding.btnRegistrationRegister.setOnClickListener {
            val email = binding.etRegistrationEmail.text.trim().toString()
            val password = binding.etRegistrationPassword.text.trim().toString()
            val repeatPassword = binding.etRegistrationRepeatPassword.text.trim().toString()

            if(email != "" && password != "" && repeatPassword != "" && password == repeatPassword) {
                fbAuth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener { authRes ->
                        if(authRes.user != null) {

                            val user = User.Builder(authRes.user!!.uid, authRes.user!!.email!!)
                                .build()

                            registerVM.createNewUser(user)
                            displayMessageToast("Account successfully created!")
                            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLogInFragment())
                        }
                    }
                    .addOnFailureListener { exception ->
                        Snackbar.make(requireView(), exception.message.toString(), Snackbar.LENGTH_SHORT)
                            .show()
                        Log.e(REGISTER_DEBUG, exception.message.toString())
                    }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}