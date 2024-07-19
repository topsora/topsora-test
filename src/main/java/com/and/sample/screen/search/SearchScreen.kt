package com.and.sample.screen.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.and.sample.PasswordState
import com.and.sample.ui.common.core.FirstName
import com.and.sample.ui.common.core.PostOfficeTextBox
import com.and.sample.ui.common.core.SearchDropDown
import com.and.sample.ui.common.core.SearchMenuButton
import com.and.sample.ui.common.core.SearchTextField
import com.and.sample.ui.common.core.SearchTitle
import com.and.sample.ui.common.core.SearchTopAppBar
import com.and.sample.ui.theme.SampleTheme

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onSearch: () -> Unit,
) {
    val state = rememberScrollState()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            SearchTopAppBar("検索メニュー") {
                onBack()
            }
        },
        bottomBar = {
            Button(
                modifier =
                    modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp, start = 24.dp, end = 24.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = { onSearch() },
            ) {
                Text("検索する")
            }
        },
    ) { paddingValues ->

        val focusRequester = remember { FocusRequester() }
        val passwordState = remember { PasswordState() }

        var visible by remember { mutableStateOf(true) }

        Column(
            modifier =
                modifier
                    .padding(paddingValues),
        ) {
            var firstName by rememberSaveable { mutableStateOf("") }
            var lastName by rememberSaveable { mutableStateOf("") }
            var condoName by rememberSaveable { mutableStateOf("") }

            Row(
                modifier =
                    modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                SearchMenuButton(
                    text = "原簿検索",
                    modifier =
                        modifier
                            .wrapContentSize()
                            .weight(1f),
                ) {
                    visible = !visible
                }
                SearchMenuButton(
                    text = "スポット検索",
                    modifier =
                        modifier
                            .wrapContentSize()
                            .weight(1f),
                ) {
                    visible = !visible
                }
            }
            if (visible) {
                Column(
                    modifier =
                        modifier
                            .verticalScroll(state),
                ) {
                    SearchTitle(
                        text = "氏名、事業所名",
                        modifier =
                            modifier
                                .wrapContentSize()
                                .padding(horizontal = 16.dp, vertical = 4.dp),
                    )
                    Row(
                        modifier =
                            modifier
                                .padding(horizontal = 16.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "氏名",
                            modifier =
                                modifier
                                    .fillMaxWidth()
                                    .padding(start = 24.dp)
                                    .weight(1f),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                        )
                        Box(
                            modifier =
                                modifier
                                    .weight(1f),
                        ) {
                            SearchTextField(
                                placeholder = "性",
                                value = firstName,
                            ) {
                                firstName = it
                            }
                        }
                        Box(
                            modifier =
                                modifier
                                    .weight(1f),
                        ) {
                            SearchTextField(
                                placeholder = "名",
                                value = lastName,
                            ) {
                                lastName = it
                            }
                        }
                    }
                    HorizontalDivider(
                        thickness = 1.dp,
                        modifier =
                            modifier
                                .padding(
                                    start = 38.dp,
                                    top = 4.dp,
                                    bottom = 4.dp,
                                    end = 16.dp,
                                ),
                    )
                    Row(
                        modifier =
                            modifier
                                .padding(horizontal = 16.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "マンション／\nビル名",
                            modifier =
                                modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                        )
                        Box(
                            modifier =
                                modifier
                                    .fillMaxWidth()
                                    .weight(1.6f),
                        ) {
                            SearchTextField(
                                placeholder = "入力してください",
                                modifier = modifier.fillMaxWidth(),
                                value = condoName,
                            ) {
                                condoName = it
                            }
                        }
                    }
                    HorizontalDivider(
                        thickness = 1.dp,
                        modifier =
                            modifier
                                .padding(
                                    start = 16.dp,
                                    top = 8.dp,
                                    bottom = 8.dp,
                                    end = 16.dp,
                                ),
                    )
                    SearchTitle(
                        text = "住所または郵便番号",
                        modifier =
                            modifier
                                .wrapContentSize()
                                .padding(horizontal = 16.dp, vertical = 4.dp),
                    )
                    Card(
                        modifier =
                            Modifier
                                .padding(horizontal = 16.dp, vertical = 4.dp)
                                .clip(RoundedCornerShape(12.dp)),
                        colors =
                            CardDefaults.cardColors(
                                containerColor = Color.White,
                            ),
                        border = BorderStroke(1.dp, Color.Gray),
                    ) {
                        Row(
                            modifier =
                                modifier
                                    .padding(horizontal = 8.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "住所",
                                modifier =
                                    modifier
                                        .wrapContentSize(),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Column(
                                modifier =
                                    modifier
                                        .fillMaxWidth()
                                        .padding(start = 8.dp),
                            ) {
                                Row(
                                    modifier =
                                        modifier
                                            .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = "都道府県",
                                        modifier =
                                            modifier
                                                .fillMaxWidth()
                                                .weight(1.5f),
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Gray,
                                    )
                                    Box(
                                        modifier =
                                            modifier
                                                .fillMaxWidth()
                                                .weight(2.5f)
                                                .padding(end = 16.dp),
                                    ) {
                                        SearchDropDown(
                                            modifier = modifier.wrapContentSize(),
                                            placeholder = "東京都",
                                        ) {
                                            println("asdf SearchDropDown on value change - $it")
                                        }
                                    }
                                }
                                Row(
                                    modifier = modifier,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = "市区都町村",
                                        modifier =
                                            modifier
                                                .fillMaxWidth()
                                                .weight(1.5f),
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Gray,
                                    )
                                    Box(
                                        modifier =
                                            modifier
                                                .fillMaxWidth()
                                                .weight(2.5f),
                                    ) {
                                        SearchDropDown(
                                            modifier =
                                                modifier
                                                    .wrapContentSize(),
                                            placeholder = "港区",
                                        ) {
                                            println("asdf SearchDropDown on value change - $it")
                                        }
                                    }
                                }
                                Row(
                                    modifier = modifier,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = "大字町名小字",
                                        modifier =
                                            modifier
                                                .fillMaxWidth()
                                                .weight(1.5f),
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Gray,
                                    )
                                    Box(
                                        modifier =
                                            modifier
                                                .fillMaxWidth()
                                                .weight(2.5f),
                                    ) {
                                        SearchDropDown(
                                            modifier = modifier.wrapContentSize(),
                                            placeholder = "赤坂",
                                        ) {
                                            println("asdf SearchDropDown on value change - $it")
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "郵便\n番号",
                            modifier =
                                modifier
                                    .wrapContentSize()
                                    .padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                        )

                        Text(
                            text = "郵便番号",
                            modifier =
                                modifier
                                    .wrapContentSize()
                                    .padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                        )

                        SearchTextField(
                            placeholder = "入力してください",
                            modifier = modifier.fillMaxWidth(),
                            value = condoName,
                        ) {
                            condoName = it
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier =
                            modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                    ) {
                        Row(
                            modifier = modifier.weight(1f),
                        ) {
                            Text(text = "", modifier.fillMaxWidth())
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier =
                                modifier
                                    .weight(1f)
                                    .fillMaxWidth(),
                        ) {
                            Box(
                                modifier =
                                    modifier
                                        .fillMaxWidth()
                                        .weight(1.5f),
                            ) {
                                SearchDropDown(
                                    modifier = modifier.wrapContentSize(),
                                    placeholder = "",
                                ) {
                                    println("asdf SearchDropDown on value change - $it")
                                }
                            }
                            Text(
                                text = "丁目\n（地番）",
                                modifier =
                                    modifier
                                        .weight(1f),
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                color = Color.Gray,
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.weight(1f),
                        ) {
                            Box(
                                modifier =
                                    modifier
                                        .fillMaxWidth()
                                        .weight(1.5f),
                            ) {
                                SearchDropDown(
                                    modifier = modifier.wrapContentSize(),
                                    placeholder = "",
                                ) {
                                    println("asdf SearchDropDown on value change - $it")
                                }
                            }
                            Text(
                                text = "番\n（枝番）",
                                modifier =
                                    modifier
                                        .weight(1f),
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                color = Color.Gray,
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier.padding(8.dp),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.weight(1f),
                        ) {
                            Text(text = "", modifier.fillMaxWidth())
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.weight(1f),
                        ) {
                            Box(
                                modifier =
                                    modifier
                                        .fillMaxWidth()
                                        .weight(1.5f),
                            ) {
                                SearchDropDown(
                                    modifier = modifier.wrapContentSize(),
                                    placeholder = "",
                                ) {
                                    println("asdf SearchDropDown on value change - $it")
                                }
                            }
                            Text(
                                text = "号\n（孫番）",
                                modifier =
                                    modifier
                                        .weight(1f),
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                color = Color.Gray,
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.weight(1f),
                        ) {
                            Text(text = "", modifier.fillMaxWidth())
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier.padding(8.dp),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.weight(1f),
                        ) {
                            Text(
                                text = "作業所名",
                                modifier
                                    .fillMaxWidth()
                                    .padding(end = 8.dp),
                                textAlign = TextAlign.End,
                                color = Color.Gray,
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.weight(2.3f),
                        ) {
                            Box(
                                modifier =
                                    modifier
                                        .fillMaxWidth()
                                        .weight(1.6f),
                            ) {
                                SearchTextField(
                                    placeholder = "入力してください",
                                    modifier = modifier.fillMaxWidth(),
                                    value = condoName,
                                ) {
                                    condoName = it
                                }
                            }
                        }
                    }
                }
            } else {
                Column(
                    modifier =
                        modifier
                            .verticalScroll(state),
                ) {
                    SearchTitle(
                        text = "検索キーワード",
                        modifier =
                            modifier
                                .wrapContentSize()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                    )
                    Text(
                        text = "スポット名",
                        modifier =
                            modifier
                                .padding(16.dp)
                                .wrapContentSize(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray,
                    )
                    FirstName(
                        "入力してください",
                        passwordState = passwordState,
                        modifier =
                            modifier
                                .focusRequester(focusRequester)
                                .padding(start = 16.dp, end = 16.dp),
                        onImeAction = { },
                    )
                    HorizontalDivider(
                        thickness = 1.dp,
                        modifier =
                            modifier
                                .padding(
                                    16.dp,
                                ),
                    )
                    Text(
                        text = "検索案件",
                        modifier =
                            modifier
                                .padding(start = 16.dp)
                                .wrapContentSize(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray,
                    )
                    listOf("郵便局", "郵便差出箱", "建物・テナント", "交差点", "施設").forEach {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier =
                                Modifier
                                    .padding(horizontal = 24.dp),
                        ) {
                            Checkbox(
                                checked = false,
                                onCheckedChange = { },
                            )
                            Text(
                                text = it,
                                modifier =
                                    Modifier
                                        .padding(4.dp)
                                        .wrapContentSize(),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray,
                            )
                        }
                    }

                    val model2 = ArrayList<Model2>()
                    model2.add(Model2("交通", "ガソリンスタンド"))
                    model2.add(Model2("公共", "文化"))
                    model2.add(Model2("教育", "医療"))
                    model2.add(Model2("レジャー", "宿泊"))
                    model2.add(Model2("金融", "商業"))
                    model2.add(Model2("コンビニ", "ファーストフード"))
                    model2.add(Model2("ファミレス", "コーヒーショップ"))
                    model2
                        .forEach {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 52.dp),
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f),
                                ) {
                                    Checkbox(
                                        checked = false,
                                        onCheckedChange = { },
                                    )
                                    Text(
                                        text = it.a,
                                        modifier =
                                            Modifier
                                                .padding(2.dp)
                                                .wrapContentSize(),
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Gray,
                                    )
                                }

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f),
                                ) {
                                    Checkbox(
                                        checked = false,
                                        onCheckedChange = { },
                                    )
                                    Text(
                                        text = it.b,
                                        modifier =
                                            Modifier
                                                .padding(4.dp)
                                                .wrapContentSize(),
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Gray,
                                    )
                                }
                            }
                        }
                    HorizontalDivider(
                        thickness = 1.dp,
                        modifier =
                            modifier
                                .padding(
                                    start = 32.dp,
                                    top = 8.dp,
                                    bottom = 8.dp,
                                    end = 16.dp,
                                ),
                    )

                    Text(
                        text = "検索範囲",
                        modifier =
                            modifier
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .wrapContentSize(),
                        style = MaterialTheme.typography.bodyLarge,
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier,
                    ) {
                        Text(
                            text = "現在地から半径",
                            modifier =
                                modifier
                                    .padding(start = 16.dp)
                                    .wrapContentSize()
                                    .weight(1f),
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Gray,
                        )
                        Box(
                            modifier =
                                modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                        ) {
                            SearchDropDown(
                                modifier =
                                    modifier
                                        .wrapContentSize(),
                                placeholder = "1,000m",
                            ) {
                                println("asdf SearchDropDown on value change - $it")
                            }
                        }
//                        DropdownList(
//                            modifier = modifier
//                                .weight(1f)
//                                .padding(end = 42.dp), placeholder = "1,000m"
//                        )
                    }
                    HorizontalDivider(
                        thickness = 1.dp,
                        modifier =
                            modifier
                                .padding(
                                    start = 32.dp,
                                    top = 8.dp,
                                    bottom = 8.dp,
                                    end = 16.dp,
                                ),
                    )
                    Text(
                        text = "郵便番号",
                        modifier =
                            modifier
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .wrapContentSize(),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    PostOfficeTextBox(
                        "郵便番号をハイフン抜きで入力してください",
                        passwordState = passwordState,
                        modifier =
                            modifier
                                .focusRequester(focusRequester)
                                .padding(start = 16.dp, end = 16.dp),
                        onImeAction = { },
                    )
                }
            }
        }
    }
}

@Composable
fun SearchScreen2(
    modifier: Modifier = Modifier,
    onSearch: () -> Unit,
) {
    val state = rememberScrollState()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            Button(
                modifier =
                    modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp, start = 24.dp, end = 24.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = { onSearch() },
            ) {
                Text("検索する")
            }
        },
    ) { paddingValues ->

        val focusRequester = remember { FocusRequester() }
        val passwordState = remember { PasswordState() }

        var visible by remember { mutableStateOf(true) }

        Column(
            modifier =
                modifier
                    .padding(paddingValues),
        ) {
            var firstName by rememberSaveable { mutableStateOf("") }
            var lastName by rememberSaveable { mutableStateOf("") }
            var condoName by rememberSaveable { mutableStateOf("") }

            Row(
                modifier =
                    modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                SearchMenuButton(
                    text = "原簿検索",
                    modifier =
                        modifier
                            .wrapContentSize()
                            .weight(1f),
                ) {
                    visible = !visible
                }
                SearchMenuButton(
                    text = "スポット検索",
                    modifier =
                        modifier
                            .wrapContentSize()
                            .weight(1f),
                ) {
                    visible = !visible
                }
            }
            if (visible) {
                Column(
                    modifier =
                        modifier
                            .verticalScroll(state),
                ) {
                    SearchTitle(
                        text = "氏名、事業所名",
                        modifier =
                            modifier
                                .wrapContentSize()
                                .padding(horizontal = 16.dp, vertical = 4.dp),
                    )
                    Row(
                        modifier =
                            modifier
                                .padding(horizontal = 16.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "氏名",
                            modifier =
                                modifier
                                    .fillMaxWidth()
                                    .padding(start = 24.dp)
                                    .weight(1f),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                        )
                        Box(
                            modifier =
                                modifier
                                    .weight(1f),
                        ) {
                            SearchTextField(
                                placeholder = "性",
                                value = firstName,
                            ) {
                                firstName = it
                            }
                        }
                        Box(
                            modifier =
                                modifier
                                    .weight(1f),
                        ) {
                            SearchTextField(
                                placeholder = "名",
                                value = lastName,
                            ) {
                                lastName = it
                            }
                        }
                    }
                    HorizontalDivider(
                        thickness = 1.dp,
                        modifier =
                            modifier
                                .padding(
                                    start = 38.dp,
                                    top = 4.dp,
                                    bottom = 4.dp,
                                    end = 16.dp,
                                ),
                    )
                    Row(
                        modifier =
                            modifier
                                .padding(horizontal = 16.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "マンション／\nビル名",
                            modifier =
                                modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                        )
                        Box(
                            modifier =
                                modifier
                                    .fillMaxWidth()
                                    .weight(1.6f),
                        ) {
                            SearchTextField(
                                placeholder = "入力してください",
                                modifier = modifier.fillMaxWidth(),
                                value = condoName,
                            ) {
                                condoName = it
                            }
                        }
                    }
                    HorizontalDivider(
                        thickness = 1.dp,
                        modifier =
                            modifier
                                .padding(
                                    start = 16.dp,
                                    top = 8.dp,
                                    bottom = 8.dp,
                                    end = 16.dp,
                                ),
                    )
                    SearchTitle(
                        text = "住所または郵便番号",
                        modifier =
                            modifier
                                .wrapContentSize()
                                .padding(horizontal = 16.dp, vertical = 4.dp),
                    )
                    Card(
                        modifier =
                            Modifier
                                .padding(horizontal = 16.dp, vertical = 4.dp)
                                .clip(RoundedCornerShape(12.dp)),
                        colors =
                            CardDefaults.cardColors(
                                containerColor = Color.White,
                            ),
                        border = BorderStroke(1.dp, Color.Gray),
                    ) {
                        Row(
                            modifier =
                                modifier
                                    .padding(horizontal = 8.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "住所",
                                modifier =
                                    modifier
                                        .wrapContentSize(),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Column(
                                modifier =
                                    modifier
                                        .fillMaxWidth()
                                        .padding(start = 8.dp),
                            ) {
                                Row(
                                    modifier =
                                        modifier
                                            .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = "都道府県",
                                        modifier =
                                            modifier
                                                .fillMaxWidth()
                                                .weight(1.5f),
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Gray,
                                    )
                                    Box(
                                        modifier =
                                            modifier
                                                .fillMaxWidth()
                                                .weight(2.5f)
                                                .padding(end = 16.dp),
                                    ) {
                                        SearchDropDown(
                                            modifier = modifier.wrapContentSize(),
                                            placeholder = "東京都",
                                        ) {
                                            println("asdf SearchDropDown on value change - $it")
                                        }
                                    }
                                }
                                Row(
                                    modifier = modifier,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = "市区都町村",
                                        modifier =
                                            modifier
                                                .fillMaxWidth()
                                                .weight(1.5f),
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Gray,
                                    )
                                    Box(
                                        modifier =
                                            modifier
                                                .fillMaxWidth()
                                                .weight(2.5f),
                                    ) {
                                        SearchDropDown(
                                            modifier =
                                                modifier
                                                    .wrapContentSize(),
                                            placeholder = "港区",
                                        ) {
                                            println("asdf SearchDropDown on value change - $it")
                                        }
                                    }
                                }
                                Row(
                                    modifier = modifier,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = "大字町名小字",
                                        modifier =
                                            modifier
                                                .fillMaxWidth()
                                                .weight(1.5f),
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Gray,
                                    )
                                    Box(
                                        modifier =
                                            modifier
                                                .fillMaxWidth()
                                                .weight(2.5f),
                                    ) {
                                        SearchDropDown(
                                            modifier = modifier.wrapContentSize(),
                                            placeholder = "赤坂",
                                        ) {
                                            println("asdf SearchDropDown on value change - $it")
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "郵便\n番号",
                            modifier =
                                modifier
                                    .wrapContentSize()
                                    .padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                        )

                        Text(
                            text = "郵便番号",
                            modifier =
                                modifier
                                    .wrapContentSize()
                                    .padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                        )

                        SearchTextField(
                            placeholder = "入力してください",
                            modifier = modifier.fillMaxWidth(),
                            value = condoName,
                        ) {
                            condoName = it
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier =
                            modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                    ) {
                        Row(
                            modifier = modifier.weight(1f),
                        ) {
                            Text(text = "", modifier.fillMaxWidth())
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier =
                                modifier
                                    .weight(1f)
                                    .fillMaxWidth(),
                        ) {
                            Box(
                                modifier =
                                    modifier
                                        .fillMaxWidth()
                                        .weight(1.5f),
                            ) {
                                SearchDropDown(
                                    modifier = modifier.wrapContentSize(),
                                    placeholder = "",
                                ) {
                                    println("asdf SearchDropDown on value change - $it")
                                }
                            }
                            Text(
                                text = "丁目\n（地番）",
                                modifier =
                                    modifier
                                        .weight(1f),
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                color = Color.Gray,
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.weight(1f),
                        ) {
                            Box(
                                modifier =
                                    modifier
                                        .fillMaxWidth()
                                        .weight(1.5f),
                            ) {
                                SearchDropDown(
                                    modifier = modifier.wrapContentSize(),
                                    placeholder = "",
                                ) {
                                    println("asdf SearchDropDown on value change - $it")
                                }
                            }
                            Text(
                                text = "番\n（枝番）",
                                modifier =
                                    modifier
                                        .weight(1f),
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                color = Color.Gray,
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier.padding(8.dp),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.weight(1f),
                        ) {
                            Text(text = "", modifier.fillMaxWidth())
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.weight(1f),
                        ) {
                            Box(
                                modifier =
                                    modifier
                                        .fillMaxWidth()
                                        .weight(1.5f),
                            ) {
                                SearchDropDown(
                                    modifier = modifier.wrapContentSize(),
                                    placeholder = "",
                                ) {
                                    println("asdf SearchDropDown on value change - $it")
                                }
                            }
                            Text(
                                text = "号\n（孫番）",
                                modifier =
                                    modifier
                                        .weight(1f),
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                color = Color.Gray,
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.weight(1f),
                        ) {
                            Text(text = "", modifier.fillMaxWidth())
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier.padding(8.dp),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.weight(1f),
                        ) {
                            Text(
                                text = "作業所名",
                                modifier
                                    .fillMaxWidth()
                                    .padding(end = 8.dp),
                                textAlign = TextAlign.End,
                                color = Color.Gray,
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.weight(2.3f),
                        ) {
                            Box(
                                modifier =
                                    modifier
                                        .fillMaxWidth()
                                        .weight(1.6f),
                            ) {
                                SearchTextField(
                                    placeholder = "入力してください",
                                    modifier = modifier.fillMaxWidth(),
                                    value = condoName,
                                ) {
                                    condoName = it
                                }
                            }
                        }
                    }
                }
            } else {
                Column(
                    modifier =
                        modifier
                            .verticalScroll(state),
                ) {
                    SearchTitle(
                        text = "検索キーワード",
                        modifier =
                            modifier
                                .wrapContentSize()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                    )
                    Text(
                        text = "スポット名",
                        modifier =
                            modifier
                                .padding(16.dp)
                                .wrapContentSize(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray,
                    )
                    FirstName(
                        "入力してください",
                        passwordState = passwordState,
                        modifier =
                            modifier
                                .focusRequester(focusRequester)
                                .padding(start = 16.dp, end = 16.dp),
                        onImeAction = { },
                    )
                    HorizontalDivider(
                        thickness = 1.dp,
                        modifier =
                            modifier
                                .padding(
                                    16.dp,
                                ),
                    )
                    Text(
                        text = "検索案件",
                        modifier =
                            modifier
                                .padding(start = 16.dp)
                                .wrapContentSize(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray,
                    )
                    listOf("郵便局", "郵便差出箱", "建物・テナント", "交差点", "施設").forEach {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier =
                                Modifier
                                    .padding(horizontal = 24.dp),
                        ) {
                            Checkbox(
                                checked = false,
                                onCheckedChange = { },
                            )
                            Text(
                                text = it,
                                modifier =
                                    Modifier
                                        .padding(4.dp)
                                        .wrapContentSize(),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray,
                            )
                        }
                    }

                    val model2 = ArrayList<Model2>()
                    model2.add(Model2("交通", "ガソリンスタンド"))
                    model2.add(Model2("公共", "文化"))
                    model2.add(Model2("教育", "医療"))
                    model2.add(Model2("レジャー", "宿泊"))
                    model2.add(Model2("金融", "商業"))
                    model2.add(Model2("コンビニ", "ファーストフード"))
                    model2.add(Model2("ファミレス", "コーヒーショップ"))
                    model2
                        .forEach {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 52.dp),
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f),
                                ) {
                                    Checkbox(
                                        checked = false,
                                        onCheckedChange = { },
                                    )
                                    Text(
                                        text = it.a,
                                        modifier =
                                            Modifier
                                                .padding(2.dp)
                                                .wrapContentSize(),
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Gray,
                                    )
                                }

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f),
                                ) {
                                    Checkbox(
                                        checked = false,
                                        onCheckedChange = { },
                                    )
                                    Text(
                                        text = it.b,
                                        modifier =
                                            Modifier
                                                .padding(4.dp)
                                                .wrapContentSize(),
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Gray,
                                    )
                                }
                            }
                        }
                    HorizontalDivider(
                        thickness = 1.dp,
                        modifier =
                            modifier
                                .padding(
                                    start = 32.dp,
                                    top = 8.dp,
                                    bottom = 8.dp,
                                    end = 16.dp,
                                ),
                    )

                    Text(
                        text = "検索範囲",
                        modifier =
                            modifier
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .wrapContentSize(),
                        style = MaterialTheme.typography.bodyLarge,
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier,
                    ) {
                        Text(
                            text = "現在地から半径",
                            modifier =
                                modifier
                                    .padding(start = 16.dp)
                                    .wrapContentSize()
                                    .weight(1f),
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Gray,
                        )
                        Box(
                            modifier =
                                modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                        ) {
                            SearchDropDown(
                                modifier =
                                    modifier
                                        .wrapContentSize(),
                                placeholder = "1,000m",
                            ) {
                                println("asdf SearchDropDown on value change - $it")
                            }
                        }
//                        DropdownList(
//                            modifier = modifier
//                                .weight(1f)
//                                .padding(end = 42.dp), placeholder = "1,000m"
//                        )
                    }
                    HorizontalDivider(
                        thickness = 1.dp,
                        modifier =
                            modifier
                                .padding(
                                    start = 32.dp,
                                    top = 8.dp,
                                    bottom = 8.dp,
                                    end = 16.dp,
                                ),
                    )
                    Text(
                        text = "郵便番号",
                        modifier =
                            modifier
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .wrapContentSize(),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    PostOfficeTextBox(
                        "郵便番号をハイフン抜きで入力してください",
                        passwordState = passwordState,
                        modifier =
                            modifier
                                .focusRequester(focusRequester)
                                .padding(start = 16.dp, end = 16.dp),
                        onImeAction = { },
                    )
                }
            }
        }
    }
}

data class Model2(
    val a: String,
    val b: String,
)

// delete later
@Composable
fun BottomOutlineTextField(
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
//    var isError by remember { mutableStateOf(false) }
    Box(
        modifier =
            Modifier
                .wrapContentSize(),
    ) {
        Card(
            modifier =
                Modifier
                    .wrapContentSize()
                    .padding(8.dp),
            colors =
                CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
            shape = MaterialTheme.shapes.small,
            border =
//            if (isError) BorderStroke(
//                1.dp,
//                MaterialTheme.colorScheme.primary
//            ) else
                BorderStroke(1.dp, Color.Gray),
        ) {
            BasicTextField(
                modifier =
                    Modifier
                        .wrapContentSize()
//                    .onFocusChanged { focusState ->
//                        isError = focusState.isFocused
//                    }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                value = value,
                onValueChange = {
                    onValueChange(it)
                },
                textStyle = MaterialTheme.typography.bodySmall,
                singleLine = true,
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.wrapContentSize(),
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                color = Color.Gray,
                                style = MaterialTheme.typography.bodySmall,
                            )
                        }
                    }
                    innerTextField()
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SampleTheme {
        SearchScreen(onBack = {}, onSearch = {})
    }
}
