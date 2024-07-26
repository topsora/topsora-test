package com.and.sample.screen.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.and.sample.CustomHalfSheet
import com.and.sample.R
import com.and.sample.SetupSnackBar
import com.and.sample.screen.search.SearchScreen2
import com.and.sample.ui.common.core.SearchTopAppBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MapContent(
    modifier: Modifier,
    mapContent: MapContent?,
    showListButton: Boolean,
    onShowListButton: (Boolean) -> Unit,
    onShowContent: (MapContent) -> Unit,
    onContentConsumed: () -> Unit,
    onBack: () -> Unit,
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    val showDialog = remember { mutableStateOf(false) }
    val snackState = remember { SnackbarHostState() }

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
        if (showListButton) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(Color.White)
            ) {
                TextButton(
                    modifier = modifier,
                    onClick = {
                        onShowListButton.invoke(false)
                    }
                ) {
                    Text(text = "リストを表示")
                }
            }
        }
        /*if (showListButton)//showListButton
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(Color.White)
            ) {
                TextButton(
                    modifier = modifier,
                    onClick = {
                        onShowListButton.invoke(false)
                    }
                ) {
                    Text(text = "リストを表示")
                }
            }*/

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
                            onShowContent(MapContent.Search())
//                            viewModel.showContent(MapContent.Search)
//                                        updateHeight = halfHeight
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
        when (mapContent) {
            is MapContent.SelectOneFromGroup -> {
                Image(
                    modifier =
                    modifier
                        .align(Alignment.CenterEnd)
                        .width(54.dp)
                        .height(54.dp)
                        .padding(6.dp)
                        .clickable {
                            onShowContent(MapContent.SelectOneFromGroup())
                        },
                    alpha = 0.7f,
                    imageVector = Icons.Filled.LocationOn,
                    colorFilter = ColorFilter.tint(Color.Red),
                    contentDescription = stringResource(id = R.string.contentDescription),
                )
            }

            is MapContent.SearchResultList -> {
                Image(
                    modifier =
                    modifier
                        .align(Alignment.CenterEnd)
                        .width(54.dp)
                        .height(54.dp)
                        .padding(6.dp)
                        .clickable {
                            onShowContent(MapContent.SelectOneFromGroup())
                        },
                    alpha = 0.7f,
                    imageVector = Icons.Filled.LocationOn,
                    colorFilter = ColorFilter.tint(Color.Red),
                    contentDescription = stringResource(id = R.string.contentDescription),
                )
            }

            is MapContent.Search -> {

            }

            null -> {
            }

            is MapContent.Detail -> {}
            is MapContent.Map -> {}
        }

        SetupSnackBar(snackState)

        Column(
            modifier = modifier.fillMaxSize(),
        ) {


            if (mapContent?.showSearchTopBar == true)
                SearchTopAppBar(text = "検索メニュー", closeButtonClick = {
                    onBack()
                })
            when (mapContent) {
                is MapContent.SelectOneFromGroup -> {
                }

                is MapContent.Search -> {
                    SearchScreen2(
                        onSearch = {
                            onShowContent(MapContent.SearchResultList())
//                            updateHeight = halfHeight
                        },
                    )
                }

                null -> {
                }

                is MapContent.Detail -> {

                }

                is MapContent.Map -> {
//                    snsh 9
//                    viewModel.contentConsumed()
                }

                is MapContent.SearchResultList -> {

                }
            }
        }

    }
}
