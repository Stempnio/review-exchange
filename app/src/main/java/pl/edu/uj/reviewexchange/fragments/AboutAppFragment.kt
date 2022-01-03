package pl.edu.uj.reviewexchange.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import pl.edu.uj.reviewexchange.databinding.FragmentAboutAppBinding
import pl.edu.uj.reviewexchange.models.BookViewModel


class AboutAppFragment : Fragment() {
    private var _binding : FragmentAboutAppBinding? = null
    private val binding get() = _binding!!

    private val bookVM by viewModels<BookViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutAppBinding.inflate(layoutInflater, container, false)



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}