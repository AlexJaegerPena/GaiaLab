package de.syntax_institut.androidabschlussprojekt.ui.climateZone.questionnaire

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.androidabschlussprojekt.R


@Composable
fun QuestionnaireScreen(
    modifier: Modifier = Modifier,
    onNavigateToResult: () -> Unit,
    onPopUpBackStack: () -> Unit,
    viewModel: QuestionnaireViewModel = viewModel()
) {
    // val questions by viewModel.questions.collectAsState()
    // var firstQuestion = questions.first()
    val actualQuestion by viewModel.actualQuestion.collectAsState()

    val question = actualQuestion
    if (question == null) return


    Box(modifier = Modifier) {
        Row(modifier = Modifier) {
            Button(onClick = { onPopUpBackStack()} ) {
                Text("zurück")
            }
            Button(onClick = { onNavigateToResult()} ) {
                Text("Navigate to result")
            }
        }
        QuestionItem(viewModel = viewModel, question = question)
        Row(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 30.dp)
            .padding(horizontal = 70.dp)) {
            Image(
                painter = painterResource(R.drawable.q_arrow_left),
                contentDescription = "previous",
                modifier = Modifier
                    .clickable(onClick = { viewModel.previousQuestion() })
                    .align(Alignment.Bottom)
                    .alpha(
                        if (question.id == 1) {
                            0f
                        } else {
                            1f
                        }
                    )

            )
            Spacer(modifier = modifier.weight(1f))
            Image(
                painter = painterResource(if (question.id == 12) { R.drawable.q_arrow_result} else { R.drawable.q_arrow_right}),
                contentDescription = "next",
                modifier = Modifier
                    .clickable(onClick = { viewModel.nextQuestion() })
                    .align(Alignment.Bottom)

            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun QuestionnaireScreenPreview() {
    QuestionnaireScreen(
        onNavigateToResult = { },
        onPopUpBackStack = { }
    )
}