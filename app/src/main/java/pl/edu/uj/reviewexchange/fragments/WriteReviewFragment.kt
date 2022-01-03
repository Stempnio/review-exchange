package pl.edu.uj.reviewexchange.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import pl.edu.uj.reviewexchange.R
import pl.edu.uj.reviewexchange.databinding.FragmentWriteReviewBinding
import pl.edu.uj.reviewexchange.view_models.WriteReviewViewModel


class WriteReviewFragment : Fragment(R.layout.fragment_write_review) {

    private var _binding : FragmentWriteReviewBinding? = null
    private val binding get() = _binding!!

    private val writeReviewVM by viewModels<WriteReviewViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWriteReviewBinding.inflate(layoutInflater, container, false)

        val bookId = arguments?.getString("book_id").toString()
        val bookName = arguments?.getString("book_name").toString()
        val bookAuthor = arguments?.getString("book_author").toString()

        binding.tvWriteReviewAuthor.text = bookAuthor
        binding.tvWriteReviewTitle.text = bookName

        binding.btnFragmentWriteReviewSubmit.setOnClickListener {
            val review = binding.etFragmentWriteReview.text.trim().toString()
            if(review != "") {
                writeReviewVM.submitReview(review, bookId, bookName, FirebaseAuth.getInstance().currentUser!!.email!!, requireContext())
                findNavController().navigate(WriteReviewFragmentDirections.actionWriteReviewFragmentToSearchFragment())
            } else {
                TODO("empty review")
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}