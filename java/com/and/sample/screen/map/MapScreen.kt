package com.and.sample.screen.map

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.Satellite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.and.sample.CustomHalfSheet
import com.and.sample.R
import com.and.sample.SetupSnackBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MapScreen(
    onSearch: () -> Unit,
    navBackStackEntry: NavBackStackEntry,
//    viewModel: MapViewModel = hiltViewModel()
) {
    Scaffold(
        content = { contentPadding ->
            val onBackPressedDispatcher =
                LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
            var backPressHandled by remember { mutableStateOf(false) }
            val coroutineScope2 = rememberCoroutineScope()
//            BackHandler(enabled = !backPressHandled) {
            BackHandler(enabled = true) {
                println("asdf back pressed")
//                backPressHandled = false
//                coroutineScope2.launch {
//                    awaitFrame()
//                    onBackPressedDispatcher?.onBackPressed()
//                    backPressHandled = false
//                }
            }

            var showBottomSheet by remember { mutableStateOf(false) }
            val sheetState =
                rememberModalBottomSheetState(
                    skipPartiallyExpanded = false,
                )

            navBackStackEntry.savedStateHandle.get<String>("key")?.let {
                println("asdf back $it")
                if (it.isEmpty().not()) {
                    println("asdf hello again")
                    showBottomSheet = true
                    navBackStackEntry.savedStateHandle.set("key", "")
                }
            }
            val showDialog = remember { mutableStateOf(false) }
            if (showDialog.value) {
                CustomHalfSheet(
                    name = "ここに家形を遷移しますか？",
                    showDialog = showDialog.value,
                    onDismiss = { showDialog.value = false },
                )
            }

            val snackState = remember { SnackbarHostState() }
            val coroutineScope = rememberCoroutineScope()
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(contentPadding),
            ) {
                Image(
                    modifier =
                        Modifier
                            .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.map),
                    contentDescription = stringResource(id = R.string.contentDescription),
                )
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier =
                            Modifier
                                .align(Alignment.BottomEnd),
                        horizontalAlignment = Alignment.End,
                    ) {
                        Card(
                            modifier =
                                Modifier
                                    .clip(
                                        RoundedCornerShape(
                                            topStartPercent = 15,
                                            bottomStartPercent = 15,
                                        ),
                                    ).clickable { },
                            colors =
                                CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background,
                                ),
                        ) {
                            Image(
                                modifier =
                                    Modifier
                                        .padding(end = 8.dp)
                                        .width(54.dp)
                                        .height(54.dp)
                                        .padding(
                                            6.dp,
                                        ),
                                alpha = 0.7f,
                                imageVector = Icons.Filled.Satellite,
                                contentDescription = stringResource(id = R.string.contentDescription),
                            )
                        }
                        Spacer(modifier = Modifier.padding(4.dp))
                        Card(
                            modifier =
                                Modifier
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(50))
                                    .clickable {
                                    },
                            colors =
                                CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background,
                                ),
                        ) {
                            Image(
                                modifier =
                                    Modifier
                                        .width(54.dp)
                                        .height(54.dp)
                                        .padding(6.dp),
                                alpha = 0.7f,
                                imageVector = Icons.Filled.RadioButtonChecked,
                                contentDescription = stringResource(id = R.string.contentDescription),
                            )
                        }
                        Spacer(modifier = Modifier.padding(1.dp))
                        TextButton(
                            onClick = { },
                        ) {
                            Text("ON/OFF")
                        }
                    }
                }

                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                ) {
                    Card(
                        modifier =
                            Modifier
                                .align(Alignment.TopStart)
                                .clip(RoundedCornerShape(50))
                                .clickable {
                                    showDialog.value = true
                                },
                        colors =
                            CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                            ),
                    ) {
                        Image(
                            modifier =
                                Modifier
                                    .width(54.dp)
                                    .height(54.dp)
                                    .padding(6.dp),
                            alpha = 0.7f,
                            imageVector = Icons.Filled.Menu,
                            contentDescription = stringResource(id = R.string.contentDescription),
                        )
                    }

                    Column(
                        modifier =
                            Modifier
                                .align(Alignment.TopEnd),
                    ) {
                        Card(
                            modifier =
                                Modifier
                                    .clip(RoundedCornerShape(50))
                                    .clickable {
//                                    coroutineScope.launch {
//                                        snackState.showSnackbar(
//                                            "該当する家形または配置位置をタップしてください",
//                                            withDismissAction = true
//                                        )
//                                    }
                                        onSearch()
                                    },
                            colors =
                                CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background,
                                ),
                        ) {
                            Image(
                                modifier =
                                    Modifier
                                        .width(54.dp)
                                        .height(54.dp)
                                        .padding(6.dp),
                                alpha = 0.7f,
                                imageVector = Icons.Filled.Search,
                                contentDescription = stringResource(id = R.string.contentDescription),
                            )
                        }
                        Spacer(modifier = Modifier.padding(4.dp))
                        Card(
                            modifier =
                                Modifier
                                    .clip(RoundedCornerShape(50))
                                    .clickable {
                                        coroutineScope.launch {
                                            snackState.showSnackbar("該当する家形または配置位置をタップしてください")
                                        }
                                    },
                            colors =
                                CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background,
                                ),
                        ) {
                            Image(
                                modifier =
                                    Modifier
                                        .width(54.dp)
                                        .height(54.dp)
                                        .padding(6.dp),
                                alpha = 0.7f,
                                imageVector = Icons.Filled.Layers,
                                contentDescription = stringResource(id = R.string.contentDescription),
                            )
                        }
                        Spacer(modifier = Modifier.padding(4.dp))
                        Card(
                            modifier =
                                Modifier
                                    .clip(RoundedCornerShape(50))
                                    .clickable { },
                            colors =
                                CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background,
                                ),
                        ) {
                            Image(
                                modifier =
                                    Modifier
                                        .width(54.dp)
                                        .height(54.dp)
                                        .padding(6.dp),
                                alpha = 0.7f,
                                imageVector = Icons.Filled.BookmarkBorder,
                                contentDescription = stringResource(id = R.string.contentDescription),
                            )
                        }
                    }
                }
                SetupSnackBar(snackState)

                if (showBottomSheet) {
                    ModalBottomSheet(
                        modifier =
                            Modifier
                                .fillMaxHeight(),
                        sheetState = sheetState,
                        scrimColor = Color.Transparent,
                        onDismissRequest = {
                            showBottomSheet = false
                        },
                    ) {
                        Text(
                            "Swipe up to open sheet. Swipe down to dismiss.",
                            modifier = Modifier.padding(16.dp),
                        )
                    }
                }
            }
        },
    )
}
