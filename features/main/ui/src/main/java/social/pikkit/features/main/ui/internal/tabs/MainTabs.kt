package social.pikkit.features.main.ui.internal.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import social.pikkit.core.ui.PikkitDrawables
import social.pikkit.core.ui.PikkitUI
import social.pikkit.core.ui.navigation.Navigation
import social.pikkit.features.challenges.ui.ChallengesUI
import social.pikkit.features.groups.ui.GroupsUI
import social.pikkit.features.ranking.ui.RankingUI
import social.pikkit.features.settings.ui.SettingsUI

private object Tabs {

    @Serializable
    data object Challenges

    @Serializable
    data object Groups

    @Serializable
    data object Ranking

    @Serializable
    data object Settings
}

@Composable
fun MainTabs(
    onStartChallenge: () -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            Tabs(
                navController = navController,
                tabs = listOf(Tabs.Challenges, Tabs.Groups, Tabs.Ranking, Tabs.Settings)
            )
        }
    ) { padding ->
        PikkitUI.Navigation(
            navController = navController,
            startDestination = Tabs.Challenges,
            modifier = PaddingValues(
                start = padding.calculateStartPadding(LocalLayoutDirection.current),
                end = padding.calculateEndPadding(LocalLayoutDirection.current),
                bottom = padding.calculateBottomPadding(),
            ).let {
                Modifier
                    .padding(it)
                    .consumeWindowInsets(it)
            }
        ) {
            composable<Tabs.Challenges> {
                ChallengesUI.Screen(
                    onStart = onStartChallenge
                )
            }

            composable<Tabs.Groups> {
                GroupsUI.Screen()
            }

            composable<Tabs.Ranking> {
                RankingUI.Screen()
            }

            composable<Tabs.Settings> {
                SettingsUI.Screen()
            }
        }
    }
}

@Composable
private fun Tabs(
    navController: NavController,
    tabs: List<Any>
) {
    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainer
                )
                .clip(RectangleShape)
                .windowInsetsPadding(NavigationBarDefaults.windowInsets)
                .sizeIn(minHeight = 60.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            tabs.forEach { tab ->
                val selected = currentDestination
                    ?.hierarchy
                    ?.any { it.hasRoute(tab::class) } == true

                Icon(
                    modifier = Modifier
                        .size(48.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(bounded = false)
                        ) {
                            navController.navigate(tab) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                        .padding(12.dp),
                    painter = when (tab) {
                        Tabs.Challenges -> painterResource(PikkitDrawables.icon_pikkit)
                        Tabs.Groups -> painterResource(PikkitDrawables.icon_groups)
                        Tabs.Ranking -> painterResource(PikkitDrawables.icon_ranking)
                        else -> painterResource(PikkitDrawables.icon_settings)
                    },
                    tint = when {
                        selected -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
                    },
                    contentDescription = null // todo: add content description
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black.copy(alpha = 0.08f))
                .height(0.5.dp)
        )
    }
}