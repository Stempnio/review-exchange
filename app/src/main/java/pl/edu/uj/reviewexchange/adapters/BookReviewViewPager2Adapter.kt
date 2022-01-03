package pl.edu.uj.reviewexchange.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.edu.uj.reviewexchange.databinding.ReviewViewpager2Binding
import pl.edu.uj.reviewexchange.models.BookReview

class BookReviewViewPager2Adapter()
    : RecyclerView.Adapter<BookReviewViewPager2Adapter.ViewHolder>() {

    private val reviewList = ArrayList<BookReview>()

    fun setReviewList(list : List<BookReview>) {
        reviewList.clear()
        reviewList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookReviewViewPager2Adapter.ViewHolder {
        return ViewHolder(ReviewViewpager2Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BookReviewViewPager2Adapter.ViewHolder, position: Int) {

        val review = reviewList[position]
        holder.author.text = review.user_email
        holder.review.text = review.review
    }

    override fun getItemCount(): Int = reviewList.size

    inner class ViewHolder(binding : ReviewViewpager2Binding) : RecyclerView.ViewHolder(binding.root) {
        val author = binding.tvReviewViewPager2Author
        val review = binding.tvReviewViewPager2Review
    }
}