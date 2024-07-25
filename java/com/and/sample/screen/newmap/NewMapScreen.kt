package com.and.sample.screen.newmap

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.Satellite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.and.sample.CustomHalfSheet
import com.and.sample.R
import com.and.sample.SetupSnackBar
import com.and.sample.screen.search.SearchScreen2
import com.and.sample.ui.common.core.SearchTopAppBar
import com.and.sample.ui.common.core.SearchTopAppBar2
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(
    ExperimentalMaterial3Api::class,
)
@Composable
fun NewMapScreen(
    modifier: Modifier = Modifier,
    viewModel: NewMapViewModel = hiltViewModel(),
) {
    val configuration = LocalConfiguration.current
    val halfHeight = configuration.screenHeightDp / 2
    val miniHeight = (configuration.screenHeightDp * 0.25).toInt()
    var updateHeight by rememberSaveable { mutableIntStateOf(halfHeight) }
    var startCount by rememberSaveable { mutableStateOf(false) }
    var previousState by rememberSaveable { mutableStateOf(SheetValue.Hidden) }

    val bottomSheetState =
        SheetState(
            density = LocalDensity.current,
            skipPartiallyExpanded = false,
        )

    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)

    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    var backPressHandled by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val uiState: MapScreenUiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackHandler(enabled = !backPressHandled) {
        viewModel.onBack()
    }

    LaunchedEffect(bottomSheetState) {
        snapshotFlow { bottomSheetState.currentValue }.collect {
            previousState = it
            if (startCount && it == SheetValue.Hidden) {
                println("asdf a")
                viewModel.showListButton()
//                showListButton = true
                viewModel.showBottomSheet(false)
            }
        }
    }

    LaunchedEffect(bottomSheetState) {
        snapshotFlow { bottomSheetState.targetValue }.collect {
            if (startCount && it == SheetValue.Hidden) {
                println("asdf b")
                updateHeight = miniHeight
            } else if (startCount && it == SheetValue.Expanded) {
                println("asdf c")
                if (previousState == SheetValue.PartiallyExpanded) {// added this for smooth execution
                    println("asdf d")
                    updateHeight = halfHeight
                }
//                or minniheirht
//                updateHeight = miniHeight
            }
        }
    }

    /*
    BottomSheetScaffold has no navigation bar padding in edgeToEdge() mode
    https://issuetracker.google.com/issues/315695275?pli=1
     */
    Scaffold(
        modifier =
        modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.gray))
            .statusBarsPadding()
            .navigationBarsPadding()
    ) { paddingValues ->
        val snackState = remember { SnackbarHostState() }
        println("debug uiState asdf $uiState")

        uiState.userMessage?.let { userMessage ->
            LaunchedEffect(scaffoldState, viewModel, userMessage) {
                scope.launch {
//                    snackState.showSnackbar("該当する家形または配置位置をタップしてください")
                    snackState.showSnackbar(userMessage)
                }
                viewModel.snackbarMessageShown()
            }
        }
        BottomSheetScaffold(
            modifier =
            modifier
                .fillMaxSize()
                .padding(paddingValues)
//                .windowInsetsPadding(TopAppBarDefaults.windowInsets)
//                    clip after padding so we don't show the title over the inset area
//                .clipToBounds()
            ,
            scaffoldState = scaffoldState,
            sheetShape = MaterialTheme.shapes.medium,
            sheetPeekHeight = updateHeight.dp,
            sheetDragHandle = null,
            sheetContainerColor = MaterialTheme.colorScheme.background,
            sheetContent = {
                SheetContent(
                    modifier = modifier,
                    onBack = {
                        onBackPressedDispatcher?.onBackPressed()
                        scope.launch {
                            bottomSheetState.hide()
                        }
                    },
                    mapContent = uiState.content,
                    onShowContent = viewModel::showContent,
                    showSheet = {
                        scope.launch {
                            bottomSheetState.partialExpand()
                        }
                        startCount = true
                    },
                    onDragIconClick = {
                        scope.launch {
                            startCount = false
                            bottomSheetState.expand()
                            startCount = true
                        }
                    },
                    onDetailClick = {
                        viewModel.showContent(MapContent.Detail())
                    }
                )
            },
            content = {
                MapContent(
                    modifier = modifier,
                    mapContent = uiState.content,
                    onShowContent = viewModel::showContent,
                    onShowListButton = {
                        viewModel.showBottomSheet(true)
//                        viewModel.showBottomSheet()
                        viewModel.showListButton(it)
//                        showListButton = it

                    },
                    onContentConsumed = viewModel::contentConsumed,
                    onBack = viewModel::onBack,
                )
                uiState.navigationState?.let {
                    if (it == NavigationState.onBack) {
                        backPressHandled = true
                        scope.launch {
                            awaitFrame()
                            onBackPressedDispatcher?.onBackPressed()
                            backPressHandled = false
                        }
                        viewModel.navigationStateConsumed()
                    }
                }

                uiState.content?.showBottomSheetState?.let {
                    when (it) {
                        true -> {
                            if (!bottomSheetState.isVisible) {
                                scope.launch {
                                    bottomSheetState.partialExpand()
                                }
                            }
                        }

                        else -> {
                            if (bottomSheetState.isVisible) {
                                scope.launch {
                                    bottomSheetState.hide()
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun SearchList(modifier: Modifier, onClick: () -> Unit) {
    Box(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier =
            modifier
                .align(Alignment.TopStart)
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 8.dp,
                ),
        ) {
            Text(
                text = "Aマンション",
                modifier =
                modifier
                    .padding(vertical = 4.dp),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = "〒012-0011　東京都新宿区3-5-4",
                modifier = modifier,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
            )
        }
        Row(
            modifier =
            modifier
                .wrapContentSize()
                .padding(start = 8.dp, end = 18.dp)
                .align(Alignment.CenterEnd),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Card(
                modifier =
                modifier
                    .wrapContentSize()
                    .padding(start = 8.dp, end = 18.dp)
                    .clip(RoundedCornerShape(50)),
                colors =
                CardDefaults.cardColors(
                    containerColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(50),
                border = BorderStroke(1.dp, Color.Gray),
            ) {
                Text(
                    "2件",
                    modifier =
                    modifier.padding(
                        horizontal = 12.dp,
                        vertical = 2.dp,
                    ),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            IconButton(
                modifier =
                modifier
                    .padding(start = 4.dp),
                onClick = {
                    // onBackPressedDispatcher?.onBackPressed()
                },
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.NavigateNext,
                    modifier =
                    modifier
                        .width(54.dp)
                        .height(54.dp)
                        .padding(6.dp),
                    contentDescription = stringResource(id = R.string.contentDescription),
                    tint = Color.Gray,
                )
            }
        }
        HorizontalDivider(
            modifier =
            modifier
                .padding(start = 16.dp, end = 16.dp)
                .align(Alignment.BottomCenter),
        )
    }
}