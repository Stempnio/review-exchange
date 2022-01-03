package pl.edu.uj.reviewexchange.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.edu.uj.reviewexchange.databinding.RowReviewBinding
import pl.edu.uj.reviewexchange.models.BookReview

class BookReviewRecyclerViewAdapter
    : RecyclerView.Adapter<BookReviewRecyclerViewAdapter.ViewHolder>() {

    private val reviewList = ArrayList<BookReview>()

    fun setReviewList(list : List<BookReview>) {
        reviewList.clear()
        reviewList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookReviewRecyclerViewAdapter.ViewHolder {
        return ViewHolder(RowReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BookReviewRecyclerViewAdapter.ViewHolder, position: Int) {

        val review = reviewList[position]
        holder.author.text = review.user_email
        holder.review.text = review.review
    }

    override fun getItemCount(): Int = reviewList.size

    inner class ViewHolder(binding: RowReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val author = binding.tvRowReviewAuthor
        val review = binding.tvRowReviewReview
    }
}