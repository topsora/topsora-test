package com.and.sample.screen.newmap

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.and.sample.R
import com.and.sample.ui.common.core.FilterDropDown
import kotlinx.coroutines.launch

data class SearchResult(val item1: String, val item2: String, val item3: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SheetContent(
    items: List<SearchResult> = listOf(
        SearchResult(
            "佐々木　太郎",
            "佐々木　花子（旧姓：田中　花子）、佐々木　ー",
            "〒012-0011　東京都新宿区3-5-4"
        ),
        SearchResult(
            "佐々木　太郎",
            "佐々木　花子（旧姓：田中　花子）、佐々木　ー",
            "〒012-0011　東京都新宿区3-5-4"
        ),
        SearchResult(
            "佐々木　太郎",
            "佐々木　花子（旧姓：田中　花子）、佐々木　ー",
            "〒012-0011　東京都新宿区3-5-4"
        ),
        SearchResult(
            "佐々木　太郎",
            "佐々木　花子（旧姓：田中　花子）、佐々木　ー",
            "〒012-0011　東京都新宿区3-5-4"
        ),
        SearchResult(
            "佐々木　太郎",
            "佐々木　花子（旧姓：田中　花子）、佐々木　ー",
            "〒012-0011　東京都新宿区3-5-4"
        ),
        SearchResult(
            "佐々木　太郎",
            "佐々木　花子（旧姓：田中　花子）、佐々木　ー",
            "〒012-0011　東京都新宿区3-5-4"
        ),
        SearchResult(
            "佐々木　太郎",
            "佐々木　花子（旧姓：田中　花子）、佐々木　ー",
            "〒012-0011　東京都新宿区3-5-4"
        ),
        SearchResult(
            "佐々木　太郎",
            "佐々木　花子（旧姓：田中　花子）、佐々木　ー",
            "〒012-0011　東京都新宿区3-5-4"
        ),
        SearchResult(
            "佐々木　太郎",
            "佐々木　花子（旧姓：田中　花子）、佐々木　ー",
            "〒012-0011　東京都新宿区3-5-4"
        ),
        SearchResult(
            "佐々木　太郎",
            "佐々木　花子（旧姓：田中　花子）、佐々木　ー",
            "〒012-0011　東京都新宿区3-5-4"
        ),
        SearchResult(
            "佐々木　太郎",
            "佐々木　花子（旧姓：田中　花子）、佐々木　ー",
            "〒012-0011　東京都新宿区3-5-4"
        ),
        SearchResult(
            "佐々木　太郎 end",
            "佐々木　花子（旧姓：田中　花子）、佐々木　ー",
            "〒012-0011　東京都新宿区3-5-4"
        ),
    ),
    modifier: Modifier,
    onBack: () -> Unit,
    sheetState: SheetState,
    mapContent: MapContent?,
    hideSheet: () -> Unit,
    onDragIconClick: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

        modifier = modifier
//            .verticalScroll(rememberScrollState())
//            .fillMaxHeight(0.99f)
//            .heightIn(min = 400.dp),
    ) {
        when (mapContent) {
            MapContent.Detail -> {}
            MapContent.Map -> {}
            MapContent.Search -> {}
            MapContent.SearchResultList -> {
                hideSheet()
                Box(
                    modifier = modifier.fillMaxWidth(),
                ) {
                    BottomSheetDefaults.DragHandle(modifier = modifier
                        .align(Alignment.TopCenter)
                        .clickable { onDragIconClick() }
                    )
                    IconButton(
                        modifier =
                        modifier
                            .align(Alignment.TopStart)
                            .padding(start = 4.dp),
                        onClick = {
                            onBack()
//                    onBackPressedDispatcher?.onBackPressed()
                        },
                    ) {
                        Icon(
                            Icons.Filled.ArrowBackIosNew,
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
                Box(
                    modifier =
                    modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "検索結果　：　４件",
                        modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 24.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                    )
                    FilterDropDown(
                        modifier =
                        modifier
                            .wrapContentSize()
                            .align(Alignment.TopEnd),
                    ) {
                    }
                    HorizontalDivider(
                        modifier =
                        modifier
                            .padding(start = 16.dp, end = 16.dp)
                            .align(Alignment.BottomCenter),
                    )
                }
//                Column(
//                    modifier = modifier
//                        .verticalScroll(rememberScrollState())
//                ) {
//                    items.forEach {
//                        ResultRow(it, modifier)
//                    }
//                }
                LazyColumn {
                    items(items = items) {
                        ResultRow(it, modifier)
                    }
                }
            }

            MapContent.SelectOneFromGroup -> {
            }

            null -> {
            }
        }

    }
}

@Composable
fun ResultRow(model: SearchResult, modifier: Modifier) {
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
                text = model.item1,
                modifier =
                modifier
                    .padding(vertical = 8.dp),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = model.item2,
                modifier =
                modifier
                    .padding(vertical = 2.dp),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
            )
            Text(
                text = model.item3,
                modifier = modifier,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
            )
        }
        Card(
            modifier =
            modifier
                .wrapContentSize()
                .padding(start = 8.dp, end = 18.dp)
                .align(Alignment.CenterEnd)
                .clip(RoundedCornerShape(8.dp))
                .clickable { },
            colors =
            CardDefaults.cardColors(
                containerColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.Gray),
        ) {
            Text(
                "原簿を見る",
                modifier = modifier.padding(8.dp),
                style = MaterialTheme.typography.bodySmall,
            )
        }
        HorizontalDivider(
            modifier =
            modifier
                .padding(start = 16.dp, end = 16.dp)
                .align(Alignment.BottomCenter),
        )
    }
}

@Composable
fun BottomSheetGestureWrapper(
    modifier: Modifier = Modifier,
    onExpandTypeChanged: (ExpandedType) -> Unit,
    content: @Composable () -> Unit
) {

    var expandedType by remember {
        mutableStateOf(ExpandedType.COLLAPSED)
    }

    var isUpdated = false

    LaunchedEffect(key1 = expandedType) {
        onExpandTypeChanged(expandedType)
    }

    Box(
        modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectVerticalDragGestures(
                    onVerticalDrag = { change, dragAmount ->
                        change.consume()
                        if (!isUpdated) {
                            expandedType = when {
                                dragAmount < 0 && expandedType == ExpandedType.COLLAPSED -> {
                                    ExpandedType.HALF
                                }

                                dragAmount < 0 && expandedType == ExpandedType.HALF -> {
                                    ExpandedType.FULL
                                }

                                dragAmount > 0 && expandedType == ExpandedType.FULL -> {
                                    ExpandedType.HALF
                                }

                                dragAmount > 0 && expandedType == ExpandedType.HALF -> {
                                    ExpandedType.COLLAPSED
                                }

                                else -> {
                                    expandedType
                                }
                            }
                            isUpdated = true
                        }
                    },
                    onDragEnd = {
                        isUpdated = false
                    }
                )
            }
            .background(Color.White)
    ) {
        content()
    }
}

