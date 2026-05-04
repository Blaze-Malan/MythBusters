package za.ac.iie.mythbusters

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        val txtReview: TextView = findViewById(R.id.txtReview)

        val answers = intent.getStringArrayListExtra("answers")
        // Retrieve answers passed from ScoreActivity

        val builder = StringBuilder()
        answers?.forEach { builder.append(it).append("\n\n") }
        //lets the user scroll just for quality of life

        txtReview.text = builder.toString()
    }
}