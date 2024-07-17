package com.and.sample.screen.newmap

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.LocationOn
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.and.sample.CustomHalfSheet
import com.and.sample.R
import com.and.sample.SetupSnackBar
import com.and.sample.screen.search.SearchScreen2
import com.and.sample.ui.common.core.FilterDropDown
import com.and.sample.ui.common.core.SearchTopAppBar2
import com.and.sample.ui.theme.ChangeStatusBarColor2
import com.and.sample.ui.theme.SampleTheme
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class
)
@Composable
fun NewMapScreen(modifier: Modifier = Modifier, viewModel: NewMapViewModel = hiltViewModel()) {
    val bottomSheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldState: BottomSheetScaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)


    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    var backPressHandled by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val uiState: AddEditTaskUiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackHandler(enabled = !backPressHandled) {
        println("asdf back pressed")
        when (uiState.navigation) {
            NavigationDetails.NavigateSearchDetailsScreen -> {
                viewModel.showContent(NavigationDetails.NavigateSearchListScreen)
            }

            NavigationDetails.NavigateSearchListScreen -> {
                viewModel.navigationConsumed()
                if (bottomSheetState.isExpanded) {
                    scope.launch {
                        bottomSheetState.collapse()
                    }
                }
            }

            NavigationDetails.NavigateSearchScreen -> {
                viewModel.navigationConsumed()
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
        }
    }
    /*
    BottomSheetScaffold has no navigation bar padding in edgeToEdge() mode
    https://issuetracker.google.com/issues/315695275?pli=1
    */
    Scaffold(
        modifier = modifier
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
            modifier = modifier
                .fillMaxSize()
                .padding(padding),
            scaffoldState = scaffoldState,
            sheetPeekHeight = 0.dp,
            sheetShape = MaterialTheme.shapes.medium,
            sheetContent = {
//                Box(
//                    modifier = modifier
//                        .heightIn(min = 300.dp, max = 600.dp)
//
//                ) {
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Top,
//                    ) {
//                        Box(
//                            modifier = modifier
//                                .fillMaxWidth()
//                        ) {
//                            BottomSheetDefaults.DragHandle(modifier = modifier.align(Alignment.TopCenter))
//                            IconButton(
//                                modifier = modifier
//                                    .align(Alignment.TopStart)
//                                    .padding(start = 4.dp),
//                                onClick = {
//                                    onBackPressedDispatcher?.onBackPressed()
//                                }
//                            ) {
//                                Icon(
//                                    Icons.Filled.ArrowBackIosNew,
//                                    modifier = modifier
//                                        .width(54.dp)
//                                        .height(54.dp)
//                                        .padding(6.dp),
//                                    contentDescription = stringResource(id = R.string.contentDescription),
//                                    tint = Color.Gray
//                                )
//                            }
//                        }
//                        Column(
//                            modifier = modifier.verticalScroll(rememberScrollState()),
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.Top,
//                        ) {
//                            uiState.navigation?.let {
//                                when (it) {
//                                    NavigationDetails.NavigateSearchDetailsScreen -> {
//                                        SearchDetail()
//                                    }
//
//                                    NavigationDetails.NavigateSearchListScreen -> {
//                                        SearchList() {
//                                            viewModel.showContent(NavigationDetails.NavigateSearchDetailsScreen)
//                                        }
////                                        Column {
////                                            Text(
////                                                text = "Search List",
////                                                style = MaterialTheme.typography.displaySmall,
////                                            )
////                                            SuggestionChip(
////                                                onClick = {
////
////                                                },
////                                                label = { Text("Suggestion chip") }
////                                            )
////                                        }
//                                    }
//
//                                    NavigationDetails.NavigateSearchScreen -> {
//
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
                Box(
                    modifier = modifier
                        .heightIn(min = 400.dp, max = 700.dp)

                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                    ) {
                        Box(
                            modifier = modifier
                                .fillMaxWidth()
                        ) {
                            BottomSheetDefaults.DragHandle(modifier = modifier.align(Alignment.TopCenter))
                            IconButton(
                                modifier = modifier
                                    .align(Alignment.TopStart)
                                    .padding(start = 4.dp),
                                onClick = {
                                    onBackPressedDispatcher?.onBackPressed()
                                }
                            ) {
                                Icon(
                                    Icons.Filled.ArrowBackIosNew,
                                    modifier = modifier
                                        .width(54.dp)
                                        .height(54.dp)
                                        .padding(6.dp),
                                    contentDescription = stringResource(id = R.string.contentDescription),
                                    tint = Color.Gray
                                )
                            }
                        }
                        Box(
                            modifier = modifier
                                .fillMaxWidth(),

                            ) {
                            Text(
                                text = "検索結果　：　４件", modifier
                                    .align(Alignment.CenterStart)
                                    .padding(start = 24.dp),
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.SemiBold
                            )
                            FilterDropDown(
                                modifier = modifier
                                    .wrapContentSize()
                                    .align(Alignment.TopEnd)
                            ) {

                            }
                            HorizontalDivider(
                                modifier = modifier
                                    .padding(start = 16.dp, end = 16.dp)
                                    .align(Alignment.BottomCenter)
                            )
                        }
                        Column(
                            modifier = modifier.verticalScroll(rememberScrollState()),
                        ) {
                            /*uiState.navigation?.let {
                                when (it) {
                                    NavigationDetails.NavigateSearchDetailsScreen -> {
                                        SearchDetail()
                                    }

                                    NavigationDetails.NavigateSearchListScreen -> {
                                        SearchList(){
                                            viewModel.showContent(NavigationDetails.NavigateSearchDetailsScreen)
                                        }
            //                                        Column {
            //                                            Text(
            //                                                text = "Search List",
            //                                                style = MaterialTheme.typography.displaySmall,
            //                                            )
            //                                            SuggestionChip(
            //                                                onClick = {
            //
            //                                                },
            //                                                label = { Text("Suggestion chip") }
            //                                            )
            //                                        }
                                    }

                                    NavigationDetails.NavigateSearchScreen -> {

                                    }
                                }
                            }*/

                            uiState.navigation?.let {
                                when (it) {
                                    NavigationDetails.NavigateSearchDetailsScreen -> {
                                        Box(modifier = modifier.fillMaxWidth()) {
                                            Column(
                                                modifier = modifier
                                                    .align(Alignment.TopStart)
                                                    .padding(
                                                        start = 24.dp,
                                                        end = 24.dp,
                                                        bottom = 8.dp
                                                    )
                                            ) {
                                                Text(
                                                    text = "Aマンション", modifier = modifier
                                                        .padding(vertical = 4.dp),
                                                    style = MaterialTheme.typography.bodyLarge,
                                                    fontWeight = FontWeight.SemiBold
                                                )
                                                Text(
                                                    text = "〒012-0011　東京都新宿区3-5-4",
                                                    modifier = modifier,
                                                    style = MaterialTheme.typography.bodySmall,
                                                    color = Color.Gray,
                                                )
                                            }
                                            Row(
                                                modifier = modifier
                                                    .wrapContentSize()
                                                    .padding(start = 8.dp, end = 18.dp)
                                                    .align(Alignment.CenterEnd),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Card(
                                                    modifier = modifier
                                                        .wrapContentSize()
                                                        .padding(start = 8.dp, end = 18.dp)
                                                        .clip(RoundedCornerShape(50)),
                                                    colors = CardDefaults.cardColors(
                                                        containerColor = Color.Transparent,
                                                    ),
                                                    shape = RoundedCornerShape(50),
                                                    border = BorderStroke(1.dp, Color.Gray),
                                                ) {
                                                    Text(
                                                        "2件",
                                                        modifier = modifier.padding(
                                                            horizontal = 12.dp,
                                                            vertical = 2.dp
                                                        ),
                                                        style = MaterialTheme.typography.bodySmall
                                                    )
                                                }
                                                IconButton(
                                                    modifier = modifier
                                                        .padding(start = 4.dp),
                                                    onClick = {
                                                        /*onBackPressedDispatcher?.onBackPressed()*/
                                                    }
                                                ) {
                                                    Icon(
                                                        Icons.AutoMirrored.Filled.NavigateNext,
                                                        modifier = modifier
                                                            .width(54.dp)
                                                            .height(54.dp)
                                                            .padding(6.dp),
                                                        contentDescription = stringResource(id = R.string.contentDescription),
                                                        tint = Color.Gray
                                                    )
                                                }
                                            }
                                            HorizontalDivider(
                                                modifier = modifier
                                                    .padding(start = 16.dp, end = 16.dp)
                                                    .align(Alignment.BottomCenter)
                                            )
                                        }
                                    }

                                    NavigationDetails.NavigateSearchListScreen -> {
                                        // type 1
                                        Box(modifier = modifier.fillMaxWidth()) {
                                            Column(
                                                modifier = modifier
                                                    .align(Alignment.TopStart)
                                                    .padding(
                                                        start = 24.dp,
                                                        end = 24.dp,
                                                        bottom = 8.dp
                                                    )
                                            ) {
                                                Text(
                                                    text = "佐々木　太郎", modifier = modifier
                                                        .padding(vertical = 8.dp),
                                                    style = MaterialTheme.typography.bodyLarge,
                                                    fontWeight = FontWeight.SemiBold
                                                )
                                                Text(
                                                    text = "佐々木　花子（旧姓：田中　花子）、佐々木　ー",
                                                    modifier = modifier
                                                        .padding(vertical = 2.dp),
                                                    style = MaterialTheme.typography.bodySmall,
                                                    color = Color.Gray,
                                                )
                                                Text(
                                                    text = "〒012-0011　東京都新宿区3-5-4",
                                                    modifier = modifier,
                                                    style = MaterialTheme.typography.bodySmall,
                                                    color = Color.Gray,
                                                )
                                            }
                                            Card(
                                                modifier = modifier
                                                    .wrapContentSize()
                                                    .padding(start = 8.dp, end = 18.dp)
                                                    .align(Alignment.CenterEnd)
                                                    .clip(RoundedCornerShape(8.dp))
                                                    .clickable { },
                                                colors = CardDefaults.cardColors(
                                                    containerColor = Color.Transparent,
                                                ),
                                                shape = RoundedCornerShape(8.dp),
                                                border = BorderStroke(1.dp, Color.Gray),
                                            ) {
                                                Text(
                                                    "原簿を見る",
                                                    modifier = modifier.padding(8.dp),
                                                    style = MaterialTheme.typography.bodySmall
                                                )
                                            }
                                            HorizontalDivider(
                                                modifier = modifier
                                                    .padding(start = 16.dp, end = 16.dp)
                                                    .align(Alignment.BottomCenter)
                                            )
                                        }
                                        // type 2
                                        Box(modifier = modifier.fillMaxWidth()) {
                                            Column(
                                                modifier = modifier
                                                    .align(Alignment.TopStart)
                                                    .padding(
                                                        start = 24.dp,
                                                        end = 24.dp,
                                                        bottom = 8.dp
                                                    )
                                            ) {
                                                Text(
                                                    text = "Aマンション", modifier = modifier
                                                        .padding(vertical = 4.dp),
                                                    style = MaterialTheme.typography.bodyLarge,
                                                    fontWeight = FontWeight.SemiBold
                                                )
                                                Text(
                                                    text = "〒012-0011　東京都新宿区3-5-4",
                                                    modifier = modifier,
                                                    style = MaterialTheme.typography.bodySmall,
                                                    color = Color.Gray,
                                                )
                                            }
                                            Row(
                                                modifier = modifier
                                                    .wrapContentSize()
                                                    .padding(start = 8.dp, end = 18.dp)
                                                    .align(Alignment.CenterEnd),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Card(
                                                    modifier = modifier
                                                        .wrapContentSize()
                                                        .padding(start = 8.dp, end = 18.dp)
                                                        .clip(RoundedCornerShape(50)),
                                                    colors = CardDefaults.cardColors(
                                                        containerColor = Color.Transparent,
                                                    ),
                                                    shape = RoundedCornerShape(50),
                                                    border = BorderStroke(1.dp, Color.Gray),
                                                ) {
                                                    Text(
                                                        "2件",
                                                        modifier = modifier.padding(
                                                            horizontal = 12.dp,
                                                            vertical = 2.dp
                                                        ),
                                                        style = MaterialTheme.typography.bodySmall
                                                    )
                                                }
                                                IconButton(
                                                    modifier = modifier
                                                        .padding(start = 4.dp),
                                                    onClick = {
                                                        /*onBackPressedDispatcher?.onBackPressed()*/
                                                    }
                                                ) {
                                                    Icon(
                                                        Icons.AutoMirrored.Filled.NavigateNext,
                                                        modifier = modifier
                                                            .width(54.dp)
                                                            .height(54.dp)
                                                            .padding(6.dp),
                                                        contentDescription = stringResource(id = R.string.contentDescription),
                                                        tint = Color.Gray
                                                    )
                                                }
                                            }
                                            HorizontalDivider(
                                                modifier = modifier
                                                    .padding(start = 16.dp, end = 16.dp)
                                                    .align(Alignment.BottomCenter)
                                            )
                                        }

//                deplicate type 1
                                        Box(modifier = modifier.fillMaxWidth()) {
                                            Column(
                                                modifier = modifier
                                                    .align(Alignment.TopStart)
                                                    .padding(
                                                        start = 24.dp,
                                                        end = 24.dp,
                                                        bottom = 8.dp
                                                    )
                                            ) {
                                                Text(
                                                    text = "佐々木　太郎", modifier = modifier
                                                        .padding(vertical = 8.dp),
                                                    style = MaterialTheme.typography.bodyLarge,
                                                    fontWeight = FontWeight.SemiBold
                                                )
                                                Text(
                                                    text = "佐々木　花子（旧姓：田中　花子）、佐々木　ー",
                                                    modifier = modifier
                                                        .padding(vertical = 2.dp),
                                                    style = MaterialTheme.typography.bodySmall,
                                                    color = Color.Gray,
                                                )
                                                Text(
                                                    text = "〒012-0011　東京都新宿区3-5-4",
                                                    modifier = modifier,
                                                    style = MaterialTheme.typography.bodySmall,
                                                    color = Color.Gray,
                                                )
                                            }
                                            Card(
                                                modifier = modifier
                                                    .wrapContentSize()
                                                    .padding(start = 8.dp, end = 18.dp)
                                                    .align(Alignment.CenterEnd)
                                                    .clip(RoundedCornerShape(8.dp))
                                                    .clickable { },
                                                colors = CardDefaults.cardColors(
                                                    containerColor = Color.Transparent,
                                                ),
                                                shape = RoundedCornerShape(8.dp),
                                                border = BorderStroke(1.dp, Color.Gray),
                                            ) {
                                                Text(
                                                    "原簿を見る",
                                                    modifier = modifier.padding(8.dp),
                                                    style = MaterialTheme.typography.bodySmall
                                                )
                                            }
                                            HorizontalDivider(
                                                modifier = modifier
                                                    .padding(start = 16.dp, end = 16.dp)
                                                    .align(Alignment.BottomCenter)
                                            )
                                        }
                                    }

                                    NavigationDetails.NavigateSearchScreen -> {

                                    }
                                }
                            }


                        }
                    }
                }
            }
        ) {
//            mapcontent
            val showDialog = remember { mutableStateOf(false) }
            if (showDialog.value) {
                CustomHalfSheet(name = "ここに家形を遷移しますか？",
                    showDialog = showDialog.value,
                    onDismiss = { showDialog.value = false })
            }

            Box(
                modifier = modifier
                    .fillMaxSize()
            ) {
                Image(
                    modifier = modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.map),
                    contentDescription = stringResource(id = R.string.contentDescription)
                )
                Box(modifier = modifier.fillMaxSize()) {
                    Column(
                        modifier = modifier
                            .align(Alignment.BottomEnd),
                        horizontalAlignment = Alignment.End,

                        ) {
                        Card(
                            modifier = modifier
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
                                modifier = modifier
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
                        Spacer(modifier = modifier.padding(4.dp))
                        Card(
                            modifier = modifier
                                .padding(8.dp)
                                .clip(RoundedCornerShape(50))
                                .clickable {
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                            )
                        ) {
                            Image(
                                modifier = modifier
                                    .width(54.dp)
                                    .height(54.dp)
                                    .padding(6.dp),
                                alpha = 0.7f,
                                imageVector = Icons.Filled.RadioButtonChecked,
                                contentDescription = stringResource(id = R.string.contentDescription)
                            )
                        }
                        Spacer(modifier = modifier.padding(1.dp))
                        TextButton(
                            onClick = { }
                        ) {
                            Text("ON/OFF")
                        }
                    }
                }

                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Card(
                        modifier = modifier
                            .align(Alignment.TopStart)
                            .clip(RoundedCornerShape(50))
                            .clickable {
//                            viewModel.setSnackbarMessage("menu click")
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.background,
                        )
                    ) {
                        Image(
                            modifier = modifier
                                .width(54.dp)
                                .height(54.dp)
                                .padding(6.dp),
                            alpha = 0.7f,
                            imageVector = Icons.Filled.Menu,
                            contentDescription = stringResource(id = R.string.contentDescription)
                        )
                    }

                    Column(
                        modifier = modifier
                            .align(Alignment.TopEnd)
                    ) {
                        Card(
                            modifier = modifier
                                .clip(RoundedCornerShape(50))
                                .clickable {
                                    viewModel.showContent(NavigationDetails.NavigateSearchScreen)
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                            )
                        ) {
                            Image(
                                modifier = modifier
                                    .width(54.dp)
                                    .height(54.dp)
                                    .padding(6.dp),
                                alpha = 0.7f,
                                imageVector = Icons.Filled.Search,
                                contentDescription = stringResource(id = R.string.contentDescription)
                            )
                        }
                        Spacer(modifier = modifier.padding(4.dp))
                        Card(
                            modifier = modifier
                                .clip(RoundedCornerShape(50))
                                .clickable {
                                    scope.launch {
//                                        snackState.showSnackbar("該当する家形または配置位置をタップしてください")
                                    }
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                            )
                        ) {
                            Image(
                                modifier = modifier
                                    .width(54.dp)
                                    .height(54.dp)
                                    .padding(6.dp),
                                alpha = 0.7f,
                                imageVector = Icons.Filled.Layers,
                                contentDescription = stringResource(id = R.string.contentDescription)
                            )
                        }
                        Spacer(modifier = modifier.padding(4.dp))
                        Card(
                            modifier = modifier
                                .clip(RoundedCornerShape(50))
                                .clickable {
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                            )
                        ) {
                            Image(
                                modifier = modifier
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

                /*demo*/
                when (uiState.navigation) {
                    NavigationDetails.NavigateSearchDetailsScreen -> {
                        Image(
                            modifier = modifier
                                .align(Alignment.CenterEnd)
                                .width(54.dp)
                                .height(54.dp)
                                .padding(6.dp)
                                .clickable {
                                    viewModel.showContent(NavigationDetails.NavigateSearchDetailsScreen)
                                },
                            alpha = 0.7f,
                            imageVector = Icons.Filled.LocationOn,
                            colorFilter = ColorFilter.tint(Color.Red),
                            contentDescription = stringResource(id = R.string.contentDescription)
                        )
                    }

                    NavigationDetails.NavigateSearchListScreen -> {
                        Image(
                            modifier = modifier
                                .align(Alignment.CenterEnd)
                                .width(54.dp)
                                .height(54.dp)
                                .padding(6.dp)
                                .clickable {
                                    viewModel.showContent(NavigationDetails.NavigateSearchDetailsScreen)
                                },
                            alpha = 0.7f,
                            imageVector = Icons.Filled.LocationOn,
                            colorFilter = ColorFilter.tint(Color.Red),
                            contentDescription = stringResource(id = R.string.contentDescription)
                        )
                    }

                    NavigationDetails.NavigateSearchScreen -> {}
                    null -> {

                    }
                }

                SetupSnackBar(snackState)
                Column(
                    modifier = modifier.fillMaxSize()
                ) {


                    when (uiState.navigation) {
                        NavigationDetails.NavigateSearchDetailsScreen -> {
                            ChangeStatusBarColor2(Color.Gray.toArgb())
                            SearchTopAppBar2("検索メニュー") {
                                viewModel.navigationConsumed()
                                scope.launch {
                                    if (bottomSheetState.isCollapsed) {
                                        bottomSheetState.expand()
                                    } else {
                                        bottomSheetState.collapse()
                                    }
                                }
                            }
                        }

                        NavigationDetails.NavigateSearchListScreen -> {
                            ChangeStatusBarColor2(Color.Gray.toArgb())
                            SearchTopAppBar2("検索メニュー") {
                                viewModel.navigationConsumed()
                                scope.launch {
                                    if (bottomSheetState.isCollapsed) {
                                        bottomSheetState.expand()
                                    } else {
                                        bottomSheetState.collapse()
                                    }
                                }
                            }
                        }

                        NavigationDetails.NavigateSearchScreen -> {
                            ChangeStatusBarColor2(Color.Gray.toArgb())
                            SearchTopAppBar2("検索メニュー") {
                                viewModel.navigationConsumed()
                                scope.launch {
                                    if (bottomSheetState.isCollapsed) {
                                        bottomSheetState.expand()
                                    } else {
                                        bottomSheetState.collapse()
                                    }
                                }
                            }
                            SearchScreen2(
                                onSearch = {
                                    scope.launch {
                                        if (bottomSheetState.isCollapsed) {
                                            bottomSheetState.expand()
                                        } else {
                                            bottomSheetState.collapse()
                                        }
                                    }
                                    viewModel.showContent(NavigationDetails.NavigateSearchListScreen)
                                }
                            )
                        }

                        null -> {
                            ChangeStatusBarColor2(Color.White.toArgb())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchList(onClick: () -> Unit) {
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .clickable { onClick() },
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        ) {
            Text(
                text = "佐々木　太郎", modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }
        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .clickable { onClick() },
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        ) {
            Text(
                text = "佐々木　太郎", modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }
        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .clickable { onClick() },
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        ) {
            Text(
                text = "佐々木　太郎", modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .clip(RoundedCornerShape(12.dp))
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
                label = { Text("基本情報") }
            )
            SuggestionChip(
                onClick = {
                },
                label = { Text("付加情報") }
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