package de.syntax_institut.androidabschlussprojekt.ui.common.card

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState


@Composable
fun CardButtonBar(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    onNavigateBack: () -> Unit,
    onFavClick: () -> Unit,
    onNavigateForward: () -> Unit,
    isFavorite: Boolean
) {
        Row(modifier = Modifier) {
            CustomButton(
                modifier = Modifier
                    .height(64.dp)
                    .padding(10.dp),
                hazeState = hazeState,
                buttonIcon = Icons.Default.ArrowBackIosNew,
                buttonText = null,
                onClick = onNavigateBack
            )
            CustomButton(
                modifier = Modifier
                    .height(64.dp)
                    .padding(10.dp),
                hazeState = hazeState,
                buttonIcon = if (isFavorite) Icons.Default.Bookmark else {
                    Icons.Default.BookmarkBorder
                },
                buttonText = null,
                onClick = onFavClick
            )
            CustomButton(
                modifier = Modifier
                    .height(64.dp)
                    .padding(10.dp),
                hazeState = hazeState,
                buttonIcon = Icons.AutoMirrored.Filled.ArrowForwardIos,
                buttonText = null,
                onClick = onNavigateForward
            )
        }
    }




@Preview(showBackground = true)
@Composable
fun CardButtonBarPreview() {
    CardButtonBar(
        hazeState = HazeState(),
        onNavigateBack = {},
        onFavClick = {},
        onNavigateForward = {},
        isFavorite = true
    )
}