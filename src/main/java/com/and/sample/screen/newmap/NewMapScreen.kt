package com.and.sample.screen.newmap

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.and.sample.CustomHalfSheet
import com.and.sample.R
import com.and.sample.SetupSnackBar
import com.and.sample.screen.search.SearchScreen2
import com.and.sample.ui.common.core.SearchTopAppBar2
import com.and.sample.ui.theme.ChangeStatusBarColor2
import com.and.sample.ui.theme.SampleTheme
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.launch

enum class ExpandedType {
    HALF, FULL, COLLAPSED
}

@OptIn(
    ExperimentalMaterial3Api::class,
)
@Composable
fun NewMapScreen(
    modifier: Modifier = Modifier,
    viewModel: NewMapViewModel = hiltViewModel(),
) {
    /*val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val halfHeight = configuration.screenHeightDp / 2
    val miniHeight = (configuration.screenHeightDp * 0.25).toInt()
    var expandedType by remember {
        mutableStateOf(ExpandedType.COLLAPSED)
    }
    var updateHeight by rememberSaveable { mutableIntStateOf(halfHeight) }
    var previousState by rememberSaveable { mutableStateOf(SheetValue.Hidden) }*/

    val configuration = LocalConfiguration.current
    val halfHeight = configuration.screenHeightDp / 2
    val screenHeight = configuration.screenHeightDp
    val miniHeight = (configuration.screenHeightDp * 0.25).toInt()
    var updateHeight by rememberSaveable { mutableIntStateOf(halfHeight) }
    val pxValue = LocalDensity.current.run { miniHeight.dp.toPx() }
    var startCount by rememberSaveable { mutableStateOf(false) }
    var sheetState by rememberSaveable { mutableStateOf(SheetValue.Hidden) }
    //    2920 - (747/2)

//    val configuration = LocalConfiguration.current
//    val screenHeight = configuration.screenHeightDp
//    var expandedType by remember {
//        mutableStateOf(ExpandedType.COLLAPSED)
//    }
//    val height by animateIntAsState(
//        when (expandedType) {
//            ExpandedType.HALF -> screenHeight / 2
//            ExpandedType.FULL -> screenHeight
//            ExpandedType.COLLAPSED -> 70
//        }
//    )


    val bottomSheetState =
        SheetState(
            skipPartiallyExpanded = false,
            density = LocalDensity.current,
            /*confirmValueChange = {
                println("asdfb aa ${it != SheetValue.Hidden} -> $it")
                sheetState = it

//                if (it == SheetValue.Expanded) {
//                    updateHeight = halfHeight
//                }
                false
                *//*if (it != SheetValue.Hidden) {
                    println("asdfa aa")
                    if (previousState != it) {
                        previousState = it
                        updateHeight = halfHeight
                        println("asdfa aa added")
                    }
                    true
                } else {
                    println("asdfa b")
                    if (previousState != it) {
                        previousState = it
                        updateHeight = miniHeight
                        println("asdfa badded")
                    }
                    true
                }*//*
            }*/
        )

    val scaffoldState: BottomSheetScaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)

    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    var backPressHandled by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val uiState: AddEditTaskUiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackHandler(enabled = !backPressHandled) {
        println("asdf back pressed")
        when (uiState.content) {
            MapContent.SelectOneFromGroup -> {
                viewModel.showContent(MapContent.SearchResultList)
            }

            MapContent.SearchResultList -> {
                viewModel.showContent(MapContent.Search)
//                if (bottomSheetState.isExpanded) {
//                    scope.launch {
//                        bottomSheetState.collapse()
//                    }
//                }
            }

            MapContent.Search -> {
                viewModel.navigationConsumed()
                if (bottomSheetState.isVisible) {
                    scope.launch {
                        bottomSheetState.partialExpand()
                    }
                }
            }

            null -> {
                println("asdf back pressed else")
                backPressHandled = true
                scope.launch {
                    awaitFrame()
                    onBackPressedDispatcher?.onBackPressed()
                    backPressHandled = false
                }
            }

            MapContent.Detail -> {

            }

            MapContent.Map -> {}
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
            .navigationBarsPadding(),
    ) { padding ->

        val snackState = remember { SnackbarHostState() }
        println("asdf uiState $uiState")

        uiState.userMessage?.let { userMessage ->
            LaunchedEffect(scaffoldState, viewModel, userMessage) {
                scope.launch {
//                    snackState.showSnackbar("該当する家形または配置位置をタップしてください")
                    println("asdf a")
                    snackState.showSnackbar(userMessage)
                }
                viewModel.snackbarMessageShown()
            }
        }
        BottomSheetScaffold(
            modifier =
            modifier
                .fillMaxSize()
                .padding(padding),
            scaffoldState = scaffoldState,
            sheetShape = MaterialTheme.shapes.medium,
            sheetPeekHeight = updateHeight.dp,
            sheetDragHandle = null,
            sheetContent = {
                Box(
                    modifier =
                    modifier
                        .fillMaxSize()
                        .padding(padding)
                        .onGloballyPositioned { coordinates ->
                            println("asdf z ${coordinates.size}, ${coordinates.positionInWindow()} - ${screenHeight.dp}, ${halfHeight.dp}, $miniHeight abc $pxValue")
                            val yc = coordinates.positionInWindow().y
//                            if (yc < (coordinates.size.height - (pxValue + (pxValue/2)))) {
//                                println("asdf true $yc ${coordinates.size.height}")
//                            }
                            if (startCount && coordinates.positionInWindow().y > 2500) {
                                println("asdf hello ${coordinates.positionInWindow()}")
                                updateHeight = miniHeight
                            }
//                                2920 - (747/2)
                        },
                ) {
                    SheetContent(
                        modifier = modifier, sheetState = bottomSheetState,
                        onBack = {
                            onBackPressedDispatcher?.onBackPressed()
                            scope.launch {
                                bottomSheetState.hide()
                            }
                        },
                        mapContent = uiState.content,
                        hideSheet = {
                            scope.launch {
                                bottomSheetState.partialExpand()
                                startCount = true
                            }
                        },
                        onDragIconClick = {
                            scope.launch {
                                bottomSheetState.expand()
//                                updateHeight = halfHeight
                            }
                        }
                    )
                }
            },
            content = {
                //            mapcontent
                val showDialog = remember { mutableStateOf(false) }
                if (showDialog.value) {
                    CustomHalfSheet(
                        name = "ここに家形を遷移しますか？",
                        showDialog = showDialog.value,
                        onDismiss = { showDialog.value = false },
                    )
                }

                Box(
                    modifier =
                    modifier
                        .fillMaxSize(),
                ) {
                    Image(
                        modifier =
                        modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.drawable.map),
                        contentDescription = stringResource(id = R.string.contentDescription),
                    )
                    Box(modifier = modifier.fillMaxSize()) {
                        Column(
                            modifier =
                            modifier
                                .align(Alignment.BottomEnd),
                            horizontalAlignment = Alignment.End,
                        ) {
                            Card(
                                modifier =
                                modifier
                                    .clip(
                                        RoundedCornerShape(
                                            topStartPercent = 15,
                                            bottomStartPercent = 15,
                                        ),
                                    )
                                    .clickable { },
                                colors =
                                CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background,
                                ),
                            ) {
                                Image(
                                    modifier =
                                    modifier
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
                            Spacer(modifier = modifier.padding(4.dp))
                            Card(
                                modifier =
                                modifier
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
                                    modifier
                                        .width(54.dp)
                                        .height(54.dp)
                                        .padding(6.dp),
                                    alpha = 0.7f,
                                    imageVector = Icons.Filled.RadioButtonChecked,
                                    contentDescription = stringResource(id = R.string.contentDescription),
                                )
                            }
                            Spacer(modifier = modifier.padding(1.dp))
                            TextButton(
                                onClick = { },
                            ) {
                                Text("ON/OFF")
                            }
                        }
                    }

                    Box(
                        modifier =
                        modifier
                            .fillMaxSize()
                            .padding(8.dp),
                    ) {
                        Card(
                            modifier =
                            modifier
                                .align(Alignment.TopStart)
                                .clip(RoundedCornerShape(50))
                                .clickable {
//                            viewModel.setSnackbarMessage("menu click")
                                },
                            colors =
                            CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                            ),
                        ) {
                            Image(
                                modifier =
                                modifier
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
                            modifier
                                .align(Alignment.TopEnd),
                        ) {
                            Card(
                                modifier =
                                modifier
                                    .clip(RoundedCornerShape(50))
                                    .clickable {
                                        viewModel.showContent(MapContent.Search)
                                        updateHeight = halfHeight
                                    },
                                colors =
                                CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background,
                                ),
                            ) {
                                Image(
                                    modifier =
                                    modifier
                                        .width(54.dp)
                                        .height(54.dp)
                                        .padding(6.dp),
                                    alpha = 0.7f,
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = stringResource(id = R.string.contentDescription),
                                )
                            }
                            Spacer(modifier = modifier.padding(4.dp))
                            Card(
                                modifier =
                                modifier
                                    .clip(RoundedCornerShape(50))
                                    .clickable {
                                        scope.launch {
//                                        snackState.showSnackbar("該当する家形または配置位置をタップしてください")
                                        }
                                    },
                                colors =
                                CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background,
                                ),
                            ) {
                                Image(
                                    modifier =
                                    modifier
                                        .width(54.dp)
                                        .height(54.dp)
                                        .padding(6.dp),
                                    alpha = 0.7f,
                                    imageVector = Icons.Filled.Layers,
                                    contentDescription = stringResource(id = R.string.contentDescription),
                                )
                            }
                            Spacer(modifier = modifier.padding(4.dp))
                            Card(
                                modifier =
                                modifier
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
                                    modifier
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

                    // demo
                    when (uiState.content) {
                        MapContent.SelectOneFromGroup -> {
                            Image(
                                modifier =
                                modifier
                                    .align(Alignment.CenterEnd)
                                    .width(54.dp)
                                    .height(54.dp)
                                    .padding(6.dp)
                                    .clickable {
                                        viewModel.showContent(MapContent.SelectOneFromGroup)
                                    },
                                alpha = 0.7f,
                                imageVector = Icons.Filled.LocationOn,
                                colorFilter = ColorFilter.tint(Color.Red),
                                contentDescription = stringResource(id = R.string.contentDescription),
                            )
                        }

                        MapContent.SearchResultList -> {
                            Image(
                                modifier =
                                modifier
                                    .align(Alignment.CenterEnd)
                                    .width(54.dp)
                                    .height(54.dp)
                                    .padding(6.dp)
                                    .clickable {
                                        viewModel.showContent(MapContent.SelectOneFromGroup)
                                    },
                                alpha = 0.7f,
                                imageVector = Icons.Filled.LocationOn,
                                colorFilter = ColorFilter.tint(Color.Red),
                                contentDescription = stringResource(id = R.string.contentDescription),
                            )
                        }

                        MapContent.Search -> {}
                        null -> {
                        }

                        MapContent.Detail -> {}
                        MapContent.Map -> {}
                    }

                    SetupSnackBar(snackState)
                    Column(
                        modifier = modifier.fillMaxSize(),
                    ) {
                        when (uiState.content) {
                            MapContent.SelectOneFromGroup -> {
                                ChangeStatusBarColor2(Color.Gray.toArgb())
                                SearchTopAppBar2("検索メニュー") {
                                    viewModel.navigationConsumed()
                                    scope.launch {
                                        if (bottomSheetState.isVisible) {
                                            bottomSheetState.partialExpand()
                                        } else {
                                            bottomSheetState.show()
                                        }
                                    }
                                }
                            }

                            MapContent.SearchResultList -> {
                                //snsh
                                ChangeStatusBarColor2(Color.Gray.toArgb())
                                SearchTopAppBar2("検索メニュー") {
                                    viewModel.navigationConsumed()
                                    scope.launch {
                                        bottomSheetState.expand()
                                    }
                                }
                            }

                            MapContent.Search -> {
                                ChangeStatusBarColor2(Color.Gray.toArgb())
                                SearchTopAppBar2("検索メニュー") {
                                    viewModel.navigationConsumed()
                                    scope.launch {
                                        if (bottomSheetState.isVisible) {
                                            bottomSheetState.partialExpand()
                                        } else {
                                            bottomSheetState.show()
                                        }
                                    }
                                }
                                SearchScreen2(
                                    onSearch = {
                                        scope.launch {
                                            if (bottomSheetState.isVisible) {
                                                bottomSheetState.partialExpand()
                                            } else {
                                                bottomSheetState.show()
                                            }
                                        }
                                        viewModel.showContent(MapContent.SearchResultList)
                                        updateHeight = halfHeight
                                    },
                                )
                            }

                            null -> {
                                ChangeStatusBarColor2(Color.White.toArgb())
                            }

                            MapContent.Detail -> TODO()
                            MapContent.Map -> TODO()
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun SearchList(onClick: () -> Unit) {
    Column {
        Card(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .clickable { onClick() },
            colors =
            CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        ) {
            Text(
                text = "佐々木　太郎",
                modifier =
                Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .clip(RoundedCornerShape(12.dp)),
            )
        }
        Card(
            modifier =
            Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .clickable { onClick() },
            colors =
            CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        ) {
            Text(
                text = "佐々木　太郎",
                modifier =
                Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .clip(RoundedCornerShape(12.dp)),
            )
        }
        Card(
            modifier =
            Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .clickable { onClick() },
            colors =
            CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        ) {
            Text(
                text = "佐々木　太郎",
                modifier =
                Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .clip(RoundedCornerShape(12.dp)),
            )
        }
    }
}

@Composable
fun SearchDetail() {
    Column {
        Text(text = "佐々木　太郎")
        Row {
            SuggestionChip(
                onClick = {
                },
                label = { Text("基本情報") },
            )
            SuggestionChip(
                onClick = {
                },
                label = { Text("付加情報") },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchContent(modifier: Modifier) {
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SampleTheme {
        SearchContent(modifier = Modifier)
    }
}
