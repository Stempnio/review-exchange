package pl.edu.uj.reviewexchange.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import pl.edu.uj.reviewexchange.adapters.BookReviewViewPager2Adapter
import pl.edu.uj.reviewexchange.databinding.FragmentReviewDisplayStrategyVpBinding
import pl.edu.uj.reviewexchange.models.BookReview

class ReviewDisplayStrategyVpFragment(private val reviews : LiveData<MutableList<BookReview>>) : Fragment() {
    private var _binding : FragmentReviewDisplayStrategyVpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReviewDisplayStrategyVpBinding.inflate(layoutInflater, container, false)

        val viewpager2 = binding.vpFragmentBookDisplayStrategyVp
        val viewpager2Adapter = BookReviewViewPager2Adapter()

        viewpager2.adapter = viewpager2Adapter

        reviews.observe(viewLifecycleOwner, {reviewList ->
            viewpager2Adapter.setReviewList(reviewList)
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}