package pl.edu.uj.reviewexchange.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
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

    fun updateUserData(data : Map<String, String>, context: Context) {
        cloud.collection("users")
            .document(auth.currentUser!!.uid)
            .update(data)
            .addOnSuccessListener {
                Log.d(FIREBASE_REPOSITORY_DEBUG, "update user data SUCCESS")

                Toast.makeText(context
                    , "Data successfully updated!"
                    , Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener { exception ->
                Log.d(FIREBASE_REPOSITORY_DEBUG, "update user data FAIL")

                Toast.makeText(context
                    , "Error while updating data! ${exception.message.toString()}"
                    , Toast.LENGTH_SHORT).show()
            }
    }

    fun submitReview(review : String, book_id: String, book_name : String, user_email : String, context: Context) {
        cloud.collection("reviews")
            .document()
            .set(mapOf("review" to review
                ,"book_id" to book_id
                ,"book_name" to book_name
                ,"user_email" to user_email
                ,"user_id" to auth.currentUser!!.uid))
            .addOnSuccessListener {
                Log.d(FIREBASE_REPOSITORY_DEBUG, "submit review SUCCESS")
                Toast.makeText(context
                    , "Review successfully added!"
                    , Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { exception ->
                Log.d(FIREBASE_REPOSITORY_DEBUG, "submit review FAIL")
                Toast.makeText(context
                    , "Error while submitting review! ${exception.message.toString()}"
                    , Toast.LENGTH_SHORT).show()
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

    fun getBookById(book_id: String) : LiveData<Book> {
        val cloudResult = MutableLiveData<Book>()

        cloud.collection("books")
            .document(book_id)
            .get()
            .addOnSuccessListener {
                val book = it.toObject(Book::class.java)
                if(book != null)
                    cloudResult.postValue(book)

                Log.d(FIREBASE_REPOSITORY_DEBUG, "get book name SUCCESS")
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

    fun addBook(book : Book, context: Context) {

        cloud.collection("books")
            .whereEqualTo("name", book.name)
            .get()
            .addOnSuccessListener {
                if(!(it.size() > 0)) {
                    cloud.collection("books")
                        .document(book.id)
                        .set(mapOf("id" to book.id,
                            "name" to book.name,
                            "author" to book.author,
                            "description" to book.description))
                        .addOnSuccessListener {
                            Log.d(FIREBASE_REPOSITORY_DEBUG, "add book SUCCESS")

                            Toast.makeText(context, "Book successfully added!", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { exception ->
                            Log.d(FIREBASE_REPOSITORY_DEBUG, "add book FAIL")

                            Toast.makeText(context
                                , "Error while adding book! ${exception.message.toString()}"
                                , Toast.LENGTH_SHORT).show()
                        }
                }
            }
    }

//    fun sampleBookList() {
//        addBook(Book.Builder()
//            .name("Big Shot")
//            .author("Jeff Kinney")
//            .build())
//
//        addBook(Book.Builder()
//            .name("The Judge’s List")
//            .author("John Grisham")
//            .build())
//
//        addBook(Book.Builder()
//            .name("The Stranger In The Lifeboat")
//            .author("Mitch Albom")
//            .build())
//
//        addBook(Book.Builder()
//            .name("The Pioneer Woman Cooks-Super Easy!")
//            .author("Ree Drummond")
//            .build())
//
//        addBook(Book.Builder()
//            .name("The Hobbit")
//            .author("J. R. R. Tolkien")
//            .build())
//
//        addBook(Book.Builder()
//            .name("Harry Potter and the Philosopher's Stone")
//            .author("J. K. Rowling")
//            .build())
//
//        addBook(Book.Builder()
//            .name("The Little Prince")
//            .author("Antoine de Saint-Exupéry")
//            .build())
//    }

}