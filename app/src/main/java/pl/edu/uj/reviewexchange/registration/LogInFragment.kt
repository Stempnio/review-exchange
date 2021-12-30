package pl.edu.uj.reviewexchange.registration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import pl.edu.uj.reviewexchange.activities.MainActivity
import pl.edu.uj.reviewexchange.databinding.FragmentLogInBinding


class LogInFragment : Fragment() {

    private val fbAuth = FirebaseAuth.getInstance()

    private var _binding : FragmentLogInBinding? = null
    private val binding get() = _binding!!

    private val LOG_IN_DEBUG = "LOG_IN_DEBUG"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpLoginOnClick()
        setUpRegistrationOnClick()
    }

    private fun setUpRegistrationOnClick() {
        binding.btnLogInRegister.setOnClickListener {
            findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToRegisterFragment().actionId)
        }
    }

    private fun setUpLoginOnClick() {
        binding.btnLogInLogIn.setOnClickListener {
            val email = binding.etLogInEmail.text.trim().toString()
            val password = binding.etLogInPassword.text.trim().toString()

            if(email != "" && password != "") {
                fbAuth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener { authResult ->

                        if (authResult.user != null) {
                            val intent = Intent(requireContext(), MainActivity::class.java).apply {
                                flags =
                                    (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            }
                            startActivity(intent)
                        }
                    }
                    .addOnFailureListener { exception ->
                        Snackbar.make(requireView(), "Ups... something went wrong", Snackbar.LENGTH_SHORT)
                            .show()
                        Log.e(LOG_IN_DEBUG, exception.message.toString())
                    }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}