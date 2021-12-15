package pl.edu.uj.reviewexchange.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.edu.uj.reviewexchange.databinding.RowBookBinding
import pl.edu.uj.reviewexchange.models.Book

class BookRecyclerViewAdapter(private val booksList: List<Book>)
    : RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookRecyclerViewAdapter.ViewHolder {
        return ViewHolder(RowBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BookRecyclerViewAdapter.ViewHolder, position: Int) {
        val book = booksList[position]
        holder.bookAuthor.text = book.author
        holder.bookName.text = book.name
//        holder.imgViewBook.setImageResource(R.drawable.ic_book)
//        holder.imgButtonReview.setImageResource(R.drawable.ic_review_button)
        holder.imgButtonReview.setOnClickListener {

        }
    }

    override fun getItemCount(): Int = booksList.size

    inner class ViewHolder(binding: RowBookBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgViewBook : ImageView = binding.imageView
        val bookName : TextView = binding.tvBookRowTitle
        val bookAuthor : TextView = binding.tvBookRowAuthor
        val imgButtonReview : ImageButton = binding.imageBtnBookRowReview
    }
}