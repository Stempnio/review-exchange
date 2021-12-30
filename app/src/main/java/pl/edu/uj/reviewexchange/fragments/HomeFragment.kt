package pl.edu.uj.reviewexchange.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import pl.edu.uj.reviewexchange.R
import pl.edu.uj.reviewexchange.databinding.FragmentHomeBinding
import pl.edu.uj.reviewexchange.models.UserViewModel


class HomeFragment : Fragment() {

    private val userVM by viewModels<UserViewModel>()

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        binding.cvHomeFragmentUserOpinions.setOnClickListener {
            view -> view.findNavController().navigate(R.id.action_homeFragment_to_userOpinionsFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userVM.user.observe(viewLifecycleOwner, {user ->
            val header = "Hello " + user.name
            binding.tvHomeHeader.text = header
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}