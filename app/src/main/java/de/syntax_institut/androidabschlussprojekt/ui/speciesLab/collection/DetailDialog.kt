package de.syntax_institut.androidabschlussprojekt.ui.speciesLab.collection

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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.data.model.firestore.CollectedSpecies
import de.syntax_institut.androidabschlussprojekt.ui.common.GlassmorphicButton
import de.syntax_institut.androidabschlussprojekt.ui.common.GlassmorphicBorder
import de.syntax_institut.androidabschlussprojekt.ui.theme.CardContent
import de.syntax_institut.androidabschlussprojekt.ui.theme.MyTypography
import de.syntax_institut.androidabschlussprojekt.util.cardImageBorder
import dev.chrisbanes.haze.HazeState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun DetailDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    hazeState: HazeState,
    species: CollectedSpecies,
    collectedSpeciesVM: CollectedSpeciesViewModel
) {

    val dateFormatter = remember { SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN) }

    val formattedDate = remember(species.savedAt) {
        dateFormatter.format(Date(species.savedAt))
    }

    Dialog(
        onDismissRequest = { onDismiss() }
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

                if (species != null) {
                    Column(modifier = Modifier.padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(species.name.uppercase(),
                            style = MyTypography.titleMedium,
                            color = CardContent,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(top = 30.dp)
                        )
                        GlassmorphicBorder(
                            hazeState = hazeState
                        ) {
                            AsyncImage(
                                model = species.imageUrl,
                                contentDescription = species.name,
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(200.dp)
                                    .padding(2.dp)
                                    .clip(RoundedCornerShape(18.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }

                        Text(
                            "Collected: ${formattedDate}",
                            style = MyTypography.bodyMedium,
                            color = CardContent
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                            GlassmorphicButton(
                                modifier = Modifier.height(40.dp),
                                hazeState = hazeState,
                                buttonIcon = Icons.Default.Delete,
                                buttonText = "Delete",
                                glowColor = Color(0xFFFF5E5E),
                                bgAlpha = 0.2f,
                                onClick = {
                                    collectedSpeciesVM.removeCollectedSpecies(speciesId = species.speciesId)
                                    onDismiss()
                                }
                            )
                            GlassmorphicButton(
                                modifier = Modifier.height(40.dp),
                                hazeState = hazeState,
                                buttonIcon = Icons.Default.Check,
                                buttonText = "OK",
                                glowColor = Color(0xFF0AFFCE),
                                bgAlpha = 0.3f,
                                onClick = { onDismiss() }
                            )
                        }
                    }
                } else {
                    CircularProgressIndicator(color = CardContent)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailDialogPreview() {
    DetailDialog(
        onDismiss = {},
        hazeState = HazeState(),
        species = CollectedSpecies(),
        collectedSpeciesVM= viewModel()
    )
}