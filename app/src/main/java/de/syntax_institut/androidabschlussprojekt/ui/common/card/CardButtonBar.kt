package de.syntax_institut.androidabschlussprojekt.ui.common.card

import android.R.attr.onClick
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import de.syntax_institut.androidabschlussprojekt.ui.common.CustomButton
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import kotlinx.coroutines.delay


@Composable
fun CardButtonBar(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    onNavigateBack: () -> Unit,
    onFavClick: () -> Unit,
    onNavigateForward: () -> Unit
) {

    val isFavorite = remember { mutableStateOf(false) }

        Row(modifier = Modifier) {
            CustomButton(
                hazeState = hazeState,
                buttonIcon = Icons.Default.ArrowBackIosNew,
                buttonText = null,
                onClick = onNavigateBack
            )
            CustomButton(
                hazeState = hazeState,
                buttonIcon = if (isFavorite.value) Icons.Default.Bookmark else {
                    Icons.Default.BookmarkBorder
                },
                buttonText = null,
                onClick = onFavClick
            )
            CustomButton(
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
        onNavigateForward = {}
    )
}