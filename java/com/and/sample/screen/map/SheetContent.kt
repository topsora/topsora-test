package com.and.sample.screen.map

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.and.sample.R
import com.and.sample.ui.common.core.FilterDropDown

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
    onShowContent: (MapContent) -> Unit,
    showSheet: () -> Unit,
    onDragIconClick: () -> Unit,
    onDetailClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier.fillMaxSize()
//                        .fillMaxHeight(0.905f)// under the toolbar
//                        .fillMaxHeight(0.975f)// under status bar,
    ) {
        when (mapContent) {
            is MapContent.Map -> {}
            is MapContent.Search -> {}
            is MapContent.SearchResultList -> {
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

            is MapContent.SelectOneFromGroup -> {
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

            is MapContent.Detail -> {
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
                            text = "〒012-0011　東京都新宿区3-5-4 ⓘ",
                            modifier = modifier,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                        )
                        Box(
                            modifier =
                            modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "佐々木　太郎",
                                modifier =
                                modifier
                                    .padding(vertical = 8.dp),
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Row(
                                modifier =
                                modifier
                                    .align(Alignment.CenterEnd)
                            ) {

                                Text(
                                    text = "誤",
                                    modifier =
                                    modifier
                                        .padding(horizontal = 8.dp, vertical = 0.dp)
                                        .drawBehind {
                                            drawCircle(
                                                color = Color(0xFFF44336),
                                                radius = 50f,
                                            )
                                        }
                                        .padding(4.dp),
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.White,
                                )
                                Text(
                                    text = "注",
                                    modifier =
                                    modifier
                                        .padding(horizontal = 8.dp, vertical = 0.dp)
                                        .drawBehind {
                                            drawCircle(
                                                color = Color(0xFFFFA833),
                                                radius = 50f,
                                            )
                                        }
                                        .padding(4.dp),
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.White,
                                )
                            }
                        }
                        Text(
                            text = "１佐々木　花子（旧姓：田中　花子）、２一郎、３二郎、４田中　祖父男",
                            modifier =
                            modifier
                                .padding(vertical = 2.dp),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1
                        )
                        var selected = mapContent.selectedTab
                        val list = listOf("基本情報", "付加情報", "転居情報", "建物周辺情報")

                        LazyRow(
                            modifier = modifier
                                .padding(vertical = 8.dp),
                        ) {
                            items(list) {
                                FilterRow(
                                    label = it,
                                    modifier = modifier,
                                    selected = selected,
                                    setSelected = { name ->
                                        selected = name
                                        when (name) {
                                            DetailTab.BasicInfo().name -> {
                                                onShowContent(
                                                    MapContent.Detail(
                                                        detailTab = DetailTab.BasicInfo(),
                                                        selectedTab = name
                                                    )
                                                )
                                            }

                                            DetailTab.AdditionalInfo().name -> {
                                                onShowContent(
                                                    MapContent.Detail(
                                                        detailTab = DetailTab.AdditionalInfo(),
                                                        selectedTab = name
                                                    )
                                                )
                                            }
                                        }
                                    },
                                )
                            }
                        }
                        HorizontalDivider(
                            modifier = modifier
                        )
                        if (mapContent.detailTab is DetailTab.BasicInfo) {
                            BasicInfoScreen(modifier)
                        }
                        if (mapContent.detailTab is DetailTab.AdditionalInfo) {
                            AdditionalInfoScreen(modifier)
                        }
                    }
                }
                showSheet()
            }

            null -> {
            }
        }

    }
}

@Composable
private fun BasicInfoScreen(modifier: Modifier) {
    Column(
        modifier =
        modifier
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier =
            modifier
                .padding(4.dp)
        ) {
            Text(
                text = "事業所",
                modifier =
                modifier
                    .weight(1f),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = "サンプルカブシキガイシャ",
                modifier =
                modifier
                    .weight(3.7f),
                style = MaterialTheme.typography.labelLarge,
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold,
            )
        }
        Row(
            modifier =
            modifier
                .padding(4.dp)
        ) {
            Text(
                text = "カナ名称",
                modifier =
                modifier
                    .weight(1f),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = "サンプル（カブ）",
                modifier =
                modifier
                    .weight(3.7f),
                style = MaterialTheme.typography.labelLarge,
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
    HorizontalDivider(
        modifier = modifier
    )
    Row(
        modifier =
        modifier
            .padding(4.dp, vertical = 8.dp)
    ) {
        Text(
            text = "住居様方",
            modifier =
            modifier
                .weight(1f),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = "佐々木様方　田中様",
            modifier =
            modifier
                .weight(3.7f),
            style = MaterialTheme.typography.labelLarge,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
        )
    }
    HorizontalDivider(
        modifier = modifier
    )
    Column(
        modifier =
        modifier
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier =
            modifier
                .padding(4.dp)
        ) {
            Text(
                text = "居住者",
                modifier =
                modifier
                    .weight(1f),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = "ササキ　タロウ、ハナコ（旧姓：タナカ）、",
                modifier =
                modifier
                    .weight(3.7f),
                style = MaterialTheme.typography.labelLarge,
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold,
            )
        }
        Row(
            modifier =
            modifier
                .padding(4.dp)
        ) {
            Text(
                text = "カナ名称",
                modifier =
                modifier
                    .weight(1f),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = "イチロウ、ジロウ、タナカ　ソフオ",
                modifier =
                modifier
                    .weight(3.7f),
                style = MaterialTheme.typography.labelLarge,
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
    HorizontalDivider(
        modifier = modifier
    )
    Column(modifier = modifier.padding(4.dp)) {
        Text(
            text = "一部転居",
            modifier =
            modifier
                .padding(top = 4.dp)
                .background(Color.Gray, RoundedCornerShape(6.dp))
                .padding(4.dp),
            style = MaterialTheme.typography.labelLarge,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = "1990/5/30〜1999/4/25",
            modifier =
            modifier,
            style = MaterialTheme.typography.labelLarge,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = "鈴木 一郎、幸子（旧姓：佐藤　幸子）",
            modifier =
            modifier,
            color = Color.Gray,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = "スズキ イチロウ、 コウジ（ 旧姓： サトウ　 コウジ）",
            modifier =
            modifier,
            color = Color.Gray,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold,
        )
    }
    HorizontalDivider(
        modifier = modifier
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(
            text = "配達順序：24",
            color = Color.Gray,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
        )

        Text(
            modifier = modifier
                .padding(4.dp),
            text = "家形に対する編集",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
        )
    }
    Button(
        modifier =
        modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors()
            .copy(containerColor = Color.Gray),
        onClick = { },
    ) {
        Text("原簿紐づけを変更する")
    }
    Button(
        modifier =
        modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors()
            .copy(containerColor = Color.Gray),
        onClick = { },
    ) {
        Text("更新家形を移動する")
    }
    Button(
        modifier =
        modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors()
            .copy(containerColor = Color.White),
        onClick = { },
    ) {
        Text(
            "原簿を非表示にする",
            color = Color.Black
        )
    }
}

@Composable
private fun AdditionalInfoScreen(modifier: Modifier) {
    Column(
        modifier =
        modifier
            .padding(vertical = 4.dp),
    ) {
        Row(
            modifier =
            modifier
                .wrapContentSize()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier =
                modifier
                    .wrapContentSize()
                    .align(Alignment.Top)
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Text(
                    text = "誤",
                    modifier =
                    modifier
                        .padding(horizontal = 8.dp, vertical = 0.dp)
                        .drawBehind {
                            drawCircle(
                                color = Color(0xFFF44336),
                                radius = 50f,
                            )
                        }
                        .padding(4.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                )
                Text(
                    text = "誤配",
                    modifier =
                    modifier
                        .padding(vertical = 4.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Column(
                modifier =
                modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 8.dp)
            ) {
                Box(
                    modifier =
                    modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "数人も佐々木さんで、誤配。",
                        modifier =
                        modifier
                            .align(Alignment.TopStart)
                            .padding(start = 32.dp, 4.dp, 4.dp, 4.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                }
                Box(
                    modifier =
                    modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "2024/12/31第一集配部xxx佐々木太郎",
                        modifier =
                        modifier
                            .align(Alignment.TopEnd)
                            .padding(4.dp),
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                }
                Box(
                    modifier =
                    modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "すべてみる＋",
                        modifier =
                        modifier
                            .align(Alignment.TopCenter)
                            .padding(4.dp),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }

        HorizontalDivider(
            modifier = modifier
        )
        Row(
            modifier =
            modifier
                .wrapContentSize()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier =
                modifier
                    .wrapContentSize()
                    .align(Alignment.Top)
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Text(
                    text = "注",
                    modifier =
                    modifier
                        .padding(horizontal = 8.dp, vertical = 0.dp)
                        .drawBehind {
                            drawCircle(
                                color = Color(0xFFFFA833),
                                radius = 50f,
                            )
                        }
                        .padding(4.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                )
                Text(
                    text = "誤配",
                    modifier =
                    modifier
                        .padding(vertical = 2.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Column(
                modifier =
                modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 8.dp),

                ) {
                Box(
                    modifier =
                    modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "数人も佐々木さんで、誤配。",
                        modifier =
                        modifier
                            .align(Alignment.TopStart)
                            .padding(start = 32.dp, 4.dp, 4.dp, 4.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                }
                Box(
                    modifier =
                    modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "2024/12/31第一集配部xxx佐々木太郎",
                        modifier =
                        modifier
                            .align(Alignment.TopEnd)
                            .padding(4.dp),
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                }
            }
        }
    }
    HorizontalDivider(
        modifier = modifier
    )
    Row(
        modifier =
        modifier
            .wrapContentSize()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "嘱託回送",
            modifier =
            modifier
                .padding(start = 16.dp, 4.dp, 4.dp, 4.dp),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )

        Text(
            text = "期間中",
            modifier = modifier
                .padding(horizontal = 16.dp)
                .background(Color.Gray, RoundedCornerShape(6.dp))
                .padding(6.dp),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )

        Column(
            modifier =
            modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 8.dp),

            ) {
            Box(
                modifier =
                modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "No.1234567",
                    modifier =
                    modifier
                        .align(Alignment.TopStart)
                        .padding(4.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
            }
            Box(
                modifier =
                modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "2022/12/1〜2023/12/1",
                    modifier =
                    modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
            }
        }
    }
    HorizontalDivider(
        modifier = modifier
    )

    Text(
        text = "付加情報に対する操作",
        modifier =
        modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.SemiBold,
    )

    Button(
        modifier =
        modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors()
            .copy(containerColor = Color.Gray),
        onClick = {},
    ) {
        Text("付加情報を追加する")
    }

    Button(
        modifier =
        modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors()
            .copy(containerColor = Color.Gray),
        onClick = {},
    ) {
        Text("付加情報を編集する")
    }

}

@Composable
fun FilterRow(
    label: String,
    modifier: Modifier,
    setSelected: (selected: String) -> Unit,
    selected: String,
) {
    val isSelected = selected == label
    FilterChip(
        modifier = modifier.padding(end = 8.dp),
        onClick = {
            setSelected(label)
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
        selected = isSelected,
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

