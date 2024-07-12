package com.and.sample.screen.selectpostoffice

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.and.sample.screen.login.FilledButtonExample

data class PostOfficeModel(val name: String, val city: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectPostOfficeScreen(
    onBack: () -> Unit,
    onNext: () -> Unit,
) {
    val postOfficeModels = mutableListOf<PostOfficeModel>()
    postOfficeModels.add(PostOfficeModel("銀座郵便局", "東京都"))
    postOfficeModels.add(PostOfficeModel("青山郵便局", "東京都"))
    postOfficeModels.add(PostOfficeModel("日本橋郵便局", "東京都"))
    postOfficeModels.add(PostOfficeModel("赤坂郵便局", "東京都"))
    postOfficeModels.add(PostOfficeModel("新宿郵便局", "東京都"))

    val (selected, setSelected) = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "郵便局選択", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.Filled.ArrowBackIosNew, "")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        },
        content = { contentPadding ->
            Column(modifier = Modifier.padding(contentPadding)) {
                Text(
                    text = "利用する郵便局を選択してください。",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                )
                PostOfficeRows(
                    mItems = postOfficeModels,
                    selected,
                    setSelected
                )
//                Debug
//                Text(
//                    text = "Selected Option: $selected",
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier.fillMaxWidth(),
//                )
            }
        },
        bottomBar = {
            FilledButtonExample {
                onNext()
            }
        }
    )


}

@Composable
fun PostOfficeRows(
    mItems: List<PostOfficeModel>,
    selected: String,
    setSelected: (selected: String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(mItems) { model ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(
                        RoundedCornerShape(
                            8.dp
                        )
                    )
                    .clickable { setSelected(model.name) },
                border = BorderStroke(2.dp, Color.Gray),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp)

                ) {
                    val isSelected = selected == model.name
                    RadioButton(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .weight(1f),
                        selected = isSelected,
                        onClick = { setSelected(model.name) },
                        enabled = true,
                    )
                    Text(
                        text = model.name,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(5f),
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                    )
                    Text(
                        text = model.city, modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(2f),
                        color = Color.Gray
                    )
                }
            }
        }
    }
}