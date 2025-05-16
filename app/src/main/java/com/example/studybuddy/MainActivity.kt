package com.example.studybuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studybuddy.ui.theme.StudybuddyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var showWelcomeScreen = remember { mutableStateOf(true) }
            var currentQuestionIndex = remember { mutableStateOf(0) }
            var score = remember { mutableStateOf(0) }
            var showScoreScreen = remember { mutableStateOf(false) }

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
                if (showWelcomeScreen.value) {
                    Text(text = "Welcome to the MY5thtry App!", style = MaterialTheme.typography.headlineLarge)
                    Spacer(modifier = Modifier.height(24.dp))

                    Button(onClick = { showWelcomeScreen.value = false }) {
                        Text(text = "Start")
                    }
                } else if (!showScoreScreen.value) {
                    Text(text = questions[currentQuestionIndex.value], style = MaterialTheme.typography.headlineLarge)
                    Spacer(modifier = Modifier.height(16.dp))

                    var feedback by remember { mutableStateOf("") }

                    Button(onClick = {
                        feedback = if (answers[currentQuestionIndex.value]) "Correct!" else "Incorrect"
                        if (answers[currentQuestionIndex.value]) score.value++
                    }) {
                        Text(text = "True")
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    Button(onClick = {
                        feedback = if (!answers[currentQuestionIndex.value]) "Correct!" else "Incorrect"
                        if (!answers[currentQuestionIndex.value]) score.value++
                    }) {
                        Text(text = "False")
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = feedback, style = MaterialTheme.typography.bodyLarge)

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = {
                        if (currentQuestionIndex.value < questions.size - 1) {
                            currentQuestionIndex.value++
                            feedback = ""
                        } else {
                            showScoreScreen.value = true
                        }
                    }) {
                        Text(text = "Next")
                    }
                } else {
                    Text(text = "Your Score: ${score.value}/${questions.size}", style = MaterialTheme.typography.headlineLarge)
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = if (score.value >= 3) "Great job!" else "Keep practicing!", style = MaterialTheme.typography.bodyLarge)

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = {
                        currentQuestionIndex.value = 0
                        score.value = 0
                        showWelcomeScreen.value = true
                        showScoreScreen.value = false
                    }) {
                        Text(text = "Review")
                    }







                }
    }
}

