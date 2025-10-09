package de.syntax_institut.androidabschlussprojekt.ui.climateZone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ClimateZoneScreen(
    modifier: Modifier = Modifier,
    onNavigateToFacts: () -> Unit,
    onNavigateToQuestionnaire: () -> Unit,
    onNavigateToQuestionnaireResult: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Climate Zone Screen")

        Button(onClick = { onNavigateToFacts()} ) {
            Text("Navigate to facts")
        }
        Button(onClick = { onNavigateToQuestionnaire()} ) {
            Text("Navigate to questionnaire")
        }
        Button(onClick = { onNavigateToQuestionnaireResult()} ) {
            Text("Navigate to result")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ClimateZoneScreenPreview() {
    ClimateZoneScreen(
        onNavigateToFacts = { },
        onNavigateToQuestionnaire = { },
        onNavigateToQuestionnaireResult = { }
    )
}