package de.syntax_institut.androidabschlussprojekt.ui.climateLab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.ui.common.FullScreenBox


@Composable
fun ClimateLabScreen(
    modifier: Modifier = Modifier,
    onNavigateToFacts: () -> Unit,
    onNavigateToCO2Quiz: () -> Unit,
    onNavigateToTips: () -> Unit,
   // onNavigateToCO2QuizResult: () -> Unit
) {
    FullScreenBox(
        bgImage = R.drawable.bg_climatelab2,
        showButton = false
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Climate Zone Screen")

            Button(onClick = { onNavigateToFacts()} ) {
                Text("Navigate to facts")
            }
            Button(onClick = { onNavigateToCO2Quiz()} ) {
                Text("Navigate to questionnaire")
            }
            Button(onClick = { onNavigateToTips()} ) {
                Text("Navigate to result")
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun ClimateLabScreenPreview() {
    ClimateLabScreen(
        onNavigateToFacts = { },
        onNavigateToCO2Quiz = { },
        onNavigateToTips = { }
    )
}