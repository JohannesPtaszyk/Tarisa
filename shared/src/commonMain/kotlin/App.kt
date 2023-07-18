import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import design.AppTheme
import navigation.BottomBarItem
import navigation.DrawerItem
import navigation.RailItem
import navigation.tabs.HomeTab
import navigation.tabs.SettingsTab

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class
)
@Composable
fun App() {
    AppTheme {
        val navigationType by rememberNavigationType()
        val tabs = remember { listOf(HomeTab, SettingsTab) }
        TabNavigator(HomeTab) {
            Scaffold(bottomBar = {
                AnimatedVisibility(
                    navigationType == NavigationType.BOTTOM,
                    enter = slideInVertically { it } + fadeIn(),
                    exit = slideOutVertically { it } + fadeOut()
                ) {
                    BottomAppBar {
                        tabs.forEach { BottomBarItem(it) }
                    }
                }
            }) {
                PermanentNavigationDrawer(drawerContent = {
                    AnimatedContent(navigationType) {
                        when (it) {
                            NavigationType.DRAWER -> {
                                PermanentDrawerSheet(
                                    modifier = Modifier.padding(
                                        horizontal = 12.dp,
                                        vertical = 16.dp
                                    )
                                ) {
                                    tabs.forEach { DrawerItem(it) }
                                }
                            }

                            NavigationType.RAIL -> {
                                NavigationRail(
                                    modifier = Modifier.padding(
                                        horizontal = 12.dp,
                                        vertical = 16.dp
                                    )
                                ) {
                                    tabs.forEach { RailItem(it) }
                                }
                            }

                            NavigationType.BOTTOM -> {
                                // No content as we show bottom bar in this case
                            }
                        }
                    }
                }, content = { CurrentTab() })
            }
        }

    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
private fun rememberNavigationType(): State<NavigationType> {
    val windowSizeClass = calculateWindowSizeClass()
    return remember(windowSizeClass) {
        derivedStateOf {
            when {
                windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded -> {
                    NavigationType.DRAWER
                }

                windowSizeClass.widthSizeClass == WindowWidthSizeClass.Medium || windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact -> {
                    NavigationType.RAIL
                }

                windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact -> {
                    NavigationType.BOTTOM
                }

                else -> {
                    NavigationType.RAIL
                }
            }
        }
    }
}

enum class NavigationType {
    DRAWER, RAIL, BOTTOM
}