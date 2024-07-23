

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
isLoading
isEmpty
error
content
    screen
message
navigation
*/
data class AddEditTaskUiState(
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val userMessage: String? = null,
    val page: ArrayList<MapContent>? = null,
    val content: MapContent? = null,
    val bottomSheetState: MapBottomSheetState? = null,
    val navigationState: NavigationState? = null,
)

sealed interface MapContent {
    data object Map : MapContent
    data object Search : MapContent
    data object SearchResultList : MapContent
    data object SelectOneFromGroup : MapContent
    data object Detail : MapContent
}

sealed interface MapBottomSheetState {
    data object show : MapBottomSheetState
    data object hide : MapBottomSheetState
}

sealed interface NavigationState {
    data object onBack : NavigationState
}

sealed interface MapMenuState {
    data object show : MapMenuState
    data object hide : MapMenuState
}

/*
   don't forget to use for network call
   viewModelScope.launch {

   }
 */

@HiltViewModel
class NewMapViewModel
@Inject
constructor(
    /*
     * need to add for performance
     * */
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddEditTaskUiState())
    val uiState: StateFlow<AddEditTaskUiState> = _uiState.asStateFlow()

    // just added map state for
    init {
        val list: ArrayList<MapContent> = ArrayList()
        list.add(MapContent.Map)
        _uiState.update {
            it.copy(
                page = list,
                content = MapContent.Map

            )
        }
    }

    fun snackbarMessageShown() {
        _uiState.update {
            it.copy(userMessage = null)
        }
    }

    fun setSnackbarMessage(message: String) {
        _uiState.update {
            it.copy(userMessage = message)
        }
    }

    fun showContent(mapContent: MapContent) {
        val list: ArrayList<MapContent> = ArrayList()
        _uiState.value.page?.forEach {
            list.add(it)
        }
        list.add(mapContent)
        _uiState.update {
            it.copy(page = list, content = mapContent)
        }
    }

    fun contentConsumed() {
        _uiState.update {
            it.copy(content = null)
        }
    }

    fun bottomSheetConsumed() {
        _uiState.update {
            it.copy(bottomSheetState = null)
        }
    }

    fun navigationStateConsumed() {
        _uiState.update {
            println("asdf 3")
            it.copy(navigationState = null)
        }
    }

    fun onBack() {
        println("asdf size1 ${_uiState.value.page}")
        _uiState.value.page?.let { page ->
            val size = page.size
            println("asdf size $size")
            if (page.size <= 1) {
                _uiState.update { model ->
                    println("asdf 1")
                    model.copy(
                        page = null,
                        content = null,
                        navigationState = NavigationState.onBack
                    )
                }
            } else {
                val previousPage = page[page.lastIndex-1]
                println("asdf zzz $previousPage")
                page.removeLast()
                when (previousPage) {
                    MapContent.Map -> {
                        _uiState.update {
                            it.copy(
                                content = previousPage,
                                bottomSheetState = MapBottomSheetState.hide
                            )
                        }
                    }
                    MapContent.Search -> {
                        _uiState.update {
                            it.copy(
                                content = previousPage,
                                bottomSheetState = MapBottomSheetState.hide
                            )
                        }
                    }

                    MapContent.SearchResultList -> {
                        _uiState.update {
                            it.copy(
                                content = previousPage,
                                bottomSheetState = MapBottomSheetState.show
                            )
                        }
                    }

                    MapContent.SelectOneFromGroup -> {
                        _uiState.update {
                            it.copy(
                                content = previousPage,
                                bottomSheetState = MapBottomSheetState.show
                            )
                        }
                    }
                    MapContent.Detail -> {

                    }
                }
            }
        }
        /*when () {
            MapContent.SelectOneFromGroup -> {
                showContent(MapContent.SearchResultList)
            }

            MapContent.SearchResultList -> {
                showContent(MapContent.Search)
            }

            MapContent.Search -> {
                navigationConsumed()
//                if (bottomSheetState.isVisible) {
//                    scope.launch {
//                        bottomSheetState.partialExpand()
//                    }
//                }
            }

            null -> {

            }

            MapContent.Detail -> {

            }

            MapContent.Map -> {}
        }*/
    }
}
