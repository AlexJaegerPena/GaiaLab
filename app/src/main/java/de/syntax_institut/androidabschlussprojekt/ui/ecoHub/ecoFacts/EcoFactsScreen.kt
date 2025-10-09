package de.syntax_institut.androidabschlussprojekt.ui.ecoHub.ecoFacts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun EcoFactsScreen(
    modifier: Modifier = Modifier,
    onPopUpBackStack: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Eco Facts")
        Button(onClick = { onPopUpBackStack()} ) {
            Text("back")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EcoFactsScreenPreview() {
    EcoFactsScreen(onPopUpBackStack = {})
}