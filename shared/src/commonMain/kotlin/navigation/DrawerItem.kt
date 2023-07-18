package navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    NavigationDrawerItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            tab.options.icon?.let { iconPainter ->
                Icon(painter = iconPainter, contentDescription = tab.options.title)
            }
        },
        label = {
            Text(tab.options.title)
        },
    )
}