package de.syntax_institut.androidabschlussprojekt.ui.userProfile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.EmojiNature
import androidx.compose.material.icons.filled.NaturePeople
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState


@Composable
fun ProfileGrid(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    onShowCO2Result: () -> Unit,
    onShowSpecies: () -> Unit,
    onShowFavFacts: () -> Unit,
    onShowFavTips: () -> Unit
) {


    val items: List<GridItemContent> = listOf(
        GridItemContent(Icons.Default.NaturePeople, "CO₂ Score") { onShowCO2Result() },
        GridItemContent(Icons.Default.EmojiNature, "My Species") { onShowSpecies() },
        GridItemContent(Icons.Default.Bookmarks, "Fav Facts") { onShowFavFacts() },
        GridItemContent(Icons.Default.Bookmarks, "Fav Tips") { onShowFavTips() },
    )

    LazyVerticalGrid(
        GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(horizontal = 4.dp)
    ) {
        items(items) { item ->
            ProfileGridItem(
                hazeState = hazeState,
                buttonIcon = item.icon,
                buttonText = item.text,
                onClick = { item.onClick() }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileGridPreview() {
    ProfileGrid(
        hazeState = HazeState(),
        onShowCO2Result = {},
        onShowSpecies = {},
        onShowFavFacts = {},
        onShowFavTips = {},
    )
}