package de.syntax_institut.androidabschlussprojekt.ui.speciesLab.speciesFacts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun SpeciesFactsScreen(
    modifier: Modifier = Modifier,
    onPopUpBackStack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { onPopUpBackStack()} ) {
            Text("Zurück")
        }
        Text("Species Facts")

    }
}


@Preview(showBackground = true)
@Composable
fun SpeciesFactsScreenPreview() {
    SpeciesFactsScreen(onPopUpBackStack = { } )
}