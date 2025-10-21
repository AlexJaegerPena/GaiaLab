package de.syntax_institut.androidabschlussprojekt.util

import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import de.syntax_institut.androidabschlussprojekt.ui.common.CustomBackButton
import de.syntax_institut.androidabschlussprojekt.ui.common.card.CustomButton
import dev.chrisbanes.haze.HazeState


@Composable
fun FullScreenBox(
    modifier: Modifier = Modifier,
    bgImage: Int,
    alpha: Float = 1f,
    showButton: Boolean = true,
    onClick: () -> Unit = { },
    content: @Composable (() -> Unit)
) {

    val hazeState = remember { HazeState() }


    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(bgImage),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
            alpha = alpha
        )
        if (showButton) {
            CustomBackButton(
                modifier = Modifier
                    .padding(start = 20.dp, top = 40.dp)
                    .height(30.dp)
                    .width(30.dp),
                hazeState = hazeState,
                onClick = onClick
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}
