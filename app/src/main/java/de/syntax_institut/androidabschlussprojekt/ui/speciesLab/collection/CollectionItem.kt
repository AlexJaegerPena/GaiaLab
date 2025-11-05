package de.syntax_institut.androidabschlussprojekt.ui.speciesLab.collection

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import de.syntax_institut.androidabschlussprojekt.data.model.firestore.CollectedSpecies
import de.syntax_institut.androidabschlussprojekt.ui.common.GlassmorphicBorder
import dev.chrisbanes.haze.HazeState
import kotlinx.coroutines.delay


@Composable
fun CollectionItem(
    modifier: Modifier = Modifier,
    species: CollectedSpecies,
    hazeState: HazeState,
    collectedSpeciesVM: CollectedSpeciesViewModel,
    shape: Shape = RoundedCornerShape(18.dp),
    glowColor: Color = Color(0xFF79ADA9),
    enabled: Boolean = true
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

    GlassmorphicBorder(
        hazeState = hazeState
    ) {
        AsyncImage(
            model = species.imageUrl,
            contentDescription = species.name,
            modifier = Modifier
                .aspectRatio(1f)
                .padding(2.dp)
                .clip(RoundedCornerShape(18.dp))
                .clickable(onClick = { showDetails = true }),
            contentScale = ContentScale.Crop
        )
    }
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