package de.syntax_institut.androidabschlussprojekt.ui.climateLab.co2quiz

import android.R.attr.onClick
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.ui.common.FullScreenBox
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardContent
import de.syntax_institut.androidabschlussprojekt.ui.theme.MyTypography
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.CO2QuizResultViewModel
import dev.chrisbanes.haze.HazeState


@Composable
fun CO2QuizResultScreen(
    modifier: Modifier = Modifier,
    // onNavigateToCO2Quiz: () -> Unit,
    // onNavigateToClimateLab: () -> Unit,
    quizVM: CO2QuizViewModel,
    resultVM: CO2QuizResultViewModel,
    hazeState: HazeState
) {

    val score by quizVM.score.collectAsState()
    val questions by quizVM.questions.collectAsState()
    val userResponses by quizVM.userResponses.collectAsState()

    LaunchedEffect(questions, userResponses) {
        quizVM.updateScore()
    }

    LaunchedEffect(score, userResponses) {
        if (userResponses.isNotEmpty() && score > 0.0) {
            resultVM.addCO2QuizResult(userResponses, score)
        }
    }
    /*
    FullScreenBox(
        bgImage = R.drawable.bg_co2quiz,
        buttonTopPadding = 40.dp,
        onClick = { onNavigateToClimateLab() },
        showSecondButton = true,
        onSecondButtonClick = { onNavigateToCO2Quiz() },
        secondButtonIcon = Icons.Outlined.Quiz
    ) {

     */
        Column(
            modifier = Modifier
                .height(600.dp)
                .padding(horizontal = 40.dp)
                .padding(top = 30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Result".uppercase(),
                color = CardContent,
                style = MyTypography.titleLarge,
                modifier = Modifier.padding(vertical = 5.dp, horizontal = 20.dp)
            )

            Column(modifier = Modifier
            ) {
                Row(modifier = Modifier) {
                    Text("Your CO² footprint score:")
                    if (score == 0.0 ) {
                        LinearProgressIndicator()
                    } else {
                        Text(score.toString())
                    }
                }

                Text("Your answers:")
                LazyColumn(modifier = Modifier.height(300.dp)) {
                    items(questions) { question ->

                        val selectedAnswerId = userResponses[question.id]

                        val selectedAnswerText = question.answers
                            .find { it.id == selectedAnswerId }
                            ?.text ?: ""
                        Log.d("QUEST RESULT", "${quizVM.userResponses}")

                        Text(
                            text = "Question${question.text}\nAnswer: $selectedAnswerText",
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }
            Button(onClick = {  resultVM.addCO2QuizResult(userResponses, score)}) {
                Text("Add result")
            }
        }
    }
// }


@Preview(showBackground = true)
@Composable
fun CO2ResultScreenPreview() {
    CO2QuizResultScreen(
        quizVM = viewModel(),
        resultVM = viewModel(),
        hazeState = HazeState()
        // onNavigateToCO2Quiz = { },
        // onNavigateToClimateLab = { }
    )
}