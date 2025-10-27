package de.syntax_institut.androidabschlussprojekt.ui.speciesLab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.ui.common.FullScreenBox


@Composable
fun SpeciesLabScreen(
    modifier: Modifier = Modifier,
    onNavigateToFacts: () -> Unit,
    onNavigateToIdentSpecies: () -> Unit,
) {
    FullScreenBox(
        bgImage = R.drawable.bg_specieslab,
        alpha = 1f,
        showButton = false

    ) {
        Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Species Lab Screen")
                Button(onClick = {onNavigateToFacts()}) {
                    Text("Go to Species Facts")
                }
                Button(onClick = {onNavigateToIdentSpecies()}) {
                    Text("Go to Species Identification")
                }
            }
        }

}


@Preview(showBackground = true)
@Composable
fun SpeciesLabScreenPreview() {
    SpeciesLabScreen(
        onNavigateToFacts = { },
        onNavigateToIdentSpecies = { }
    )
}