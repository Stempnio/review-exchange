package pl.edu.uj.reviewexchange.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pl.edu.uj.reviewexchange.databinding.FragmentBookReviewsBinding

class BookReviewsFragment : Fragment() {
    private var _binding : FragmentBookReviewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookReviewsBinding.inflate(layoutInflater, container, false)

        val bookId = arguments?.getString("book_id").toString()
        val bookName = arguments?.getString("book_author").toString()
        val bookAuthor = arguments?.getString("book_name").toString()

        binding.tvBookReviewAuthor.text = bookAuthor
        binding.tvBookReviewTitle.text = bookName


        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}