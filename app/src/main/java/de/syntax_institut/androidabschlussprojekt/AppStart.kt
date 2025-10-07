package de.syntax_institut.androidabschlussprojekt

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backpack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Quiz
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
import de.syntax_institut.androidabschlussprojekt.ui.explore.ExploreScreen
import de.syntax_institut.androidabschlussprojekt.ui.home.HomeScreen
import de.syntax_institut.androidabschlussprojekt.ui.milestone.MilestoneScreen
import de.syntax_institut.androidabschlussprojekt.ui.profile.ProfileScreen
import de.syntax_institut.androidabschlussprojekt.ui.questionnaire.Questionnaire
import de.syntax_institut.androidabschlussprojekt.ui.species.IdentifySpecies
import de.syntax_institut.androidabschlussprojekt.ui.species.SpeciesCollection
import de.syntax_institut.androidabschlussprojekt.ui.tips.TipsScreen
import kotlinx.serialization.Serializable


@Serializable
object HomeRoute

@Serializable
object QuestionnaireRoute

@Serializable
object ExploreRoute

@Serializable
object SpeciesRoute

@Serializable
object SpeciesCollectionRoute

@Serializable
object TipsRoute

@Serializable
object ProfileRoute

@Serializable
object MilestoneRoute

enum class TabItem(
    val route: Any,
    val tabTitle: String,
    val tabIcon: ImageVector
) {
    HOME(HomeRoute, "Home", Icons.Default.Backpack),
    Quiz(QuestionnaireRoute, "Quiz", Icons.Default.Quiz),
    WORLDMAP(ExploreRoute, "World", Icons.Default.Map),
    SPECIES(SpeciesRoute, "Species", Icons.Default.CameraAlt),
}


@Composable
fun AppStart(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    var selectedTab by rememberSaveable { mutableStateOf(TabItem.HOME) }


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
                HomeScreen()
            }
            composable<QuestionnaireRoute> {
                Questionnaire()
            }
            composable<ExploreRoute> {
                ExploreScreen()
            }
            composable<TipsRoute> {
                TipsScreen()
            }
            composable<ProfileRoute> {
                ProfileScreen()
            }
            composable<MilestoneRoute> {
                MilestoneScreen()
            }
            composable<SpeciesRoute> {
                IdentifySpecies(
                    onNavigateToCollection = { navController.navigate(SpeciesCollection()) }
                )
            }

            composable<SpeciesCollectionRoute> {
                SpeciesCollection()
            }
        }
    }
    // SpeciesScreen()
    // Questionnaire()
}


@Preview(showBackground = true)
@Composable
fun AppStartPreview() {
    AppStart()
}