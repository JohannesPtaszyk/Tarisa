package navigation.tabs

import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import design.theme.AppIcons

internal object SettingsTab : Tab {

    @Composable
    override fun Content() {
        Text("Hello, Settings!")
    }

    override val options: TabOptions
        @Composable
        get() = TabOptions(1u, "Home", rememberVectorPainter(AppIcons.Settings))
}