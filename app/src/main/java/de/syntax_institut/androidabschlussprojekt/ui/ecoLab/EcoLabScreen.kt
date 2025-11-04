package de.syntax_institut.androidabschlussprojekt.ui.ecoLab

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
fun EcoLabScreen(
    modifier: Modifier = Modifier,
    onNavigateToFacts: () -> Unit,
    onNavigateToTips: () -> Unit
) {
    FullScreenBox(
        bgImage = R.drawable.bg_ecolab,
        alpha = 1f,
        showButton = false
    ) {
        Box(modifier = Modifier,
            contentAlignment = Alignment.TopCenter) {
            Box(
                modifier = Modifier.padding(top = 310.dp, end = 250.dp)
                    .width(100.dp)
                    .height(310.dp)
                    .clip(RoundedCornerShape(20.dp))
                    //.background(color = Color.Red.copy(alpha = 0.5f))
                    .clickable(onClick = { onNavigateToFacts() })
            )
            Box(
                modifier = Modifier.padding(top = 330.dp, start = 240.dp)
                    .width(110.dp)
                    .height(310.dp)
                    .clip(RoundedCornerShape(20.dp))
                    //.background(color = Color.Red.copy(alpha = 0.5f))
                    .clickable(onClick = { onNavigateToTips() })
            )
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