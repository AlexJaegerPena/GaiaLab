package de.syntax_institut.androidabschlussprojekt.ui.questionnaire

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.syntax_institut.androidabschlussprojekt.data.model.Answer
import de.syntax_institut.androidabschlussprojekt.data.model.FactorType
import de.syntax_institut.androidabschlussprojekt.data.model.Question
import de.syntax_institut.androidabschlussprojekt.data.model.QuestionCategory
import de.syntax_institut.androidabschlussprojekt.util.comicBorder

@Composable
fun QuestionItem(
    modifier: Modifier = Modifier,
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
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
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
                   .drawBehind {
                    val strokeWidth = 8f
                    val path = Path().apply {
                        moveTo(0f, 0f)
                        quadraticBezierTo(size.width * 0.15f, -10f, size.width, 0f)
                        quadraticBezierTo(size.width + 10f, size.height * 0.2f, size.width, size.height)
                        quadraticBezierTo(size.width * 0.25f, size.height + 10f, 0f, size.height)
                        quadraticBezierTo(-10f, size.height * 0.2f, 0f, 0f)
                    }
                    drawPath(
                        path = path,
                        color = Color.Black,
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                    )
                },
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
                            .padding(top = 100.dp)
                            .height(150.dp)
                            .width(300.dp)
                            .background(Color.White)
                            .comicBorder(),
                        contentAlignment = Alignment.Center
                    ) {
                    Text(text = question.text,
                        modifier = Modifier.padding(20.dp))
                    }
                    LazyColumn(modifier = Modifier.padding(top = 280.dp)) {
                        items(question.answers) { answer ->
                            Box(modifier = Modifier
                                .padding(vertical = 8.dp)
                                .height(50.dp)
                                .width(260.dp)
                                .comicBorder(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(answer.text)
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
        question = Question(
        id = 0, text = "Test", answers = listOf(
            Answer(0, "test1", 1.2, FactorType.MULTIPLIER),
            Answer(1, "test2", 1.2, FactorType.MULTIPLIER),
        ),
        category = "Mobility"
    ))
}