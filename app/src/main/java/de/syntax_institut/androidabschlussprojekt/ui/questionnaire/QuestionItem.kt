package de.syntax_institut.androidabschlussprojekt.ui.questionnaire

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import de.syntax_institut.androidabschlussprojekt.data.model.Answer
import de.syntax_institut.androidabschlussprojekt.data.model.FactorType
import de.syntax_institut.androidabschlussprojekt.data.model.Question
import de.syntax_institut.androidabschlussprojekt.data.model.QuestionCategory
import de.syntax_institut.androidabschlussprojekt.util.comicBorder
import kotlinx.coroutines.time.delay


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun QuestionItem(
    modifier: Modifier = Modifier,
    viewModel: QuestionnaireViewModel,
    question: Question
) {

    val categoryEnum = when(question.category) {
        "mobility" -> QuestionCategory.MOBILITY
        "housing" -> QuestionCategory.HOUSING
        "nutrition" -> QuestionCategory.NUTRITION
        "consumption" -> QuestionCategory.CONSUMPTION
        else -> QuestionCategory.MOBILITY
    }

    val bgImage = categoryEnum.bgImg




    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(bgImage),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.4f
        )
        Column(
            modifier = Modifier.height(800.dp).padding(horizontal = 40.dp).padding(top = 80.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = modifier
                   .fillMaxWidth()
                   .padding(top = 10.dp)
                   .comicBorder(),
                  contentAlignment = Alignment.TopCenter
                ) {
                    Image(
                        painter = painterResource(bgImage),
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
                        Text(text = question.category,
                            modifier = Modifier.padding(vertical = 5.dp, horizontal = 20.dp))
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
                    Text(text = question.text,
                        modifier = Modifier.padding(20.dp))
                    }
                    LazyColumn(modifier = Modifier.padding(top = 250.dp)) {
                        itemsIndexed(question.answers) { index, answer ->
                            Box(modifier = Modifier
                                .padding(vertical = 8.dp)
                                .height(50.dp)
                                .width(260.dp)
                                .comicBorder()
                                .clickable(onClick = {
                                    viewModel.selectAnswer(answer)
                                    // delay(2000)
                                     }
                                ),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(answer.text)
                                if (viewModel.selectedAnswerId.value == answer.id ) {
                                    Image(
                                        painter = painterResource(R.drawable.q_arrow_right),
                                        contentDescription = "")
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