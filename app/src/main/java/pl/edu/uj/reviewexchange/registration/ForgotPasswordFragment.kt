package pl.edu.uj.reviewexchange.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import pl.edu.uj.reviewexchange.databinding.FragmentForgotPasswordBinding
import pl.edu.uj.reviewexchange.fragments.displayMessageToast
import pl.edu.uj.reviewexchange.models.BookViewModel


class ForgotPasswordFragment : Fragment() {
    private var _binding : FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    private val bookVM by viewModels<BookViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordBinding.inflate(layoutInflater, container, false)

        binding.btnForgotPasswordFragmentReset.setOnClickListener {
            val email = binding.etForgotPasswordEmail.text.trim().toString()
            if(email != "") {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            //TODO message
                            displayMessageToast("If email is correct, you will receive message")
                        } else {
                            displayMessageToast("forgot fail")
                        }
                    }
            }
        }

        binding.btnForgotPasswordGoBack.setOnClickListener {
            findNavController().navigate(ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLogInFragment())
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}