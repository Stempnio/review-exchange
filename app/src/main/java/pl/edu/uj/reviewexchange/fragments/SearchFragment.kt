package pl.edu.uj.reviewexchange.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import pl.edu.uj.reviewexchange.adapters.BookRecyclerViewAdapter
import pl.edu.uj.reviewexchange.databinding.FragmentSearchBinding
import pl.edu.uj.reviewexchange.models.BookPlaceholder

class SearchFragment : Fragment() {
    private var _binding : FragmentSearchBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)

        val recyclerViewBookList = binding.recyclerViewBookList
        recyclerViewBookList.layoutManager = LinearLayoutManager(context)
        recyclerViewBookList.adapter = BookRecyclerViewAdapter(BookPlaceholder.BOOKS)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}