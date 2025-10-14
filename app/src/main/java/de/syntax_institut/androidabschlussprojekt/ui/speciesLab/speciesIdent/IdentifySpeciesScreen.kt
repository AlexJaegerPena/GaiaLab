package de.syntax_institut.androidabschlussprojekt.ui.speciesLab.speciesIdent

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.IdentifySpeciesViewModel
import de.syntax_institut.androidabschlussprojekt.util.FullScreenBox
import de.syntax_institut.androidabschlussprojekt.util.comicBorder
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.koin.androidx.compose.koinViewModel
import kotlin.math.ceil


@Composable
fun IdentifySpeciesScreen(
    modifier: Modifier = Modifier,
    onPopUpBackStack: () -> Unit,
    onNavigateToCollection: () -> Unit,

    viewModel: IdentifySpeciesViewModel = koinViewModel()
) {
    val context = LocalContext.current

    val result by viewModel.result.collectAsState()

    var bitmap by remember { mutableStateOf<Bitmap?>(null) }


    FullScreenBox(
        bgImage = R.drawable.bg_speciesscanner,
        alpha = 1f
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(onClick = { onPopUpBackStack()} ) {
                Text("Zurück")
            }
            if (bitmap != null) {
                Image(
                    painter = BitmapPainter(bitmap!!.asImageBitmap()),
                    contentDescription = "",
                    modifier = Modifier
                        .rotate(-8f)
                        .height(180.dp)
                        .width(203.dp)
                        .padding(end = 26.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(modifier = Modifier
                    .rotate(-8f)
                    .height(180.dp)
                    .width(203.dp)
                    .padding(end = 26.dp),
                ) {
                    Text("Species Identification")
                }
            }
            Column(modifier = Modifier
                .rotate(-7f)
                .padding(top = 20.dp, start = 10.dp )
                .height(100.dp)
            ) {
                result?.predictions?.firstOrNull()?.taxa?.items?.let { items ->
                    items.forEach {
                        if (it.probability * 100 > 1) {
                            Text("${it.scientific_name}: ${ceil(it.probability * 100).toInt()} %",
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                }
            }

            Button(
                modifier = Modifier.comicBorder(),
                colors = ButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.White,
                    disabledContentColor = Color.Black
                ),
                onClick = {
                    // Bild aus Assets laden
                    val inputStream = context.assets.open("buteo2.jpg")
                    val bytes = inputStream.readBytes()
                    inputStream.close()

                    // MultipartBody erstellen
                    val requestBody = bytes.toRequestBody("image/jpeg".toMediaTypeOrNull())
                    val multipartBody =
                        MultipartBody.Part.createFormData("image", "buteo2.jpg", requestBody)

                    // Bitmap erstellen um Bild anzuzeigen
                    bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)

                    viewModel.identify(multipartBody)
                }
            ) {
                Text("Identify species")
            }
            Button(
                modifier = Modifier.comicBorder(),
                colors = ButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.White,
                    disabledContentColor = Color.Black
                ),
                onClick = {

                }
            ) {
                Text("See collection")
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun SpeciesScreenPreview() {
    IdentifySpeciesScreen(onNavigateToCollection = {}, onPopUpBackStack = {})
}