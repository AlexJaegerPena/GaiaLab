package de.syntax_institut.androidabschlussprojekt.ui.ecoLab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.util.FullScreenBox


@Composable
fun EcoLabScreen(
    modifier: Modifier = Modifier,
    onNavigateToFacts: () -> Unit,
    onNavigateToTips: () -> Unit
) {
    FullScreenBox(
        bgImage = R.drawable.bg_ecolab,
        alpha = 1f,
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

}


@Preview(showBackground = true)
@Composable
fun EcoLabScreenPreview() {
    EcoLabScreen(
        onNavigateToFacts = {},
        onNavigateToTips = {}
    )
}