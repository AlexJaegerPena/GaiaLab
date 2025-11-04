package de.syntax_institut.androidabschlussprojekt.ui.speciesLab

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        Box(modifier = Modifier,
            contentAlignment = Alignment.TopCenter) {
            Box(
                modifier = Modifier.padding(top = 330.dp, start = 240.dp)
                    .width(100.dp)
                    .height(300.dp)
                    .clip(RoundedCornerShape(20.dp))
                    //.background(color = Color.Red.copy(alpha = 0.5f))
                    .clickable(onClick = { onNavigateToFacts() })
            )
            Box(
                modifier = Modifier.padding(top = 280.dp, end = 200.dp)
                    .width(180.dp)
                    .height(360.dp)
                    .clip(RoundedCornerShape(20.dp))
                    //.background(color = Color.Red.copy(alpha = 0.5f))
                    .clickable(onClick = { onNavigateToIdentSpecies() })
            )
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