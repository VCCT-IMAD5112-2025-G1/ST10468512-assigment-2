package za.co.varsitycollege.st10468512.quizapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Scoresheet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_scoresheet)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total",0)
        val questions = intent.getStringExtra("Questions")
        val answers = intent.getBooleanArrayExtra("Answers")
        val userAnswer = intent.getBooleanArrayExtra("userAnswer")

        //Defining the buttons and textview so that it is accessible in the program by referencing their ids form the layout
        val ReviewText =findViewById<TextView>(R.id.ReviewText)
        val ScoreText = findViewById<TextView>(R.id.ScoreView)
        val feedback = findViewById<TextView>(R.id.feedback)
        val exitbutton = findViewById<Button>(R.id.exitbutton)
        val reviewButton = findViewById<Button>(R.id.Revbutton)
        val ScoreButton = findViewById<Button>(R.id.scorebutton)



        //Displaying the users score
        ScoreText.text="score: $score out of $total"

        //using if statements to display feedback based on the users score
        if (score >=3) {
            feedback.text="Excellent work"
        }
        else{
            feedback.text="Keep trying "
        }

        //Programming the exit button
        exitbutton.setOnClickListener {
            //Closing the activity once the button is clicked
            finishAffinity()

        }

        //Programming the Review button
        reviewButton.setOnClickListener {

            //Making sure all the arrays are not null before accessing them
            if (questions!= null && answers!=null && userAnswer!=null){

                //Creating a string bulider to build the review text
                val ReviewText= StringBuilder()

                //Using a loop to loop through all the questions and add the information
                for (i in questions.indices )
                {
                    ReviewText.append("${questions[i]}\n")
                    ReviewText.append("Your Answer:${userAnswer[i]}\n")
                    ReviewText.append("Correct Answer: ${answers[i]}\n\n")



                }






            }
        }

        //Coding the score Button
        ScoreButton.setOnClickListener {

            //Clearing the review when showing the score and feedback
            ReviewText.text=""

            //Showing the score and feedback once again
            ScoreText.text="Score: $score out of $total"
            if (score >=3){
                feedback.text="Excellent work"
            }
            else{
                feedback.text="Keep trying!"
            }
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}