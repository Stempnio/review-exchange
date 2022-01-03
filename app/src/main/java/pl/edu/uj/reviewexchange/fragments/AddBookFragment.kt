package pl.edu.uj.reviewexchange.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import pl.edu.uj.reviewexchange.databinding.FragmentAddBookBinding
import pl.edu.uj.reviewexchange.models.Book
import pl.edu.uj.reviewexchange.models.BookViewModel

class AddBookFragment : Fragment() {

    private var _binding : FragmentAddBookBinding? = null
    private val binding get() = _binding!!

    private val bookVM by viewModels<BookViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBookBinding.inflate(layoutInflater, container, false)

        binding.btnAddBookSubmitBook.setOnClickListener {
            val name = binding.etAddBookName.text.trim().toString()
            val author = binding.etAddBookAutor.text.trim().toString()

            if(name != "" && author != "") {
                val book_id = "$author $name"

                bookVM.addBook(Book.Builder()
                    .id(book_id)
                    .author(author)
                    .name(name)
                    .build(), requireContext())
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}