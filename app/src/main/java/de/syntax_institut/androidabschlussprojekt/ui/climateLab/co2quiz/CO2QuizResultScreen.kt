package de.syntax_institut.androidabschlussprojekt.ui.climateLab.co2quiz

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NaturePeople
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.androidabschlussprojekt.data.model.co2quiz.ScoreDisplay
import de.syntax_institut.androidabschlussprojekt.data.model.co2quiz.ScoreEvaluation
import de.syntax_institut.androidabschlussprojekt.data.model.co2quiz.infoRowContent
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardContent
import de.syntax_institut.androidabschlussprojekt.ui.theme.MyTypography
import dev.chrisbanes.haze.HazeState
import org.koin.androidx.compose.koinViewModel


@Composable
fun CO2QuizResultScreen(
    modifier: Modifier = Modifier,
    onNavigateToTips: () -> Unit,
    onPopupBackStack: () -> Unit,
    onSecondButtonClick: () -> Unit,
    quizVM: CO2QuizViewModel = koinViewModel(),
    resultVM: CO2QuizResultViewModel = koinViewModel(),
) {

    val questions by quizVM.questions.collectAsState()
    val lastResult by resultVM.lastResult.collectAsState()

    var previousResultId by remember { mutableStateOf<String?>(null) }

    val hazeState = remember { HazeState() }


    LaunchedEffect(lastResult?.quizId) {
        if (lastResult != null && lastResult!!.quizId != previousResultId) {
            previousResultId = lastResult!!.quizId
            Log.d("QuizresultScreen","Neues Result erkannt: ${lastResult!!.co2Score}")
        }
    }

    CO2ScreenBox(
        onNavigateToTips = { onNavigateToTips() },
        onPopupBackStack = { onPopupBackStack() },
        onSecondButtonClick = { onSecondButtonClick() },
        quizVM = quizVM,
        resultVM = resultVM,
        secondButtonIcon = Icons.Outlined.Quiz,
        hazeState = hazeState
    ) {
        Column(
            modifier = Modifier
                .height(600.dp)
                .padding(horizontal = 50.dp)
                .padding(top = 145.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Your footprint result".uppercase(),
                color = CardContent,
                style = MyTypography.titleMedium
            )
            if (lastResult != null) {
                Column(modifier = Modifier.padding(vertical = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)) {

                    val scoreInTons = lastResult!!.co2Score / 1000
                    val scoreFormatted = String.format("%.2f", scoreInTons)
                    val evaluation = getScoreEvaluation(scoreInTons)
                    val displayScore = ScoreDisplay(scoreInTons, evaluation)

                    Row(
                        modifier = Modifier
                            .border(width = 2.dp, color = displayScore.evaluation.color, shape = RoundedCornerShape(20.dp))
                            .padding(20.dp),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            (scoreFormatted),
                            color = displayScore.evaluation.color,
                            style = MyTypography.titleLarge
                        )
                        Text(("/tons CO₂ per year"),
                            color = displayScore.evaluation.color,
                            style = MyTypography.bodyMedium
                        )
                    }
                    Row(modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                        Icon(displayScore.evaluation.icon, contentDescription = null, tint = displayScore.evaluation.color )
                        Text(displayScore.evaluation.text, color = displayScore.evaluation.color)
                    }
                }
            } else {
                Icon(
                    imageVector = Icons.Outlined.NaturePeople,
                    contentDescription = null,
                    modifier = Modifier.size(60.dp),
                    tint = Color(0xFFEC9F48)
                )
                Text("No quiz results yet. Take the quiz to see your footprint.",
                    color = Color(0xFFEC9F48),
                    style = MyTypography.titleLarge,
                    modifier = modifier.padding(start = 16.dp, bottom = 20.dp)
                )
            }
            Text("Did you know?",
                color = CardContent,
                style = MyTypography.titleSmall,
                modifier = Modifier.padding(top = 10.dp, start = 8.dp).align(Alignment.Start)
            )
            LazyColumn(
                modifier = Modifier.height(400.dp)
            ) {
                items(items = infoRowContent) { item ->
                    CO2InfoRow(icon = item.icon, text = item.text)
                }
            }
    }



        // TODO: auslagern in Dialog
        /*
            Text(
                "Your answers:",
                color = CardContent,
                style = MyTypography.bodyLarge
            )

            val mappedPairs = lastResult?.qaPair?.mapNotNull { (questionId, answerId) ->
                val question =
                    questions.find { it.id.toString() == questionId } ?: return@mapNotNull null
                val answer = question.answers.find { it.id == answerId } ?: return@mapNotNull null

                question.text to answer.text
            } ?: emptyList()


            LazyColumn(modifier = Modifier.height(350.dp)) {

                items(items = mappedPairs) { (questionText, answerText) ->
                    Text(questionText, color = CardContent, style = MyTypography.titleSmall)
                    Text(answerText, color = CardContent, style = MyTypography.bodyMedium, modifier = modifier.padding(bottom = 10.dp))
                }
            }

         */
    }
}

private fun getScoreEvaluation(score: Double): ScoreEvaluation {
    return when {
        score <= 2.0 -> ScoreEvaluation.EXCELLENT
        score <= 6.0 -> ScoreEvaluation.GOOD
        score <= 10.0 -> ScoreEvaluation.FAIR
        else -> ScoreEvaluation.POOR
    }
}



@Preview(showBackground = true)
@Composable
fun CO2ResultScreenPreview() {
    CO2QuizResultScreen(
        onNavigateToTips = {},
        onPopupBackStack = {},
        onSecondButtonClick = {},
        quizVM = viewModel(),
        resultVM = viewModel(),
    )
}