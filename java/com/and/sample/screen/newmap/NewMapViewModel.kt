package com.and.sample.screen.newmap

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
data class MapScreenUiState(
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val userMessage: String? = null,
    val page: ArrayList<MapContent>? = null,
    val content: MapContent? = null,
    val navigationState: NavigationState? = null,
)

sealed interface MapContent {
    val showSearchTopBar: Boolean
    val showMapOptionMenuButton: Boolean
    var showBottomSheetState: Boolean
    var showListButton: Boolean


    data class Map(
        override val showSearchTopBar: Boolean = false,
        override val showMapOptionMenuButton: Boolean = true,
        override var showBottomSheetState: Boolean = false,
        override var showListButton: Boolean = false,
    ) : MapContent

    data class Search(
        override val showSearchTopBar: Boolean = true,
        override val showMapOptionMenuButton: Boolean = false,
        override var showBottomSheetState: Boolean = false,
        override var showListButton: Boolean = false,
    ) : MapContent

    data class SearchResultList(
        override val showSearchTopBar: Boolean = true,
        override val showMapOptionMenuButton: Boolean = false,
        override var showBottomSheetState: Boolean = true,
        override var showListButton: Boolean = false,
    ) : MapContent

    data class SelectOneFromGroup(
        override val showSearchTopBar: Boolean = true,
        override val showMapOptionMenuButton: Boolean = false,
        override var showBottomSheetState: Boolean = true,
        override var showListButton: Boolean = false,
    ) : MapContent

    data class Detail(
        override val showSearchTopBar: Boolean = false,
        override val showMapOptionMenuButton: Boolean = true,
        override var showBottomSheetState: Boolean = true,
        override var showListButton: Boolean = false,
        var detailTab: DetailTab = DetailTab.Unselected
    ) : MapContent
}

sealed interface DetailTab {
    data object Unselected : DetailTab
    data object Basic : DetailTab
    data object Additional : DetailTab
}

sealed interface NavigationState {
    data object onBack : NavigationState
}

data class MapAddOnState(
    val showSearchTopBar: Boolean = false,
    val showMapOptionMenuButton: Boolean = false,
)

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
    private val _uiState = MutableStateFlow(MapScreenUiState())
    val uiState: StateFlow<MapScreenUiState> = _uiState.asStateFlow()

    // just added map state for
    init {
        val list: ArrayList<MapContent> = ArrayList()
        list.add(MapContent.Map())
        _uiState.update {
            it.copy(
                page = list,
                content = MapContent.Map()

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

    fun showBottomSheet(show: Boolean = true) {
        _uiState.update {
            val content = it.content
            content?.showBottomSheetState = show
            it.copy(content = content)
        }
    }

    fun showListButton(show: Boolean = true) {
        _uiState.update {
            val content = it.content
            content?.showListButton = show
            it.copy(content = content)
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

    fun navigationStateConsumed() {
        _uiState.update {
            it.copy(navigationState = null)
        }
    }

    fun onBack() {
        _uiState.value.page?.let { page ->
            if (page.size <= 1) {
                _uiState.update { model ->
                    model.copy(
                        page = null,
                        content = null,
                        navigationState = NavigationState.onBack
                    )
                }
            } else {
                val previousPage = page[page.lastIndex - 1]
                page.removeLast()
                when (previousPage) {
                    is MapContent.Map -> {
                        _uiState.update {
                            it.copy(
                                content = previousPage,
                            )
                        }
                    }

                    is MapContent.Search -> {
                        _uiState.update {
                            it.copy(
                                content = previousPage,
                            )
                        }
                    }

                    is MapContent.SearchResultList -> {
                        _uiState.update {
                            it.copy(
                                content = previousPage,
                            )
                        }
                    }

                    is MapContent.SelectOneFromGroup -> {
                        _uiState.update {
                            it.copy(
                                content = previousPage,
                            )
                        }
                    }

                    is MapContent.Detail -> {
                        println("asdf aaa $previousPage")
                        _uiState.update {
                            it.copy(
                                content = previousPage,
                            )
                        }
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
