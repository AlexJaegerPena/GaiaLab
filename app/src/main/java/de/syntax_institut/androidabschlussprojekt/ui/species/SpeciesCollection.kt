package de.syntax_institut.androidabschlussprojekt.ui.species

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.util.FullScreenBox


@Composable
fun SpeciesCollection(
    modifier: Modifier = Modifier
) {

    // val speciesCollection
    FullScreenBox(
        bgImage = R.drawable.species_bg,
        alpha = 0.4f,
        content = {

        }
    )
}


@Preview(showBackground = true)
@Composable
fun SpeciesCollectionPreview() {
    SpeciesCollection()
}