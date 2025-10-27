package de.syntax_institut.androidabschlussprojekt.ui

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
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToSpeciesLab: () -> Unit,
    onNavigateToClimateZone: () -> Unit,
    onNavigateToEcoHub: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    FullScreenBox(
        bgImage = R.drawable.bg_home,
        alpha = 1f,
        showButton = false
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Species Lab Screen")
            Button(onClick = {onNavigateToSpeciesLab()}) {
                Text("Go to Species Lab")
            }
            Button(onClick = {onNavigateToClimateZone()}) {
                Text("Go to Climate Lab")
            }
            Button(onClick = {onNavigateToEcoHub()}) {
                Text("Go to Eco Lab")
            }
            Button(onClick = {onNavigateToProfile()}) {
                Text("Go to Profile")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onNavigateToSpeciesLab = {},
        onNavigateToClimateZone = {},
        onNavigateToEcoHub = {},
        onNavigateToProfile = {}
    )
}