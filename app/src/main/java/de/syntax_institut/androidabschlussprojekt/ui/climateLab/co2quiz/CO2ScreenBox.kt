package de.syntax_institut.androidabschlussprojekt.ui.climateLab.co2quiz

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.ui.common.GlassmorphicButton
import de.syntax_institut.androidabschlussprojekt.ui.common.FullScreenBox
import de.syntax_institut.androidabschlussprojekt.ui.theme.MyTypography
import dev.chrisbanes.haze.HazeState


@Composable
fun CO2ScreenBox(
    modifier: Modifier = Modifier,
    onNavigateToTips: () -> Unit,
    onPopupBackStack: () -> Unit,
    onSecondButtonClick: () -> Unit,
    quizVM: CO2QuizViewModel,
    resultVM: CO2QuizResultViewModel,
    hazeState: HazeState,
    secondButtonIcon: ImageVector,
    content: @Composable (() -> Unit)
) {



    FullScreenBox(
        bgImage = R.drawable.bg_co2quiz,
        buttonTopPadding = 40.dp,
        onClick = { onPopupBackStack() },
        showSecondButton = true,
        onSecondButtonClick = {
            onSecondButtonClick()
        },
        secondButtonIcon = secondButtonIcon
    ) {
        Column(modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            content()

            GlassmorphicButton(
                modifier = Modifier
                    .height(140.dp)
                    .width(280.dp)
                    .padding(top = 60.dp),
                hazeState = hazeState,
                buttonIcon = Icons.Default.Info,
                buttonText = "Climate tips".uppercase(),
                bgAlpha = 0.2f,
                textStyle = MyTypography.titleLarge,
                onClick = { onNavigateToTips() }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CO2ScreenBoxPreview() {
    CO2ScreenBox(
        onNavigateToTips = {},
        onPopupBackStack = {},
        onSecondButtonClick = {},
        quizVM = viewModel(),
        resultVM = viewModel(),
        hazeState = HazeState(),
        content = {},
        secondButtonIcon = Icons.Default.Info
    )
}