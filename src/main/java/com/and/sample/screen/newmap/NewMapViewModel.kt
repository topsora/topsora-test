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
    val navigation: NavigationDetails? = null,
)

sealed interface NavigationDetails {
    data object NavigateSearchScreen : NavigationDetails
    data object NavigateSearchListScreen : NavigationDetails
    //    have another one
    data object NavigateSearchDetailsScreen : NavigationDetails
}

@HiltViewModel
class NewMapViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

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

    fun showContent(navigationDetails:NavigationDetails) {
        _uiState.update {
            it.copy(navigation = navigationDetails)
        }
    }
    fun navigationConsumed() {
        _uiState.update {
            it.copy(navigation = null)
        }
    }
}