package pl.edu.uj.reviewexchange.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import pl.edu.uj.reviewexchange.models.Book
import pl.edu.uj.reviewexchange.models.BookReview
import pl.edu.uj.reviewexchange.models.User

class FirebaseRepository {
    private val FIREBASE_REPOSITORY_DEBUG = "FIREBASE_REPOSITORY_DEBUG"

    private val storage = FirebaseStorage.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val cloud = FirebaseFirestore.getInstance()

    fun createNewUser(user : User) {
        cloud.collection("users")
            .document(user.uid)
            .set(user)
    }

    fun getUserData() : LiveData<User> {
        val cloudResult = MutableLiveData<User>()
        val uid = auth.currentUser?.uid

        cloud.collection("users")
            .document(uid!!)
            .get()
            .addOnSuccessListener {
                val user = it.toObject(User::class.java)
                if(user != null)
                    cloudResult.postValue(user)
                else
                    Log.d(FIREBASE_REPOSITORY_DEBUG, "get user null")
            }
            .addOnFailureListener { exception ->
                Log.d(FIREBASE_REPOSITORY_DEBUG, exception.message.toString())
            }

        return cloudResult
    }

    fun updateUserData(data : Map<String, String>) {
        cloud.collection("users")
            .document(auth.currentUser!!.uid)
            .update(data)
            .addOnSuccessListener {
                Log.d(FIREBASE_REPOSITORY_DEBUG, "update user data SUCCESS")
            }
            .addOnFailureListener {
                Log.d(FIREBASE_REPOSITORY_DEBUG, "update user data FAIL")
            }
    }


    fun submitReview(review : String, book_id: String) {
        cloud.collection("reviews")
            .document()
            .set(mapOf("review" to review
                ,"book_id" to book_id
                ,"user_id" to auth.currentUser!!.uid))
            .addOnSuccessListener {
                Log.d(FIREBASE_REPOSITORY_DEBUG, "submit review SUCCESS")
            }
            .addOnFailureListener {
                Log.d(FIREBASE_REPOSITORY_DEBUG, "submit review FAIL")
            }
    }

    fun getReviewsByBookId(book_id : String) : LiveData<MutableList<BookReview>> {
        val cloudResult = MutableLiveData<MutableList<BookReview>>()

        cloud.collection("reviews")
            .whereEqualTo("book_id", book_id)
            .get()
            .addOnSuccessListener {
                val reviews = it.toObjects(BookReview::class.java)
                cloudResult.postValue(reviews)
                Log.d(FIREBASE_REPOSITORY_DEBUG, "get reviews success")
            }
            .addOnFailureListener { exception ->
                Log.d(FIREBASE_REPOSITORY_DEBUG, exception.message.toString())
            }

        return cloudResult
    }

    fun bookHasReviews(book_id : String) : LiveData<Boolean> {
        val hasReviews = MutableLiveData<Boolean>()

        cloud.collection("reviews")
            .whereEqualTo("book_id", book_id)
            .get()
            .addOnSuccessListener {
                Log.d(FIREBASE_REPOSITORY_DEBUG, "book has reviews SUCCESS")
                if(it.size() > 0)
                    hasReviews.postValue(true)
                else
                    hasReviews.postValue(false)
            }
            .addOnFailureListener {
                Log.d(FIREBASE_REPOSITORY_DEBUG, "book has reviews FAIL")
            }

        return hasReviews
    }

    fun getUserReviews() : LiveData<MutableList<BookReview>> {
        val cloudResult = MutableLiveData<MutableList<BookReview>>()

        cloud.collection("reviews")
            .whereEqualTo("user_id", auth.currentUser!!.uid)
            .get()
            .addOnSuccessListener {
                val reviews = it.toObjects(BookReview::class.java)
                cloudResult.postValue(reviews)
                Log.d(FIREBASE_REPOSITORY_DEBUG, "get reviews success")
            }
            .addOnFailureListener { exception ->
                Log.d(FIREBASE_REPOSITORY_DEBUG, exception.message.toString())
            }

        return cloudResult
    }

    fun getBooks() : LiveData<MutableList<Book>> {
        val cloudResult = MutableLiveData<MutableList<Book>>()

        cloud.collection("books")
            .get()
            .addOnSuccessListener {
                val books = it.toObjects(Book::class.java)
                cloudResult.postValue(books)
                Log.d(FIREBASE_REPOSITORY_DEBUG, "get books success")
            }
            .addOnFailureListener { exception ->
                Log.d(FIREBASE_REPOSITORY_DEBUG, exception.message.toString())
            }

        return cloudResult
    }

    fun getBookName(book_id: String) : LiveData<String> {
        val cloudResult = MutableLiveData<String>()

        cloud.collection("books")
            .document(book_id)
            .get()
            .addOnSuccessListener {
                val name = it.get("name")
                if(name != null) {
                    cloudResult.postValue(name.toString())
                    Log.d(FIREBASE_REPOSITORY_DEBUG, "get book name SUCCESS")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(FIREBASE_REPOSITORY_DEBUG, exception.message.toString())
            }

        return cloudResult
    }

}