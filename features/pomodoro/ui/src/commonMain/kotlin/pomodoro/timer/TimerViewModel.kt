package pomodoro.timer

import architecture.LazyInitViewModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class TimerViewModel: LazyInitViewModel<TimerState>(TimerState()) {
    override fun initialize() {
        val timer = flow {
            repeat(100) {
                emit(it.toLong())
                delay(1000)
            }
        }
        coroutineScope.launch {
            timer.collect {
                updateState { copy(millisLeft = it) }
            }
        }
    }
}