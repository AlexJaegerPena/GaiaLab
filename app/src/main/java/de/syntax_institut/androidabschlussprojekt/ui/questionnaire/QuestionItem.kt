package de.syntax_institut.androidabschlussprojekt.ui.questionnaire

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.androidabschlussprojekt.R
import kotlinx.coroutines.flow.first


@Composable
fun QuestionItem(
    modifier: Modifier = Modifier,
    viewModel: QuestionnaireViewModel = viewModel()
) {

    val questions by viewModel.questions.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.q_consumption_bg),
            contentDescription = ""
        )
        Column(modifier = Modifier) {
            if(questions.isEmpty()) {
                Text("loading...")
            } else {
                val question = questions.first()
                Text(text = question.text)

                LazyColumn(modifier = Modifier) {
                    items(question.answers) { answer ->
                        Text(answer.text)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun QuestionItemPreview() {
    QuestionItem()
}