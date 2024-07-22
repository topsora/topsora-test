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
data class AddEditTaskUiState(
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val userMessage: String? = null,
    val content: MapContent? = null,
)

sealed interface MapContent {
    data object Map : MapContent
    data object Search : MapContent

    data object SearchResultList : MapContent

    data object SelectOneFromGroup : MapContent
    data object Detail : MapContent
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
            _uiState.update {
                it.copy(content = mapContent)
            }
        }

        fun navigationConsumed() {
            _uiState.update {
                it.copy(content = null)
            }
        }
    }
