package pl.edu.uj.reviewexchange.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.edu.uj.reviewexchange.databinding.RowUserReviewBinding
import pl.edu.uj.reviewexchange.models.BookReview

class UserReviewsRecyclerViewAdapter
    : RecyclerView.Adapter<UserReviewsRecyclerViewAdapter.ViewHolder>() {

    private val reviewList = ArrayList<BookReview>()

    fun setReviewList(list : List<BookReview>) {
        reviewList.clear()
        reviewList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserReviewsRecyclerViewAdapter.ViewHolder {
        return ViewHolder(RowUserReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UserReviewsRecyclerViewAdapter.ViewHolder, position: Int) {

        val review = reviewList[position]
        holder.bookName.text = review.book_name
        holder.review.text = review.review
    }

    override fun getItemCount(): Int = reviewList.size

    inner class ViewHolder(binding: RowUserReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val bookName = binding.tvRowUserReviewTitle
        val review = binding.tvRowReviewReview
    }

}