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
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.CardContent
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Fact
import de.syntax_institut.androidabschlussprojekt.data.model.myApi.Tip


@Composable
fun CardImage(
    modifier: Modifier = Modifier,
    data: CardContent
) {

    data.imageUrl?.let { url ->
        Box(
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF0C5F6D),
                            Color(0xFF66C6BA)
                        )
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(3.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            AsyncImage(
                model = url,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,
                contentDescription = "Image for ${data.title}",
                placeholder = painterResource(id = R.drawable.bg_home), // TODO: loading Placeholder image
                error = painterResource(id = R.drawable.bg_home), // TODO: error placeholder image
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CardImagePreview() {

    CardImage(data = Fact(
        id = 1,
        title = "test",
        text = "testealsdkjnsfdkj sdlkfjl slkdfjlksjdflk lskdjflk sdfklölsdjkfösdjkdf lkdjfölsjdf sjdflösdjfl lkdjkf djfjl  dldjlfk",
        category = "animal",
        imageUrl = painterResource(id = R.drawable.bg_home).toString(),
        infoUrl = ""
    ))
}