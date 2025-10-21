package de.syntax_institut.androidabschlussprojekt

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.outlined.DeviceThermostat
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.material.icons.outlined.EmojiNature
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.syntax_institut.androidabschlussprojekt.ui.HomeScreen
import de.syntax_institut.androidabschlussprojekt.ui.climateLab.ClimateLabScreen
import de.syntax_institut.androidabschlussprojekt.ui.climateLab.climateFacts.ClimateFactsScreen
import de.syntax_institut.androidabschlussprojekt.ui.climateLab.co2quiz.CO2QuizResultScreen
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.SpeciesLabScreen
import de.syntax_institut.androidabschlussprojekt.ui.climateLab.co2quiz.QuestionnaireScreen
import de.syntax_institut.androidabschlussprojekt.ui.common.bottomBar.GlassmorphicBottomBar
import de.syntax_institut.androidabschlussprojekt.ui.ecoLab.EcoLabScreen
import de.syntax_institut.androidabschlussprojekt.ui.ecoLab.ecoTips.EcoTipsScreen
import de.syntax_institut.androidabschlussprojekt.ui.ecoLab.ecoFacts.EcoFactsScreen
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.speciesIdent.IdentifySpeciesScreen
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.speciesCollection.SpeciesCollectionScreen
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.speciesFacts.SpeciesFactsScreen
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute
// Species
@Serializable
object SpeciesLabRoute

@Serializable
object SpeciesFactsRoute

@Serializable
object SpeciesIdentRoute

@Serializable
object SpeciesCollectionRoute

// Climate
@Serializable
object ClimateLabRoute

@Serializable
object ClimateFactsRoute

@Serializable
object CO2QuizRoute

@Serializable
object CO2QuizResultRoute

// Eco
@Serializable
object EcoLabRoute

@Serializable
object EcoFactsRoute

@Serializable
object EcoTipsRoute


@Serializable
object ProfileRoute

/*
@Serializable
object MilestoneRoute
 */

enum class TabItem(
    val route: Any,
    val tabTitle: String,
    val tabIcon: ImageVector
) {
    HOME(HomeRoute, "Home", Icons.Default.Public),
    SPECIESLAB(SpeciesLabRoute, "Species", Icons.Outlined.EmojiNature),
    CLIMATEZONE(ClimateLabRoute, "Climate", Icons.Outlined.DeviceThermostat),
    ECOHUB(EcoLabRoute, "Eco", Icons.Outlined.Eco),
}


@Composable
fun AppStart(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    var selectedTab by rememberSaveable { mutableStateOf(TabItem.HOME) }
    val hazeState = remember { HazeState() }

    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = selectedTab.route,
            modifier = Modifier
                .fillMaxSize()
                .haze(
                    state = hazeState,
                    backgroundColor = MaterialTheme.colorScheme.background,
                    tint = Color.Black.copy(alpha = 0.3f),
                    blurRadius = 30.dp,
                )
        ) {
            composable<HomeRoute> {
                HomeScreen(
                    onNavigateToSpeciesLab = { navController.navigate(SpeciesLabRoute)},
                    onNavigateToClimateZone = { navController.navigate(ClimateLabRoute)},
                    onNavigateToEcoHub = { navController.navigate(EcoLabRoute)}
                )
            }
            composable<ProfileRoute> {

            }
            // Species
            composable<SpeciesLabRoute> {
                SpeciesLabScreen(
                    onNavigateToFacts = { navController.navigate(SpeciesFactsRoute)},
                    onNavigateToIdentSpecies = { navController.navigate(SpeciesIdentRoute)},
                    onNavigateToSpeciesCollection = { navController.navigate(SpeciesCollectionRoute)}
                )
            }
            composable<SpeciesFactsRoute> {
                SpeciesFactsScreen(onPopUpBackStack = { navController.popBackStack() })
            }
            composable<SpeciesIdentRoute> {
                IdentifySpeciesScreen(
                    onNavigateToCollection = { navController.navigate(SpeciesCollectionRoute)},
                    onPopUpBackStack = { navController.popBackStack() }
                )
            }
            composable<SpeciesCollectionRoute> {
                SpeciesCollectionScreen(onPopUpBackStack = { navController.popBackStack() })
            }
            // Climate
            composable<ClimateLabRoute> {
                ClimateLabScreen(
                    onNavigateToFacts = { navController.navigate(ClimateFactsRoute)},
                    onNavigateToCO2Quiz = { navController.navigate(CO2QuizRoute)},
                    onNavigateToCO2QuizResult = { navController.navigate(CO2QuizResultRoute)}
                )
            }
            composable<ClimateFactsRoute> {
                ClimateFactsScreen(onPopUpBackStack = { navController.popBackStack() })
            }
            composable<CO2QuizRoute> {
                QuestionnaireScreen(
                    onNavigateToResult = { navController.navigate(CO2QuizResultRoute)},
                    onPopUpBackStack = { navController.popBackStack() })
            }
            composable<CO2QuizResultRoute> {
                CO2QuizResultScreen(
                    onNavigateToCO2Quiz = { navController.navigate(CO2QuizRoute)},
                    onPopUpBackStack = { navController.popBackStack() })
            }

            // Eco
            composable<EcoLabRoute> {
                EcoLabScreen(
                    onNavigateToFacts = { navController.navigate(EcoFactsRoute)},
                    onNavigateToTips = { navController.navigate(EcoTipsRoute)},
                )
            }
            composable<EcoFactsRoute> {
                EcoFactsScreen(onPopUpBackStack = { navController.popBackStack() })
            }
            composable<EcoTipsRoute> {
                EcoTipsScreen(onPopUpBackStack = { navController.popBackStack() })
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            GlassmorphicBottomBar(
                hazeState = hazeState,
                selectedTab = selectedTab,
                onTabSelected = { tab ->
                    selectedTab = tab
                    navController.navigate(tab.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppStartPreview() {
    AppStart()
}