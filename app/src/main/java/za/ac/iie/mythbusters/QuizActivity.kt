package za.ac.iie.mythbusters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private val userAnswersList = ArrayList<String>()
    //First step of adding the review system
    // This list will keep track of every answer the user gives,
    //(Microsoft Copilot, 2026)
    private val questions = listOf(
        Pair("Microwaving food in plastic containers is always safe.", false),
        Pair("Drinking lots of coffee will sober you up immediately.", false),
        Pair("Cracking your knuckles causes arthritis.", false),
        Pair("Storing batteries in the fridge can help them last longer.", true),
        Pair("Putting a wooden spoon across a pot prevents it from boiling over.", true)
        // Here are the quiz questions
    )

    private var currentIndex = 0
    private var score = 0
    private var answered = false
    //keep track of where we are in the quiz and the score

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val txtScore: TextView = findViewById(R.id.txtScore)
        val txtQuestion: TextView = findViewById(R.id.txtQuestion)
        val btnTrue: Button = findViewById(R.id.btnTrue)
        val btnFalse: Button = findViewById(R.id.btnFalse)
        val btnNext: Button = findViewById(R.id.btnNext)

        txtQuestion.text = questions[currentIndex].first
        txtScore.text = "Score: $score"
        // Show the first question and the starting score

        btnTrue.setOnClickListener {
            if (!answered) {
                checkAnswer(true)
                answered = true
                btnFalse.isEnabled = false
            }
        }

        btnFalse.setOnClickListener {
            if (!answered) {
                checkAnswer(false)
                answered = true
                btnTrue.isEnabled = false
            }
        }
        //Boolean logic for our answers users can only choose true or false

        btnNext.setOnClickListener {
            if (currentIndex < questions.size - 1) {
                currentIndex++
                txtQuestion.text = questions[currentIndex].first
                answered = false
                btnTrue.isEnabled = true
                btnFalse.isEnabled = true
                // Next button moves to the next question
            } else {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                intent.putStringArrayListExtra("answers", userAnswersList)
                //send the answers list
                //(Microsoft Copilot, 2026)
                startActivity(intent)
            }
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val questionText = questions[currentIndex].first
        val correctAnswer = questions[currentIndex].second
        val message = if (userAnswer == correctAnswer) {
            score++
            "Correct! Well done."
        } else {
            "Incorrect! Better luck next time."
            //checks if the answer are correct if else logic to display some feedback to our user
        }


        val reviewEntry = "Q${currentIndex + 1}: $questionText\n" +
                "Your answer: $userAnswer\n" +
                "Correct answer: $correctAnswer"
        userAnswersList.add(reviewEntry)
        //record the review entry
        ////(Microsoft Copilot, 2026)

        findViewById<TextView>(R.id.txtScore).text = "Score: $score"
        showPopup(message)
        // Update the score display
    }

    private fun showPopup(message: String) {
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.popup_layout, null)
        val txtPopup: TextView = view.findViewById(R.id.txtPopup)
        txtPopup.text = message

        val dialog = AlertDialog.Builder(this)
            .setView(view)
            .create()

        view.setOnClickListener { dialog.dismiss() }
        // Tap anywhere on the pop‑up to close it
        //(Microsoft Copilot, 2026)

        dialog.show()
        dialog.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.8).toInt(),
            (resources.displayMetrics.heightPixels * 0.5).toInt()
            //Custom size of popup so it's more like a flash card appearing on the users screen
        )
    }
}