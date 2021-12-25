package pl.edu.uj.reviewexchange.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import pl.edu.uj.reviewexchange.R
import pl.edu.uj.reviewexchange.databinding.FragmentHomeBinding
import pl.edu.uj.reviewexchange.databinding.FragmentWriteReviewBinding


class WriteReviewFragment : Fragment(R.layout.fragment_write_review) {

    private var _binding : FragmentWriteReviewBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWriteReviewBinding.inflate(layoutInflater, container, false)

        val bookId = arguments?.getString("book_id").toString()
        val bookName = arguments?.getString("book_author").toString()
        val bookAuthor = arguments?.getString("book_name").toString()

        binding.tvWriteReviewAuthor.text = bookAuthor
        binding.tvWriteReviewTitle.text = bookName

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}