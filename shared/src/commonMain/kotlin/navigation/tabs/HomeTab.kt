package navigation.tabs

import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import design.theme.AppIcons
import pomodoro.timer.TimerScreen
import pomodoro.timer.TimerViewModel

internal object HomeTab : Tab {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { TimerViewModel() }
        TimerScreen(viewModel)
    }

    override val options: TabOptions
        @Composable
        get() = TabOptions(0u, "Home", rememberVectorPainter(AppIcons.Home))
}