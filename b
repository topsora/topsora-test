import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.and.sample.R
import com.and.sample.ui.common.core.SearchTextField

@Composable
fun TestScreen(viewModel: TestViewModel = hiltViewModel()) {

//    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val flowColor by viewModel.color.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Red)
            .background(Color(flowColor))
            .clickable { viewModel.generateNewColor() }

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen2(modifier: Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "付加情報の編集", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.ArrowBackIosNew, "")
                    }
                },
                modifier =
                Modifier
                    .fillMaxWidth(),
            )
        },
        content = { contentPadding ->
            Column(modifier = Modifier.padding(contentPadding)) {
                Text(
                    text = "この原簿に対する付加情報を編集します。",
                    textAlign = TextAlign.Center,
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp, bottom = 16.dp),
                )
                Card(
                    modifier =
                    modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    colors =
                    CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer,
                    ),
                    shape = RoundedCornerShape(8.dp),
                ) {

                    Text(
                        text = "〒012-0011　東京都新宿区3-5-4",
                        modifier =
                        modifier
                            .padding(vertical = 8.dp, horizontal = 16.dp),
                        style = MaterialTheme.typography.titleSmall,
                        color = colorResource(id = R.color.gray),
                        maxLines = 1
                    )

                    Text(
                        text = "佐々木　太郎",
                        modifier =
                        modifier
                            .padding(vertical = 4.dp, horizontal = 16.dp),
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1
                    )

                    Text(
                        text = "佐々木　花子（旧姓：田中　花子）、一郎、二郎、田中　祖父男",
                        modifier =
                        modifier
                            .padding(vertical = 4.dp, horizontal = 16.dp),
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1
                    )
                    Spacer(modifier = modifier.height(8.dp))
                }
                Row(
                    modifier =
                    modifier
                        .wrapContentSize()
                        .padding(vertical = 4.dp, horizontal = 16.dp),
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
                                .padding(vertical = 2.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                    var condoName by rememberSaveable { mutableStateOf("") }
                    SearchTextField(
                        placeholder = "フリーコメントが入ります。フリーコメントが入ります。",
                        modifier = modifier.width(320.dp),
                        value = condoName,
                        containerColor = Color.Transparent,
                        onValueChange = { condoName = it },
                    )
                    IconButton(onClick = { }) {
                        Icon(Icons.Outlined.Delete, "")
                    }
                }

                HorizontalDivider(
                    modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )

                Row(
                    modifier =
                    modifier
                        .wrapContentSize()
                        .padding(vertical = 4.dp, horizontal = 16.dp),
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
                            text = "注意",
                            modifier =
                            modifier
                                .padding(vertical = 2.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                    var condoName by rememberSaveable { mutableStateOf("") }
                    SearchTextField(
                        placeholder = "フリーコメントが入ります。フリーコメントが入ります。",
                        modifier = modifier.width(320.dp),
                        value = condoName,
                        containerColor = Color.Transparent,
                        onValueChange = { condoName = it },
                    )
                    IconButton(onClick = { }) {
                        Icon(Icons.Outlined.Delete, "")
                    }
                }

                HorizontalDivider(
                    modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        },
        bottomBar = {
            Button(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp, start = 24.dp, end = 24.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = { },
                colors = ButtonDefaults.buttonColors().copy(containerColor = Color.Transparent),
            ) {
                Text(
                    text = "キャンセル",
                    textDecoration = TextDecoration.Underline,
                    color = colorResource(id = R.color.gray)
                )
            }
        },
    )
}
