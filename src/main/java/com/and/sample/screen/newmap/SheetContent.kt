package com.and.sample.screen.newmap

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.and.sample.R
import com.and.sample.ui.common.core.FilterDropDown
import kotlinx.coroutines.launch

data class SearchResult(val item1: String, val item2: String, val item3: String)

val list = listOf(
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
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SheetContent(
    items: List<SearchResult> = list,
    modifier: Modifier,
    onBack: () -> Unit,
    mapContent: MapContent?,
    showSheet: () -> Unit,
    onDragIconClick: () -> Unit,
    onDetailClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
//                        .fillMaxHeight(0.905f)// under the toolbar
//                        .fillMaxHeight(0.975f)// under status bar,
    ) {
        when (mapContent) {
            MapContent.Map -> {}
            MapContent.Search -> {}
            MapContent.SearchResultList -> {
                SheetDragHandle(
                    modifier = modifier, onBack = onBack, onDragIconClick = onDragIconClick
                )
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
                LazyColumn {
                    items(items = items) {
                        ResultRow(it, modifier, onDetailClick = { onDetailClick() })
                    }
                }
                showSheet()
            }

            MapContent.SelectOneFromGroup -> {
                SheetDragHandle(
                    modifier = modifier, onBack = onBack, onDragIconClick = onDragIconClick
                )
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
                SearchList(modifier = modifier) {
                }
                showSheet()
            }

            MapContent.Detail -> {
                var a by remember { mutableStateOf(false) }
                var b by remember { mutableStateOf(false) }
                SheetDragHandle(
                    isShowBackButton = false,
                    modifier = modifier,
                    onBack = onBack,
                    onDragIconClick = onDragIconClick
                )
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
                        Row {
                            Card(
                                modifier =
                                modifier
                                    .wrapContentSize()
                                    .padding(bottom = 4.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable { onDetailClick() },
                                colors =
                                CardDefaults.cardColors(
                                    containerColor = Color.Transparent,
                                ),
                                shape = RoundedCornerShape(8.dp),
                                border = BorderStroke(1.dp, colorResource(id = R.color.gray)),
                            ) {
                                Text(
                                    "通配区/区分口",
                                    modifier = modifier.padding(8.dp),
                                    color = colorResource(id = R.color.gray),
                                    style = MaterialTheme.typography.labelSmall,
                                )
                            }
                            Text(
                                "123-456",
                                modifier = modifier.padding(8.dp),
                                color = colorResource(id = R.color.gray),
                                style = MaterialTheme.typography.labelSmall,
                            )
                        }
                        Text(
                            text = "〒012-0011　東京都新宿区3-5-4",
                            modifier = modifier,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                        )
                        Text(
                            text = "佐々木　太郎",
                            modifier =
                            modifier
                                .padding(vertical = 8.dp),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Text(
                            text = "佐々木　花子（旧姓：田中　花子）、佐々木　ー",
                            modifier =
                            modifier
                                .padding(vertical = 2.dp),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                        )
                        val asdf = listOf("基本情報", "付加情報", "転居情報", "建物周辺情報")
                        LazyRow {
                            items(asdf) {
                                FilterRow(label = it, modifier = modifier) { str ->
                                    when (str) {
                                        "基本情報" -> {
                                            a = true
                                            b = false
                                        }

                                        "付加情報" -> {
                                            b = true
                                            a = false
                                        }
                                    }
                                }
                            }
                        }
                        HorizontalDivider(
                            modifier =
                            modifier
                        )
                        if (a) {
                            Text(
                                text = "基本情報UI",
                                modifier =
                                modifier
                                    .padding(vertical = 8.dp),
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                        if (b) {
                            Text(
                                text = "付加情報UI",
                                modifier =
                                modifier
                                    .padding(vertical = 8.dp),
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    }
                }
            }

            null -> {
            }
        }

    }
}

@Composable
fun FilterRow(label: String, modifier: Modifier, onClick: (String) -> Unit) {
    var selected by remember { mutableStateOf(false) }
    FilterChip(
        modifier = modifier.padding(end = 8.dp),
        onClick = {
            onClick(label)
            selected = !selected
        },
        shape = RoundedCornerShape(50),
        colors = FilterChipDefaults.filterChipColors()
            .copy(
                containerColor = Color.Transparent,
                selectedContainerColor = colorResource(id = R.color.gray),
                selectedLabelColor = Color.White,
                labelColor = colorResource(id = R.color.gray)
            ),
        label = {
            Text(
                text = label,
                modifier =
                modifier
                    .padding(vertical = 8.dp),
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        selected = selected,
    )
}

@Composable
fun ResultRow(model: SearchResult, modifier: Modifier, onDetailClick: () -> Unit) {
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
                .clickable { onDetailClick() },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SheetDragHandle(
    isShowBackButton: Boolean = true,
    modifier: Modifier,
    onBack: () -> Unit,
    onDragIconClick: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
    ) {

        Card(
            modifier =
            modifier
                .align(Alignment.TopCenter)
                .clip(
                    RoundedCornerShape(
                        8.dp,
                    ),
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { onDragIconClick() },
            colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
        ) {
            BottomSheetDefaults.DragHandle(
                modifier = modifier
                    .padding(horizontal = 16.dp)
            )
        }
        if (isShowBackButton)
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
}

