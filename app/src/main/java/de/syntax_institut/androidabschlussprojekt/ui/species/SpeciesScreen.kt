package de.syntax_institut.androidabschlussprojekt.ui.species

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.koin.androidx.compose.koinViewModel
import kotlin.math.ceil


@Composable
fun SpeciesScreen(
    modifier: Modifier = Modifier,
    viewModel: SpeciesViewModel = koinViewModel()
) {
    val context = LocalContext.current

    val result by viewModel.result.collectAsState()

    var bitmap by remember { mutableStateOf<android.graphics.Bitmap?>(null) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                // Bild aus Assets laden
                val inputStream = context.assets.open("frog.jpg")
                val bytes = inputStream.readBytes()
                inputStream.close()

                // MultipartBody erstellen
                val requestBody = bytes.toRequestBody("image/jpeg".toMediaTypeOrNull())
                val multipartBody =
                    MultipartBody.Part.createFormData("image", "buteo2.jpg", requestBody)

                // Bitmap erstellen, um Bild anzuzeigen
                bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)

                viewModel.identify(multipartBody)
            }
        ) {
            Text("Bild hochladen")
        }

        bitmap?.let {
            Image(painter = BitmapPainter(it.asImageBitmap()), contentDescription = "")
        }

        result?.predictions?.firstOrNull()?.taxa?.items?.let { items ->
                items.forEach {
                    if (it.probability * 100 > 1) {
                        Text("${it.scientific_name}: ${ceil(it.probability*100).toInt()} %")
                    }
                }

        }
    }
}



@Preview(showBackground = true)
@Composable
fun SpeciesScreenPreview() {
    SpeciesScreen()
}