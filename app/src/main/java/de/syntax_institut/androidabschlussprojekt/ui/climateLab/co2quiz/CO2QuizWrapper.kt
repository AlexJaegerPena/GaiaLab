package de.syntax_institut.androidabschlussprojekt.ui.climateLab.co2quiz

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.androidabschlussprojekt.R
import de.syntax_institut.androidabschlussprojekt.ui.common.CustomButton
import de.syntax_institut.androidabschlussprojekt.ui.common.FullScreenBox
import de.syntax_institut.androidabschlussprojekt.ui.theme.MyTypography
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.CO2QuizResultViewModel
import dev.chrisbanes.haze.HazeState
import org.koin.androidx.compose.koinViewModel


@Composable
fun CO2QuizWrapper(
    modifier: Modifier = Modifier,
    onNavigateToTips: () -> Unit,
    onPopUpBackStack: () -> Unit,
    quizVM: CO2QuizViewModel = koinViewModel(),
    resultVM: CO2QuizResultViewModel = koinViewModel()

) {

    val hazeState = remember { HazeState() }
    var showResult by remember { mutableStateOf(false) }

    FullScreenBox(
        bgImage = R.drawable.bg_co2quiz,
        buttonTopPadding = 40.dp,
        onClick = { if (showResult) { showResult = false } else { onPopUpBackStack() }},
        showSecondButton = true,
        onSecondButtonClick = { showResult = !showResult },
        secondButtonIcon = if (showResult) { Icons.Outlined.Quiz } else { Icons.Outlined.Leaderboard }
    ) {
        Column(modifier = Modifier) {
            if (showResult) {
                CO2QuizResultScreen(
                    modifier = modifier,
                    // onNavigateToCO2Quiz = { onNavigateToCO2Quiz() },
                    // onNavigateToClimateLab = { onNavigateToClimateLab() },
                    quizVM = quizVM,
                    resultVM = resultVM,
                    hazeState = hazeState
                )
            } else {
                CO2QuizScreen(
                    modifier = modifier,
                    onNavigateToResult = { showResult = true },
                    // onPopUpBackStack = { onPopUpBackStack() },
                    quizVM = quizVM,
                    hazeState = hazeState
                )
            }
            CustomButton(
                modifier = Modifier
                    .height(70.dp)
                    .width(310.dp)
                    .padding(start = 8.dp),
                hazeState = hazeState,
                buttonIcon = Icons.Default.Info,
                buttonText = "Climate tips".uppercase(),
                textStyle = MyTypography.titleLarge,
                onClick = { onNavigateToTips() }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CO2QuizWrapperPreview() {
    CO2QuizWrapper(
        onNavigateToTips = {},
        onPopUpBackStack = {},
        quizVM = viewModel(),
        resultVM = viewModel()
    )
}