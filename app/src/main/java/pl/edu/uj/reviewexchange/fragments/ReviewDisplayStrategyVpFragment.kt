package pl.edu.uj.reviewexchange.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pl.edu.uj.reviewexchange.databinding.FragmentReviewDisplayStrategyVpBinding


class ReviewDisplayStrategyVpFragment(private val bookId : Int) : Fragment() {
    private var _binding : FragmentReviewDisplayStrategyVpBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReviewDisplayStrategyVpBinding.inflate(layoutInflater, container, false)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}