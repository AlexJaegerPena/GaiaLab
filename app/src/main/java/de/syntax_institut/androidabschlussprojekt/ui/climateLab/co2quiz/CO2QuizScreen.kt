package de.syntax_institut.androidabschlussprojekt.ui.climateLab.co2quiz

import android.R.attr.onClick
import android.R.attr.textStyle
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.ui.climateLab.co2quiz.CO2QuizViewModel
import de.syntax_institut.androidabschlussprojekt.ui.common.CustomButton
import de.syntax_institut.androidabschlussprojekt.ui.common.FullScreenBox
import de.syntax_institut.androidabschlussprojekt.ui.theme.MyTypography
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.CO2QuizResultViewModel
import dev.chrisbanes.haze.HazeState
import org.koin.androidx.compose.koinViewModel


@Composable
fun CO2QuizScreen(
    modifier: Modifier = Modifier,
    onNavigateToResult: () -> Unit,
    quizVM: CO2QuizViewModel,
    resultVM: CO2QuizResultViewModel,
    hazeState: HazeState
) {

    val actualQuestion by quizVM.actualQuestion.collectAsState()
    val userResponses by quizVM.userResponses.collectAsState()
    // val score by quizVM.score.collectAsState()

    val resultSaved by resultVM.resultSaved.collectAsState()

    val question = actualQuestion
    if (question == null) return


    LaunchedEffect(resultSaved) {
        if (resultSaved) {
            onNavigateToResult()
            resultVM.resetResultSaved()
            quizVM.resetQuiz()
        }
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .height(600.dp),
        verticalArrangement = Arrangement.Top
    ) {
        QuestionItem(viewModel = quizVM, question = question)
        Spacer(modifier = modifier.weight(1f))
        Row(
            modifier = Modifier.padding(horizontal = 70.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            CustomButton(
                modifier = Modifier
                    .height(25.dp)
                    .width(54.dp)
                    .alpha(if (question.id == 1) 0f else 1f),
                hazeState = hazeState,
                buttonIcon = Icons.Default.ArrowBackIosNew,
                buttonText = null,
                onClick = { quizVM.previousQuestion() }
            )
            Spacer(modifier = modifier.weight(1f))
            if (question.id == 12) {
                CustomButton(
                    modifier = Modifier
                        .height(40.dp),
                    hazeState = hazeState,
                    buttonIcon = null,
                    buttonText = "See result",
                    // enabled = if () { true } else { false },
                    textStyle = MyTypography.bodyMedium,
                    glowColor = Color(0xFF58DCB8),
                    bgColor = Color(0xFF6AE7DB),
                    bgAlpha = 0.2f,
                    onClick = {
                        val finalScore = quizVM.calculateScore()
                        Log.d("CO2QuizScreen", "Submitting score: $finalScore")
                        resultVM.addCO2QuizResult(userResponses, finalScore)
                    }
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun CO2QuizScreenPreview() {
    CO2QuizScreen(
        onNavigateToResult = {},
       // onPopUpBackStack = {},
        quizVM = viewModel(),
        resultVM = viewModel(),
        hazeState = HazeState()
    )
}