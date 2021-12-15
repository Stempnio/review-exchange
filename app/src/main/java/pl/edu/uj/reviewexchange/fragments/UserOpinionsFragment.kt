package pl.edu.uj.reviewexchange.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.edu.uj.reviewexchange.R
import pl.edu.uj.reviewexchange.databinding.FragmentUserOpinionsBinding


class UserOpinionsFragment : Fragment() {

    private var _binding : FragmentUserOpinionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserOpinionsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}