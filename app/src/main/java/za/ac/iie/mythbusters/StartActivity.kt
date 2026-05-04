package za.ac.iie.mythbusters

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtWelcome: TextView = findViewById(R.id.txtWelcome)
        val btnStart: Button = findViewById(R.id.btnStart)

        txtWelcome.text = "Welcome to MythBusters lifehack quiz!"

        btnStart.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}