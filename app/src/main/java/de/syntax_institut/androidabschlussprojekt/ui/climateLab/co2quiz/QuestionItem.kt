package de.syntax_institut.androidabschlussprojekt.ui.climateLab.co2quiz

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.androidabschlussprojekt.data.model.co2quiz.Answer
import de.syntax_institut.androidabschlussprojekt.data.model.co2quiz.FactorType
import de.syntax_institut.androidabschlussprojekt.data.model.co2quiz.Question
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardContent
import de.syntax_institut.androidabschlussprojekt.ui.theme.MyTypography
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun QuestionItem(
    modifier: Modifier = Modifier,
    viewModel: CO2QuizViewModel,
    question: Question
) {

    val scope = rememberCoroutineScope()
    val userResponses by viewModel.userResponses.collectAsState()
    val selectedAnswerId = userResponses[question.id]

    Column(
        modifier = Modifier
            .padding(top = 115.dp)
            .padding(horizontal = 44.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start

    ) {
        Text(
            text = question.category.replaceFirstChar { it.uppercase() },
            color = Color.Black.copy(alpha = 0.7f),
            style = MyTypography.bodyMedium,
            modifier = Modifier
                .background(
                    color = Color(0xFFA4CCC5),
                    shape = RoundedCornerShape(20.dp, 0.dp, 20.dp, 0.dp)
                )
                .align(Alignment.Start)
                .padding(vertical = 6.dp, horizontal = 12.dp)
        )
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = question.text,
                modifier = Modifier.padding(top = 20.dp),
                color = CardContent,
                style = MyTypography.headlineSmall
            )

            LazyColumn(modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)) {
                itemsIndexed(question.answers) { index, answer ->
                    Row(modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clickable(onClick = {
                            viewModel.saveQAPairs(questionId = question.id, answerId = answer.id)
                            scope.launch {
                                delay(1000)
                                viewModel.nextQuestion()
                            }
                        }),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(imageVector =
                            if (selectedAnswerId == answer.id) {
                                Icons.Default.RadioButtonChecked
                            } else {
                                Icons.Default.RadioButtonUnchecked
                            },
                            contentDescription = null,
                            tint = CardContent
                        )

                        Text(answer.text,
                            color = CardContent,
                            style = MyTypography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun QuestionItemPreview() {
    QuestionItem(
        viewModel = viewModel(),
        question = Question(
        id = 0, text = "Test", answers = listOf(
            Answer(0, "test1", 1.2, FactorType.MULTIPLIER),
            Answer(1, "test2", 1.2, FactorType.MULTIPLIER),
        ),
        category = "Mobility"
    ))
}