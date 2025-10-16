package de.syntax_institut.androidabschlussprojekt.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Fact


@Composable
fun FactImage(
    modifier: Modifier = Modifier,
    fact: Fact
) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFDDA130),
                        Color(0xFF5E6C3B)
                    )
                ),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(3.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        AsyncImage(
            model = fact.imageUrl,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop,
            contentDescription = "Image for ${fact.title}",
            placeholder = painterResource(id = R.drawable.bg_home), // TODO: loading Placeholder image
            error = painterResource(id = R.drawable.bg_home), // TODO: error placeholder image
        )
    }
}


@Preview(showBackground = true)
@Composable
fun FactImagePreview() {
    FactImage(fact = Fact(
            id = 1,
            title = "test",
            text = "testealsdkjnsfdkj sdlkfjl slkdfjlksjdflk lskdjflk sdfklölsdjkfösdjkdf lkdjfölsjdf sjdflösdjfl lkdjkf djfjl  dldjlfk",
            category = "animal",
            imageUrl = painterResource(id = R.drawable.bg_home).toString(),
            infoUrl = ""
        ))
}