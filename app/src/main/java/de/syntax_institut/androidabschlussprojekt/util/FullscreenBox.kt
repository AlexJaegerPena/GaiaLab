package de.syntax_institut.androidabschlussprojekt.util

import android.R.attr.button
import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.Back
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import de.syntax_institut.androidabschlussprojekt.ui.common.CustomButton
import dev.chrisbanes.haze.HazeState


@Composable
fun FullScreenBox(
    modifier: Modifier = Modifier,
    bgImage: Int,
    alpha: Float = 1f,
    content: @Composable (() -> Unit)
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(bgImage),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
            alpha = alpha
        )
        /*
        IconButton(onClick = onClick) {
            Icon(Icons.Filled.ArrowBackIosNew,
                contentDescription = "back", modifier = Modifier.padding(10.dp).neonCyanBorder())
        }
         */
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}
