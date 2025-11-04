package de.syntax_institut.androidabschlussprojekt.ui.speciesLab.identification

import android.R.attr.onClick
import android.R.attr.textStyle
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.syntax_institut.androidabschlussprojekt.ui.common.CustomButton
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.collection.CollectedSpeciesViewModel
import de.syntax_institut.androidabschlussprojekt.ui.theme.MyTypography
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.ImgBBViewModel
import dev.chrisbanes.haze.HazeState
import org.koin.androidx.compose.koinViewModel


@Composable
fun PhotoIdentification(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    identifyVM: IdentifySpeciesViewModel = koinViewModel(),
    collectedSpeciesVM: CollectedSpeciesViewModel = koinViewModel()
) {
    val context = LocalContext.current

    val result by identifyVM.result.collectAsState()
    val isLoading by identifyVM.isLoading.collectAsState()

    var selectedImage by remember { mutableStateOf<Uri?>(null) }
    var showDialog by remember { mutableStateOf(false)}


    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImage = uri }
    )

    fun launchPhotoPicker() {
        singlePhotoPickerLauncher.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        )
    }

    LaunchedEffect(selectedImage) {
        selectedImage?.let { uri ->
            identifyVM.identifyImage(uri, context)
            showDialog = true
        }
    }

    if (showDialog) {
        IdentifyDialog(
            onDismiss = { showDialog = false},
            isLoading = isLoading,
            result = result,
            hazeState = hazeState,
            collectedSpeciesVM = collectedSpeciesVM,
            selectedImage = selectedImage
        )
    }
        CustomButton(
            modifier = Modifier
                .height(70.dp)
                .width(310.dp)
                .padding(start = 8.dp),
            hazeState = hazeState,
            buttonIcon = Icons.Default.PhotoCamera,
            buttonText = "Identify Species".uppercase(),
            textStyle = MyTypography.titleLarge,
            onClick = { launchPhotoPicker() }
        )
}


@Preview(showBackground = true)
@Composable
fun PhotoIdentificationPreview() {
    PhotoIdentification(hazeState = HazeState())
}