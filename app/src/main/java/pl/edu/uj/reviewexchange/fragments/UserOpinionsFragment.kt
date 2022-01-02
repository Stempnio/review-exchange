package pl.edu.uj.reviewexchange.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import pl.edu.uj.reviewexchange.adapters.UserReviewsRecyclerViewAdapter
import pl.edu.uj.reviewexchange.databinding.FragmentUserOpinionsBinding
import pl.edu.uj.reviewexchange.view_models.UserOpinionsViewModel


class UserOpinionsFragment : Fragment() {

    private val userOpinionsVM by viewModels<UserOpinionsViewModel>()

    private var _binding : FragmentUserOpinionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserOpinionsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvUserReviews = binding.rvFragmentUserOpinions
        rvUserReviews.layoutManager = LinearLayoutManager(context)

        val rvUserReviewsAdapter = UserReviewsRecyclerViewAdapter()
        rvUserReviews.adapter = rvUserReviewsAdapter

        userOpinionsVM.reviews.observe(viewLifecycleOwner, {reviews ->
            rvUserReviewsAdapter.setReviewList(reviews)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}