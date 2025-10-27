package de.syntax_institut.androidabschlussprojekt.ui.speciesLab.collection

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import de.syntax_institut.androidabschlussprojekt.data.model.firestore.CollectedSpecies
import de.syntax_institut.androidabschlussprojekt.util.cardImageBorder
import dev.chrisbanes.haze.HazeState
import kotlinx.coroutines.delay


@Composable
fun CollectionItem(
    modifier: Modifier = Modifier,
    species: CollectedSpecies,
    hazeState: HazeState,
    collectedSpeciesVM: CollectedSpeciesViewModel,
) {

    var showDetails by remember { mutableStateOf(false)}
    var isClicked by remember { mutableStateOf(false) }

    LaunchedEffect(isClicked) {
        if (isClicked) {
            showDetails = true
            delay(120)
            isClicked = false
        }
    }

    if (showDetails) {
        DetailDialog(onDismiss = { showDetails = false }, hazeState = hazeState, species = species, collectedSpeciesVM = collectedSpeciesVM)
    }

    AsyncImage(
        model = species.imageUrl,
        contentDescription = species.name,
        modifier = Modifier
            .width(130.dp)
            .height(140.dp)
            .cardImageBorder(cornerRadius = 10.dp)
            .clip(RoundedCornerShape(2.dp))
            .clickable(onClick = { showDetails = true }),
        contentScale = ContentScale.Crop,
    )
}


@Preview(showBackground = true)
@Composable
fun CollectionItemPreview() {
    CollectionItem(
        species = CollectedSpecies(
            imageUrl = "https://i.ibb.co/example.jpg",
            name = "Test Species"
        ),
        hazeState = HazeState(),
        collectedSpeciesVM = viewModel()
    )
}