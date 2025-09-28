package de.syntax_institut.androidabschlussprojekt.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.syntax_institut.androidabschlussprojekt.ui.questionnaire.QuestionItem


@Composable
fun AppStart(modifier: Modifier = Modifier) {
    QuestionItem()
}


@Preview(showBackground = true)
@Composable
fun AppStartPreview() {
    AppStart()
}