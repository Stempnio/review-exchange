package pl.edu.uj.reviewexchange.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import pl.edu.uj.reviewexchange.R
import pl.edu.uj.reviewexchange.databinding.RowBookBinding
import pl.edu.uj.reviewexchange.models.Book
import pl.edu.uj.reviewexchange.models.BookReview

class BookRecyclerViewAdapter(private val booksList: MutableList<Book>)
    : RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder>(), Filterable {

    private var bookFilterList : MutableList<Book> = booksList

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()

                // if empty, whole array is returned
                if (charSearch.isEmpty()) {
                    bookFilterList = booksList
                } else { // filtering book list
                    val filteredList = ArrayList<Book>()
                    for (book in booksList) {
                        if(book.name.lowercase().contains(charSearch.lowercase()))
                            filteredList.add(book)
                    }
                    bookFilterList = filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = bookFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                bookFilterList = results?.values as ArrayList<Book>
                notifyDataSetChanged()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            BookRecyclerViewAdapter.ViewHolder {
        return ViewHolder(RowBookBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BookRecyclerViewAdapter.ViewHolder, position: Int) {
        val book = bookFilterList[position]
        holder.bookAuthor.text = book.author
        holder.bookName.text = book.name
        holder.imgButtonReview.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_searchFragment_to_bookReviewsFragment,
                bundleOf("book_id" to book.id.toString(), "book_name" to book.name, "book_author" to book.author)))
    }

    override fun getItemCount(): Int = bookFilterList.size

    inner class ViewHolder(binding: RowBookBinding) : RecyclerView.ViewHolder(binding.root) {
        val bookName : TextView = binding.tvBookRowTitle
        val bookAuthor : TextView = binding.tvBookRowAuthor
        val imgButtonReview : ImageView = binding.imageBtnBookRowReview
    }

}