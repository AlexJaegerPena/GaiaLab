package de.syntax_institut.androidabschlussprojekt

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Recycling
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import de.syntax_institut.androidabschlussprojekt.ui.ecoHub.EcoHubScreen
import de.syntax_institut.androidabschlussprojekt.ui.ecoHub.ecoTips.EcoTipsScreen
import de.syntax_institut.androidabschlussprojekt.ui.ecoHub.ecoFacts.EcoFactsScreen
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.speciesIdent.IdentifySpeciesScreen
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.speciesIdent.SpeciesCollectionScreen
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.speciesFacts.SpeciesFactsScreen
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
    HOME(HomeRoute, "Home", Icons.Default.Home),
    SPECIESLAB(SpeciesLabRoute, "Species Lab", Icons.Default.Science),
    CLIMATEZONE(ClimateZoneRoute, "Climate Zone", Icons.Default.Thermostat),
    ECOHUB(EcoHubRoute, "Eco Hub", Icons.Default.Recycling),
}


@Composable
fun AppStart(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    var selectedTab by rememberSaveable { mutableStateOf(TabItem.SPECIESLAB) }


    Scaffold(
        containerColor = Color.Transparent,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = { /* ... */ }, // TODO: Top Bar?

        bottomBar = {
            NavigationBar(
                containerColor = Color.Transparent,
                contentColor = Color.Transparent,
                tonalElevation = 5.dp
            ) {
                TabItem.entries.forEach { tab ->
                    NavigationBarItem(
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab },
                        icon = { Icon(
                            imageVector = tab.tabIcon,
                            contentDescription = "TabItem"
                        )
                        },
                        label = { Text(tab.tabTitle) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Black,
                            unselectedIconColor = Color.Black,
                            selectedTextColor = Color.Black,
                            unselectedTextColor = Color.Black
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = selectedTab.route,
            modifier = Modifier.padding(innerPadding)
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

            /*
           composable<ProfileRoute> {
               ProfileScreen()
           }
           composable<MilestoneRoute> {
               MilestoneScreen()
           }
            */
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppStartPreview() {
    AppStart()
}