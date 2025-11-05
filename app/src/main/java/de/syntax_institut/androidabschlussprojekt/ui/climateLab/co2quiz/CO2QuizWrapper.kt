package de.syntax_institut.androidabschlussprojekt.ui.climateLab.co2quiz

/*
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
        Column(modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (showResult) {
                CO2QuizResultScreen(
                    modifier = modifier,
                    quizVM = quizVM,
                    resultVM = resultVM,
                    hazeState = hazeState
                )
            } else {
                CO2QuizScreen(
                    modifier = modifier,
                    onNavigateToResult = { showResult = true },
                    quizVM = quizVM,
                    resultVM = resultVM,
                    hazeState = hazeState
                )
            }
            CustomButton(
                modifier = Modifier
                    .height(140.dp)
                    .width(280.dp)
                    .padding(top = 60.dp),
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
 */