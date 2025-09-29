package de.syntax_institut.androidabschlussprojekt.ui.questionnaire

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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.util.comicBorder


@Composable
fun QuestionnaireResult(
    modifier: Modifier = Modifier,
    viewModel: QuestionnaireViewModel = viewModel()
) {

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.q_consumption_bg),
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
                .comicBorder(),
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    painter = painterResource(R.drawable.q_consumption_bg),
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
                    Text(text = "Test results",
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
                    Text("Your score: ...")
                    Text("Your answers:")

                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun QuestionnaireResultPreview() {
    QuestionnaireResult(viewModel = viewModel())
}