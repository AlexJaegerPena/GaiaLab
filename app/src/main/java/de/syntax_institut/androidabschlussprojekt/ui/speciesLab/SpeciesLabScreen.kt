package de.syntax_institut.androidabschlussprojekt.ui.speciesLab

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import de.syntax_institut.androidabschlussprojekt.R


@Composable
fun SpeciesLabScreen(
    modifier: Modifier = Modifier,
    onNavigateToFacts: () -> Unit,
    onNavigateToIdentSpecies: () -> Unit,
    onNavigateToSpeciesCollection: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.home_bg),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
            alpha = 1f
        )
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
            Button(onClick = {onNavigateToSpeciesCollection()}) {
                Text("Go to Species Collection")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SpeciesLabScreenPreview() {
    SpeciesLabScreen(
        onNavigateToFacts = { },
        onNavigateToIdentSpecies = { },
        onNavigateToSpeciesCollection = { }
    )
}