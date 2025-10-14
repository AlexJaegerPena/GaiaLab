package de.syntax_institut.androidabschlussprojekt.ui.climateZone.questionnaire

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.util.comicBorder
import org.koin.androidx.compose.koinViewModel


@Composable
fun QuestionnaireResultScreen(
    modifier: Modifier = Modifier,
    onNavigateToQuestionnaire: () -> Unit,
    onPopUpBackStack: () -> Unit,
    viewModel: QuestionnaireViewModel = koinViewModel()
) {

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.bg_home),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.4f
        )
        Column(
            modifier = Modifier
                .height(800.dp)
                .padding(horizontal = 40.dp)
                .padding(top = 80.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .comicBorder(),
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    painter = painterResource(R.drawable.bg_home),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .comicBorder()
                        .align(alignment = Alignment.TopStart)
                ) {
                    Text(
                        text = "Test results",
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 20.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(top = 80.dp)
                        .height(150.dp)
                        .width(300.dp)
                        .background(Color.White)
                        .comicBorder(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(modifier = Modifier) {
                        Text("Your score: ...")
                        Text("Your answers:")
                        LazyColumn(modifier = Modifier) {
                            items(viewModel.questions.value) { question ->

                                val selectedAnswersId = viewModel.userResponses[question.id]
                                val selectedAnswerText = question.answers.find { it.id == selectedAnswersId }?.text ?: ""
                                Log.d("QUEST RESULT", "${viewModel.userResponses}")
                                Text(
                                    text = "Question${question.text}\nAnswer: $selectedAnswerText",
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )
                            }
                        }
                        Row(modifier = Modifier) {
                            Button(onClick = { onPopUpBackStack() }) {
                                Text("zurück")
                            }
                            Button(onClick = { onNavigateToQuestionnaire() }) {
                                Text("Navigate to questionnaire")
                            }
                        }
                    }

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun QuestionnaireResultScreenPreview() {
    QuestionnaireResultScreen(
        viewModel = viewModel(),
        onNavigateToQuestionnaire = { },
        onPopUpBackStack = { }
    )
}