package com.and.sample.screen.map

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.Satellite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.sample.CustomHalfSheet
import com.and.sample.R
import com.and.sample.SetupSnackBar
import com.and.sample.screen.search.SearchScreen2
import com.and.sample.ui.common.core.SearchTopAppBar2
import com.and.sample.ui.theme.ChangeStatusBarColor
import com.and.sample.ui.theme.SampleTheme
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class
)
@Composable
fun NewMapScreenWithBottomSheetSupport() {
    val bottomSheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
    var isSearchMode by remember { mutableStateOf(false) }
    var isShowSearchLayout by remember { mutableStateOf(false) }
    var isShowSearchList by remember { mutableStateOf(false) }
    var isShowSearchDetails by remember { mutableStateOf(false) }


    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    var backPressHandled by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    BackHandler(enabled = !backPressHandled) {
        println("asdf back pressed")
        when {
            isShowSearchDetails -> {
                println("asdf 1")
                isShowSearchDetails = false
                isShowSearchList = true
            }

            isShowSearchList -> {
                println("asdf 2")
                if (bottomSheetState.isExpanded) {
                    println("asdf 3")
                    coroutineScope.launch {
                        bottomSheetState.collapse()
                    }
                }
                isSearchMode = false
                isShowSearchList = false
            }

            isShowSearchLayout -> {
                isSearchMode = false
                isShowSearchLayout = false
            }

            else -> {
                println("asdf back pressed else")
                backPressHandled = true
                coroutineScope.launch {
                    awaitFrame()
                    onBackPressedDispatcher?.onBackPressed()
                    backPressHandled = false
                }
            }
        }
    }
    /*
    BottomSheetScaffold has no navigation bar padding in edgeToEdge() mode
    https://issuetracker.google.com/issues/315695275?pli=1
    */
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
    ) { padding ->
        BottomSheetScaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            scaffoldState = scaffoldState,
            sheetPeekHeight = 0.dp,
            sheetShape = MaterialTheme.shapes.medium,
            sheetContent = {
                Box(
                    modifier = Modifier
                        .heightIn(min = 300.dp, max = 600.dp)

                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            BottomSheetDefaults.DragHandle(modifier = Modifier.align(Alignment.TopCenter))
                            IconButton(
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .padding(start = 4.dp),
                                onClick = {
                                    onBackPressedDispatcher?.onBackPressed()
                                }
                            ) {
                                Icon(
                                    Icons.Filled.ArrowBackIosNew,
                                    modifier = Modifier
                                        .width(54.dp)
                                        .height(54.dp)
                                        .padding(6.dp),
                                    contentDescription = stringResource(id = R.string.contentDescription),
                                    tint = Color.Gray
                                )
                            }
                        }
                        Column(
                            modifier = Modifier.verticalScroll(rememberScrollState()),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top,
                        ) {
//                        Text(
//                            text = "Search List",
//                            modifier = Modifier
//                                .height(700.dp),
//                            style = MaterialTheme.typography.displaySmall,
//                        )
                            if (isShowSearchList) {
                                Column {
                                    Text(
                                        text = "Search List",
                                        style = MaterialTheme.typography.displaySmall,
                                    )
                                    SuggestionChip(
                                        onClick = {
                                            isShowSearchList = false
                                            isShowSearchDetails = true
                                        },
                                        label = { Text("Suggestion chip") }
                                    )
//                                Spacer(modifier = Modifier.height(300.dp))
                                }
                            }
                            if (isShowSearchDetails) {
                                Text(
                                    text = "details",
                                    style = MaterialTheme.typography.displaySmall,
                                )
//                            SuggestionChip(
//                                onClick = { Log.d("Suggestion chip", "hello world") },
//                                label = { Text("Suggestion chip") }
//                            )
                            }
                        }
                    }
                }
            }
        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .navigationBarsPadding()
//            ) {
//                Image(
//                    modifier = Modifier
//                        .fillMaxSize(),
//                    contentScale = ContentScale.Crop,
//                    painter = painterResource(id = R.drawable.map),
//                    contentDescription = stringResource(id = R.string.contentDescription)
//                )
//                Button(
//                    modifier = Modifier
//                        .padding(bottom = 200.dp)
//                        .wrapContentSize()
//                        .padding(bottom = 300.dp)
//                        .align(Alignment.Center),
//                    onClick = {
//                        coroutineScope.launch {
//                            if (bottomSheetState.isCollapsed) {
//                                bottomSheetState.expand()
//                                isShowSearchList = true
//                            } else {
//                                bottomSheetState.collapse()
//                            }
//
//                        }
//                    }) {
//                    (Text(text = "show button sheet"))
//                }
//            }
            val showDialog = remember { mutableStateOf(false) }
            if (showDialog.value) {
                CustomHalfSheet(name = "ここに家形を遷移しますか？",
                    showDialog = showDialog.value,
                    onDismiss = { showDialog.value = false })
            }

            val snackState = remember { SnackbarHostState() }
            val coroutineScope = rememberCoroutineScope()
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.map),
                    contentDescription = stringResource(id = R.string.contentDescription)
                )
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomEnd),
                        horizontalAlignment = Alignment.End,

                        ) {
                        Card(
                            modifier = Modifier
                                .clip(
                                    RoundedCornerShape(
                                        topStartPercent = 15,
                                        bottomStartPercent = 15
                                    )
                                )
                                .clickable { },
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                            ),
                        ) {
                            Image(
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .width(54.dp)
                                    .height(54.dp)
                                    .padding(
                                        6.dp
                                    ),
                                alpha = 0.7f,
                                imageVector = Icons.Filled.Satellite,
                                contentDescription = stringResource(id = R.string.contentDescription)
                            )
                        }
                        Spacer(modifier = Modifier.padding(4.dp))
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .clip(RoundedCornerShape(50))
                                .clickable {
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                            )
                        ) {
                            Image(
                                modifier = Modifier
                                    .width(54.dp)
                                    .height(54.dp)
                                    .padding(6.dp),
                                alpha = 0.7f,
                                imageVector = Icons.Filled.RadioButtonChecked,
                                contentDescription = stringResource(id = R.string.contentDescription)
                            )
                        }
                        Spacer(modifier = Modifier.padding(1.dp))
                        TextButton(
                            onClick = { }
                        ) {
                            Text("ON/OFF")
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .clip(RoundedCornerShape(50))
                            .clickable {
//                                showDialog.value = true
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.background,
                        )
                    ) {
                        Image(
                            modifier = Modifier
                                .width(54.dp)
                                .height(54.dp)
                                .padding(6.dp),
                            alpha = 0.7f,
                            imageVector = Icons.Filled.Menu,
                            contentDescription = stringResource(id = R.string.contentDescription)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                    ) {
                        Card(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .clickable {
                                    //                                    coroutineScope.launch {
                                    //                                        snackState.showSnackbar(
                                    //                                            "該当する家形または配置位置をタップしてください",
                                    //                                            withDismissAction = true
                                    //                                        )
                                    //                                    }
                                    //
                                    //                                    onSearch()
                                    isSearchMode = true
                                    isShowSearchLayout = true
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                            )
                        ) {
                            Image(
                                modifier = Modifier
                                    .width(54.dp)
                                    .height(54.dp)
                                    .padding(6.dp),
                                alpha = 0.7f,
                                imageVector = Icons.Filled.Search,
                                contentDescription = stringResource(id = R.string.contentDescription)
                            )
                        }
                        Spacer(modifier = Modifier.padding(4.dp))
                        Card(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .clickable {
                                    coroutineScope.launch {
                                        snackState.showSnackbar("該当する家形または配置位置をタップしてください")
                                    }
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                            )
                        ) {
                            Image(
                                modifier = Modifier
                                    .width(54.dp)
                                    .height(54.dp)
                                    .padding(6.dp),
                                alpha = 0.7f,
                                imageVector = Icons.Filled.Layers,
                                contentDescription = stringResource(id = R.string.contentDescription)
                            )
                        }
                        Spacer(modifier = Modifier.padding(4.dp))
                        Card(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .clickable { },
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                            )
                        ) {
                            Image(
                                modifier = Modifier
                                    .width(54.dp)
                                    .height(54.dp)
                                    .padding(6.dp),
                                alpha = 0.7f,
                                imageVector = Icons.Filled.BookmarkBorder,
                                contentDescription = stringResource(id = R.string.contentDescription)
                            )
                        }
                    }
                }
                SetupSnackBar(snackState)
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {

                    if (isSearchMode) {
                        ChangeStatusBarColor(Color.Gray.toArgb())
                        SearchTopAppBar2("検索メニュー2") {
                            isSearchMode = false
                            onBackPressedDispatcher?.onBackPressed()
                        }
                    }
                    if (isShowSearchLayout)
                        SearchScreen2(
                            onSearch = {
                                isShowSearchLayout = false
                                coroutineScope.launch {
                                    if (bottomSheetState.isCollapsed) {
                                        bottomSheetState.expand()
                                        isShowSearchList = true
                                    } else {
                                        bottomSheetState.collapse()
                                    }

                                }
                            }
                        )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview2() {
    SampleTheme {
//        NewMapScreen()
    }
}

/*
isLoading
isEmpty
error
content
    screen
message
navigation


* */
