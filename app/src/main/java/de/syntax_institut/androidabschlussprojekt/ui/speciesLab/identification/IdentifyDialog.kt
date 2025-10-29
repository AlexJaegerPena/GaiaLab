package de.syntax_institut.androidabschlussprojekt.ui.speciesLab.identification

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.data.model.SpeciesApiResponse
import de.syntax_institut.androidabschlussprojekt.ui.common.CustomButton
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardContent
import de.syntax_institut.androidabschlussprojekt.ui.theme.MyTypography
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.collection.CollectedSpeciesViewModel
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.ImgBBViewModel
import de.syntax_institut.androidabschlussprojekt.util.cardImageBorder
import dev.chrisbanes.haze.HazeState
import org.koin.androidx.compose.koinViewModel
import kotlin.math.ceil


@Composable
fun IdentifyDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    isLoading: Boolean,
    result: SpeciesApiResponse?,
    hazeState: HazeState,
    collectedSpeciesVM: CollectedSpeciesViewModel,
    imgBBVM: ImgBBViewModel = koinViewModel(),
    selectedImage: Uri? = null
) {

    val imageUrl by imgBBVM.imageUrl.collectAsState()
    val isUploading by imgBBVM.isUploading.collectAsState()

    val nameToSave = remember(result) {
        result?.predictions?.firstOrNull()?.taxa?.items?.firstOrNull()?.let {
            val name = it.scientific_name
            val probability = ceil(it.probability * 100).toInt()
            "${name}\n(${probability}%)"
        } ?: ""
    }


    LaunchedEffect(imageUrl) {
        if (imageUrl != null && nameToSave.isNotEmpty()) {
            collectedSpeciesVM.addSpeciesToCollection(imageUrl!!, nameToSave)
            onDismiss()
        }
    }

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(500.dp)
                .clip(RoundedCornerShape(36.dp))
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(R.drawable.bg_carditem),
                    contentDescription = null,
                    modifier = Modifier.matchParentSize(),
                    contentScale = ContentScale.FillBounds,
                )
                Column(modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("Identification result".uppercase(),
                        style = MyTypography.titleLarge,
                        color = CardContent
                    )
                    HorizontalDivider(modifier.padding(start = 28.dp, end = 28.dp, bottom = 20.dp),thickness = 3.dp, color = CardContent)


                    ImageLayoutView(selectedImage = selectedImage)

                    /*
                    Image(
                        painter = painterResource(R.drawable.buteo),
                        contentDescription = "",
                        modifier = Modifier
                            .width(200.dp)
                            .height(200.dp)
                            .cardImageBorder()
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )

                     */

                    if (isLoading || isUploading) {
                        LinearProgressIndicator(color = CardContent, modifier = Modifier.padding(vertical = 5.dp, horizontal = 40.dp))
                    }

                    val firstItem = result?.predictions?.firstOrNull()?.taxa?.items?.firstOrNull()
                    firstItem?.let {
                        var name = firstItem.scientific_name
                        var probability = ceil(firstItem.probability * 100).toInt()
                        Text(
                            "${name} (${probability}%)",
                            style = MyTypography.titleMedium,
                            color = CardContent
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                        CustomButton(
                            modifier = Modifier.height(40.dp),
                            hazeState = hazeState,
                            buttonIcon = Icons.Default.Close,
                            buttonText = "Cancel",
                            onClick = { onDismiss() }
                        )
                        CustomButton(
                            modifier = Modifier.height(40.dp),
                            hazeState = hazeState,
                            buttonIcon = Icons.Default.Save,
                            buttonText = "Save",
                            enabled = !isUploading && selectedImage != null,
                            onClick = {
                                selectedImage?.let {
                                    imgBBVM.uploadImage(it)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun ImageLayoutView(selectedImage: Uri?) {
    AsyncImage(
        model = selectedImage,
        contentDescription = null,
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .cardImageBorder()
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop,
    )
}


/*
@Preview(showBackground = true)
@Composable
fun IdentDialogPreview() {
    IdentDialog(
        onDismiss = {},
        isLoading = false,
        result = ,
        hazeState = HazeState(),
        collectedSpeciesVM = ,
    )
}

 */