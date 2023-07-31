package architecture

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class LazyInitViewModel<STATE>(initialState: STATE) : ScreenModel {

    private val mutableState = MutableStateFlow(initialState)
    val state: StateFlow<STATE> by lazy {
        initialize()
        mutableState.asStateFlow()
    }

    abstract fun initialize()

    fun updateState(block: STATE.() -> STATE) {
        mutableState.value = mutableState.value.block()
    }
}