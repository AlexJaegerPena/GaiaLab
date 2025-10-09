package de.syntax_institut.androidabschlussprojekt.ui.ecoHub

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun EcoHubScreen(
    modifier: Modifier = Modifier,
    onNavigateToFacts: () -> Unit,
    onNavigateToTips: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Eco Hub")
        Row(modifier = Modifier) {
            Button(onClick = { onNavigateToFacts()} ) {
                Text("Navigate to facts")
            }
            Button(onClick = { onNavigateToTips()} ) {
                Text("Navigate to tips")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EcoHubScreenPreview() {
    EcoHubScreen(
        onNavigateToFacts = {},
        onNavigateToTips = {}
    )
}