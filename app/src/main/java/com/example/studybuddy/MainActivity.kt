package com.example.studybuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studybuddy.ui.theme.StudybuddyTheme
import kotlin.system.exitProcess
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource





class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val showWelcomeScreen = remember { mutableStateOf(true) }
            val currentQuestionIndex = remember { mutableStateOf(0) }
            val score = remember { mutableStateOf(0) }
            val showScoreScreen = remember { mutableStateOf(false) }
            val feedback = remember { mutableStateOf("") }
            val showReviewScreen = remember { mutableStateOf(false) }


            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id=R.drawable.a6_flashcards_blauw_gelinieerd_voorzijde),
                    contentDescription = null ,
                    contentScale= ContentScale.FillBounds,
                    modifier= Modifier.fillMaxSize()
                )
            }
            val questions = arrayOf(
                "The Great Wall of China is visible from space.",
                "The Titanic sank on its maiden voyage.",
                "Napoleon Bonaparte was extremely short.",
                "The Berlin Wall fell in 1989.",
                "The United States declared independence from France."
            )
            val answers = arrayOf(false, true, false, true, false)

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                when {
                    showWelcomeScreen.value -> {
                        Text(text = "Welcome to the StudyBuddy App!", style = MaterialTheme.typography.headlineLarge)
                        Spacer(modifier = Modifier.height(24.dp))

                        Button(onClick = { showWelcomeScreen.value = false }) {
                            Text(text = "Start")
                        }
                    }
                    !showScoreScreen.value && !showReviewScreen.value -> {
                        Text(text = questions[currentQuestionIndex.value], style = MaterialTheme.typography.headlineLarge)
                        Spacer(modifier = Modifier.height(16.dp))

                        Button(onClick = {
                            feedback.value = if (answers[currentQuestionIndex.value]) "Correct!" else "Incorrect"
                            if (answers[currentQuestionIndex.value]) score.value++
                        }) {
                            Text(text = "True")
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        Button(onClick = {
                            feedback.value = if (!answers[currentQuestionIndex.value]) "Correct!" else "Incorrect"
                            if (!answers[currentQuestionIndex.value]) score.value++
                        }) {
                            Text(text = "False")
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(text = feedback.value, style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(16.dp))

                        Button(onClick = {
                            if (currentQuestionIndex.value < questions.size - 1) {
                                currentQuestionIndex.value++
                                feedback.value = ""
                            } else {
                                showScoreScreen.value = true
                            }
                        }) {
                            Text(text = "Next")
                        }
                    }
                    showScoreScreen.value -> {
                        Text(text = "Your Score: ${score.value}/${questions.size}", style = MaterialTheme.typography.headlineLarge)
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(text = if (score.value >= 3) "Great job!" else "Keep practicing!", style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(16.dp))

                        Button(onClick = {
                            showScoreScreen.value = false
                            showReviewScreen.value = true
                        }) {
                            Text(text = "Review Answers")
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        Button(onClick = { exitProcess(0) }) {
                            Text(text = "Exit")
                        }
                    }
                    showReviewScreen.value -> {
                        Text(text = "Review All Questions & Answers:", style = MaterialTheme.typography.headlineLarge)
                        Spacer(modifier = Modifier.height(16.dp))

                        questions.forEachIndexed { index, question ->
                            Text(text = "❓ $question", style = MaterialTheme.typography.bodyLarge)
                            Text(
                                text = "✅ Correct Answer: ${if (answers[index]) "True" else "False"}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        Button(onClick = {
                            showWelcomeScreen.value = true
                            showReviewScreen.value = false
                            currentQuestionIndex.value = 0
                            score.value = 0
                            feedback.value = ""
                        }) {
                            Text(text = "Restart Quiz")
                        }
                    }
                }
            }
        }
    }
}










