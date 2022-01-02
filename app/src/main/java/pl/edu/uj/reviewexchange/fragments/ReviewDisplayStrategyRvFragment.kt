package pl.edu.uj.reviewexchange.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import pl.edu.uj.reviewexchange.adapters.BookReviewRecyclerViewAdapter
import pl.edu.uj.reviewexchange.databinding.FragmentReviewDisplayStrategyRvBinding
import pl.edu.uj.reviewexchange.models.BookReviewViewModel

class ReviewDisplayStrategyRvFragment(private val book_id : String) : Fragment() {

    private var _binding : FragmentReviewDisplayStrategyRvBinding? = null
    private val binding get() = _binding!!

    private val bookReviewVM by viewModels<BookReviewViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReviewDisplayStrategyRvBinding.inflate(layoutInflater, container, false)

        val recyclerView = binding.rvFragmentDisplayStrategyRv
        recyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = BookReviewRecyclerViewAdapter()
        recyclerView.adapter = adapter

        bookReviewVM.getReviews(book_id).observe(viewLifecycleOwner, { reviewList ->
            adapter.setReviewList(reviewList)
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}