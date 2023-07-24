package navigation.tabs

import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import design.AppIcons

internal object HomeTab : Tab {

    @Composable
    override fun Content() {
        Text("Hello, Home!")
    }

    override val options: TabOptions
        @Composable
        get() = TabOptions(0u, "Home", rememberVectorPainter(AppIcons.Home))
}