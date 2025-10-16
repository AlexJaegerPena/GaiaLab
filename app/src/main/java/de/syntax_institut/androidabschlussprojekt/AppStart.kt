package de.syntax_institut.androidabschlussprojekt

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Compost
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Recycling
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.outlined.Compost
import androidx.compose.material.icons.outlined.DeviceThermostat
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.material.icons.outlined.EmojiNature
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import de.syntax_institut.androidabschlussprojekt.ui.climateZone.ClimateZoneScreen
import de.syntax_institut.androidabschlussprojekt.ui.climateZone.climateFacts.ClimateFactsScreen
import de.syntax_institut.androidabschlussprojekt.ui.climateZone.questionnaire.QuestionnaireResultScreen
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.SpeciesLabScreen
import de.syntax_institut.androidabschlussprojekt.ui.climateZone.questionnaire.QuestionnaireScreen
import de.syntax_institut.androidabschlussprojekt.ui.common.bottomBar.GlassmorphicBottomBar
import de.syntax_institut.androidabschlussprojekt.ui.ecoHub.EcoHubScreen
import de.syntax_institut.androidabschlussprojekt.ui.ecoHub.ecoTips.EcoTipsScreen
import de.syntax_institut.androidabschlussprojekt.ui.ecoHub.ecoFacts.EcoFactsScreen
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
object ClimateZoneRoute

@Serializable
object ClimateFactsRoute

@Serializable
object QuestionnaireRoute

@Serializable
object QuestionnaireResultRoute

// Eco
@Serializable
object EcoHubRoute

@Serializable
object EcoFactsRoute

@Serializable
object EcoTipsRoute

/*
@Serializable
object ProfileRoute

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
    CLIMATEZONE(ClimateZoneRoute, "Climate", Icons.Outlined.DeviceThermostat),
    ECOHUB(EcoHubRoute, "Eco", Icons.Outlined.Eco),
}


@Composable
fun AppStart(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    var selectedTab by rememberSaveable { mutableStateOf(TabItem.SPECIESLAB) }
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
                    onNavigateToClimateZone = { navController.navigate(ClimateZoneRoute)},
                    onNavigateToEcoHub = { navController.navigate(EcoHubRoute)}
                )
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
            composable<ClimateZoneRoute> {
                ClimateZoneScreen(
                    onNavigateToFacts = { navController.navigate(ClimateFactsRoute)},
                    onNavigateToQuestionnaire = { navController.navigate(QuestionnaireRoute)},
                    onNavigateToQuestionnaireResult = { navController.navigate(QuestionnaireResultRoute)}
                )
            }
            composable<ClimateFactsRoute> {
                ClimateFactsScreen(onPopUpBackStack = { navController.popBackStack() })
            }
            composable<QuestionnaireRoute> {
                QuestionnaireScreen(
                    onNavigateToResult = { navController.navigate(QuestionnaireResultRoute)},
                    onPopUpBackStack = { navController.popBackStack() })
            }
            composable<QuestionnaireResultRoute> {
                QuestionnaireResultScreen(
                    onNavigateToQuestionnaire = { navController.navigate(QuestionnaireRoute)},
                    onPopUpBackStack = { navController.popBackStack() })
            }

            // Eco
            composable<EcoHubRoute> {
                EcoHubScreen(
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