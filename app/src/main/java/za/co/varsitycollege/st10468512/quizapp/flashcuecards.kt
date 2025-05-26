package za.co.varsitycollege.st10468512.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class flashcuecards : AppCompatActivity() {

    //defining the array which will store the questions
    private val questions = arrayOf(
        "The capital of Spain is Madrid",
        "Dinosaurs are extincted",
        "Jupiter isn't the largest planet in the galaxy",
        "Humans have 207 bones in their body ",
        "South Africa has won the most rugby world cups"
    )
    // Defining the array what will store the answers
    private val answers = booleanArrayOf(true, true, false, false, true)
    private val userAnswer = BooleanArray(questions.size) {false}

    private var currentIndex = 0
    private var score = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flashcuecards)





         val trueid = findViewById<Button>(R.id.trueid)
         val falseid = findViewById<Button>(R.id.falseid)
         val nextbtn = findViewById<Button>(R.id.nextbtn)
         val questiontext = findViewById<TextView>(R.id.questiontext)

        // show the first question
        showQuestion()


            trueid.setOnClickListener{
            checkAnswer(true)
        }

        falseid.setOnClickListener {
            checkAnswer(false)
        }


        nextbtn.setOnClickListener {
            currentIndex++
            if (currentIndex < questions.size) {
                showQuestion()
            } else {

                val intent = Intent(this, Scoresheet::class.java)
                intent.putExtra("score",score)
                intent.putExtra("total",questions.size)
                intent.putExtra("Questions",questions)
                intent.putExtra("userActivity",userAnswer)
                startActivity(intent)

                finish()
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    //This function is used to display the current question
    private fun showQuestion() {
        val nextbtn = findViewById<Button>(R.id.nextbtn)
        val truebtn = findViewById<Button>(R.id.trueid)
        val falsebtn = findViewById<Button>(R.id.falseid)
        val questiontext = findViewById<TextView>(R.id.questiontext)

        //Sets the text to the question based on the current index
        questiontext.text=questions[currentIndex]


        //Enabling the true and false button so that it appears in screen
        truebtn.isEnabled=true
        falsebtn.isEnabled=true

        nextbtn.isEnabled=true

    }
    //This function is used to check the users answers
    private fun checkAnswer(studentAnswer: Boolean) {
        val nextbtn = findViewById<Button>(R.id.nextbtn)
        val truebtn = findViewById<Button>(R.id.trueid)
        val falsebtn = findViewById<Button>(R.id.falseid)
        val correct = answers[currentIndex]

        userAnswer[currentIndex]=studentAnswer
        if (studentAnswer == correct) {

            Toast.makeText(this@flashcuecards, "The answer is correct", Toast.LENGTH_LONG).show()
            score++
        } else {
            Toast.makeText(this@flashcuecards, "The answer is incorrect", Toast.LENGTH_LONG).show()
        }
        truebtn.isEnabled = false
        falsebtn.isEnabled = false
        nextbtn.isEnabled = true


    }
}