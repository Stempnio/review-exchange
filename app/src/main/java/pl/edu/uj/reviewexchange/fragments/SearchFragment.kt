package pl.edu.uj.reviewexchange.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import pl.edu.uj.reviewexchange.adapters.BookRecyclerViewAdapter
import pl.edu.uj.reviewexchange.databinding.FragmentSearchBinding
import pl.edu.uj.reviewexchange.models.BookViewModel

class SearchFragment : Fragment() {

    private val bookVM by viewModels<BookViewModel>()

    private var _binding : FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)

        val recyclerViewBookList = binding.recyclerViewBookList
        recyclerViewBookList.layoutManager = LinearLayoutManager(context)


        val rvBookAdapter = BookRecyclerViewAdapter()
        recyclerViewBookList.adapter = rvBookAdapter

        bookVM.books.observe(viewLifecycleOwner, {bookList ->
            rvBookAdapter.setBookList(bookList)
//            recyclerViewBookList.adapter = rvBookAdapter
        })

        binding.svFragmentSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                rvBookAdapter.filter.filter(newText)
                return false
            }

        })


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}