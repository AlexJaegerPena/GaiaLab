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
import de.syntax_institut.androidabschlussprojekt.ui.climateLab.ClimateFactsScreen
import de.syntax_institut.androidabschlussprojekt.ui.climateLab.ClimateTipsScreen
import de.syntax_institut.androidabschlussprojekt.ui.climateLab.co2quiz.CO2QuizResultScreen
import de.syntax_institut.androidabschlussprojekt.ui.climateLab.co2quiz.CO2QuizScreen
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.SpeciesLabScreen
import de.syntax_institut.androidabschlussprojekt.ui.common.bottomBar.GlassmorphicBottomBar
import de.syntax_institut.androidabschlussprojekt.ui.ecoLab.EcoLabScreen
import de.syntax_institut.androidabschlussprojekt.ui.ecoLab.EcoTipsScreen
import de.syntax_institut.androidabschlussprojekt.ui.ecoLab.EcoFactsScreen
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.identification.CollectionIdentifyScreen
import de.syntax_institut.androidabschlussprojekt.ui.speciesLab.SpeciesFactsScreen
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.favorites.FavFactScreen
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.ProfileScreen
import de.syntax_institut.androidabschlussprojekt.ui.userProfile.favorites.FavTipsScreen
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
object ProfileRoute

@Serializable
object FavFactsRoute

@Serializable
object FavTipsRoute

// Species
@Serializable
object SpeciesLabRoute

@Serializable
object SpeciesFactsRoute

@Serializable
object SpeciesIdentRoute

// Climate
@Serializable
object ClimateLabRoute

@Serializable
object ClimateFactsRoute

@Serializable
object ClimateTipsRoute

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
fun AppStart(
    modifier: Modifier = Modifier,
) {
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
                    onNavigateToSpeciesLab = { navController.navigate(SpeciesLabRoute) },
                    onNavigateToClimateZone = { navController.navigate(ClimateLabRoute) },
                    onNavigateToEcoHub = { navController.navigate(EcoLabRoute) },
                    onNavigateToProfile = { navController.navigate(ProfileRoute) }
                )
            }
            composable<ProfileRoute> {
                ProfileScreen(
                    onPopUpBackStack = { navController.popBackStack() },
                    onShowCO2Result = { navController.navigate(CO2QuizResultRoute) },
                    onShowSpecies = { navController.navigate(SpeciesIdentRoute) },
                    onShowFavFacts = { navController.navigate(FavFactsRoute) },
                    onShowFavTips = { },
                   // authVM = authVM,
                    // userVM = userVM
                )
            }
            composable<FavFactsRoute> {
                FavFactScreen(
                    onPopUpBackStack = { navController.popBackStack() },
                    category = "all"
                )
            }
            composable<FavTipsRoute> {
                FavTipsScreen(
                    onPopUpBackStack = { navController.popBackStack() },
                    category = "all"
                )
            }



            // ----- Species -----
            composable<SpeciesLabRoute> {
                SpeciesLabScreen(
                    onNavigateToFacts = { navController.navigate(SpeciesFactsRoute) },
                    onNavigateToIdentSpecies = { navController.navigate(SpeciesIdentRoute) }
                )
            }
            composable<SpeciesFactsRoute> {
                SpeciesFactsScreen(
                    onPopUpBackStack = { navController.popBackStack() },
                    category = "species"
                )
            }
            composable<SpeciesIdentRoute> {
                CollectionIdentifyScreen(onPopUpBackStack = { navController.popBackStack() })
            }

            // ----- Climate -----
            composable<ClimateLabRoute> {
                ClimateLabScreen(
                    onNavigateToFacts = { navController.navigate(ClimateFactsRoute) },
                    onNavigateToCO2Quiz = { navController.navigate(CO2QuizRoute) }
                )
            }
            composable<ClimateFactsRoute> {
                ClimateFactsScreen(
                    onPopUpBackStack = { navController.popBackStack() },
                    category = "climate"
                )
            }
            composable<ClimateTipsRoute> {
                ClimateTipsScreen(
                    onPopUpBackStack = { navController.popBackStack() },
                    category = "climate"
                )
            }
            composable<CO2QuizRoute> {
                CO2QuizScreen(
                    onNavigateToTips = { navController.navigate(ClimateTipsRoute) },
                    onPopupBackStack = {  navController.popBackStack() },
                    onSecondButtonClick = { navController.navigate(CO2QuizResultRoute) }
                )
            }
            composable<CO2QuizResultRoute> {
                CO2QuizResultScreen(
                    onNavigateToTips = { navController.navigate(ClimateTipsRoute) },
                    onPopupBackStack = {  navController.popBackStack() },
                    onSecondButtonClick = { navController.navigate(CO2QuizRoute) }
                )
            }

            // ----- Eco -----
            composable<EcoLabRoute> {
                EcoLabScreen(
                    onNavigateToFacts = { navController.navigate(EcoFactsRoute) },
                    onNavigateToTips = { navController.navigate(EcoTipsRoute) }
                )
            }
            composable<EcoFactsRoute> {
                EcoFactsScreen(
                    onPopUpBackStack = { navController.popBackStack() },
                    category = "eco"
                )
            }
            composable<EcoTipsRoute> {
                EcoTipsScreen(
                    onPopUpBackStack = { navController.popBackStack() },
                    category = "eco"
                )
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