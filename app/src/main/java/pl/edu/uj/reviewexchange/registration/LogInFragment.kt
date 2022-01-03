package pl.edu.uj.reviewexchange.registration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import pl.edu.uj.reviewexchange.activities.MainActivity
import pl.edu.uj.reviewexchange.databinding.FragmentLogInBinding
import pl.edu.uj.reviewexchange.fragments.displayMessageToast


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

        setUpLoginOnClick()
        setUpRegistrationOnClick()
        setUpForgotPasswordOnClick()

        return binding.root
    }

    private fun setUpRegistrationOnClick() {
        binding.btnLogInRegister.setOnClickListener {
            findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToRegisterFragment().actionId)
        }
    }

    private fun setUpForgotPasswordOnClick() {
        binding.btnLogInForgotPassword.setOnClickListener {
            findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToForgotPasswordFragment())
        }
    }

    private fun setUpLoginOnClick() {
        binding.btnLogInLogIn.setOnClickListener {
            val email = binding.etLogInEmail.text.trim().toString()
            val password = binding.etLogInPassword.text.trim().toString()

            if(email != "" && password != "") {

                fbAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(LOG_IN_DEBUG, "signInWithEmail:success")
                            val intent = Intent(requireContext(), MainActivity::class.java).apply {
                                flags =
                                    (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            }
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(LOG_IN_DEBUG, "signInWithEmail:failure", task.exception)
                            displayMessageToast(task.exception.toString())
                        }
                    }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}