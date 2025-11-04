package de.syntax_institut.androidabschlussprojekt.ui.climateLab

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun ClimateLabScreen(
    modifier: Modifier = Modifier,
    onNavigateToFacts: () -> Unit,
    onNavigateToCO2Quiz: () -> Unit,
) {
    FullScreenBox(
        bgImage = R.drawable.bg_climatelab,
        showButton = false
    ) {
        Box(modifier = Modifier,
            contentAlignment = Alignment.TopCenter) {
            Box(
                modifier = Modifier.padding(top = 300.dp, end = 230.dp)
                    .width(100.dp)
                    .height(300.dp)
                    .clip(RoundedCornerShape(20.dp))
                    //.background(color = Color.Red.copy(alpha = 0.5f))
                    .clickable(onClick = { onNavigateToFacts() })
            )
            Box(
                modifier = Modifier.padding(top = 340.dp, start = 220.dp)
                    .width(140.dp)
                    .height(300.dp)
                    .clip(RoundedCornerShape(20.dp))
                    //.background(color = Color.Red.copy(alpha = 0.5f))
                    .clickable(onClick = { onNavigateToCO2Quiz() })
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ClimateLabScreenPreview() {
    ClimateLabScreen(
        onNavigateToFacts = { },
        onNavigateToCO2Quiz = { }
    )
}