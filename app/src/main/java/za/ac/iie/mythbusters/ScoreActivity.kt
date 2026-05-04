package za.ac.iie.mythbusters

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val score = intent.getIntExtra("score", 0)
        val answers = intent.getStringArrayListExtra("answers")
        //receiving the answers pushed from quizactivity
        //(Microsoft Copilot, 2026)
        val txtFinalScore: TextView = findViewById(R.id.txtFinalScore)
        val txtFeedback: TextView = findViewById(R.id.txtFeedback)
        val btnReview: Button = findViewById(R.id.btnReview)
        val btnFinalize: Button = findViewById(R.id.btnFinalize)

        txtFinalScore.text = "Your final score: $score / 5"
        // Show the final score to the user

        val feedbackMessage = when (score) {
            0, 1 -> "You need to improve — keep practicing!"
            2, 3 -> "Well done — you’re getting the hang of it!"
            4, 5 -> "Excellent — your knowledge shines!"
            else -> "Nice effort!"
        }
        txtFeedback.text = feedbackMessage
        //feedback message for users

        btnReview.setOnClickListener {
            val reviewIntent = Intent(this, ReviewActivity::class.java)
            reviewIntent.putStringArrayListExtra("answers", answers)
            startActivity(reviewIntent)
            // If the user taps review open ReviewActivity
        }

        btnFinalize.setOnClickListener {
            val intent = Intent(this, StartActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            // Finalize button takes the user back to the start screen
        }
    }
}