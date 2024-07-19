package com.and.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.and.sample.ui.theme.SampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleTheme {
                NavGraph()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartialBottomSheet() {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState =
        rememberModalBottomSheetState(
            skipPartiallyExpanded = false,
        )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
        },
        bottomBar = {
            Button(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp, start = 24.dp, end = 24.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    showBottomSheet = true
                },
            ) {
                Text("検索する")
            }
        },
    ) { paddingValues ->
        if (showBottomSheet) {
            ModalBottomSheet(
                modifier =
                    Modifier
                        .fillMaxHeight()
                        .padding(paddingValues),
                sheetState = sheetState,
                onDismissRequest = { showBottomSheet = false },
            ) {
                Text(
                    "Swipe up to open sheet. Swipe down to dismiss.",
                    modifier = Modifier.padding(16.dp),
                )
            }
        }
    }
//    var showBottomSheet by remember { mutableStateOf(false) }
//    val sheetState = rememberModalBottomSheetState(
//        skipPartiallyExpanded = false,
//    )
//
//    Column(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        Button(
//            onClick = { showBottomSheet = true }
//        ) {
//            Text("Display partial bottom sheet")
//        }
//
//        if (showBottomSheet) {
//            ModalBottomSheet(
//                modifier = Modifier.fillMaxHeight(),
//                sheetState = sheetState,
//                onDismissRequest = { showBottomSheet = false }
//            ) {
//                Text(
//                    "Swipe up to open sheet. Swipe down to dismiss.",
//                    modifier = Modifier.padding(16.dp)
//                )
//            }
//        }
//    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

// @Preview(showBackground = true)
// @Composable
// fun GreetingPreview() {
//    SampleTheme {
//    }
// }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomHalfSheet(
    name: String = "ここに家形を遷移しますか？",
    showDialog: Boolean,
    onDismiss: () -> Unit,
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            content = {
                Card {
                    Column(
                        modifier =
                            Modifier
                                .wrapContentSize()
                                .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            modifier =
                                Modifier
                                    .wrapContentSize()
                                    .padding(vertical = 24.dp),
                            text = name,
                        )
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            onClick = { },
                        ) {
                            Text("はい")
                        }
                        OutlinedButton(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            onClick = { },
                        ) {
                            Text("いいえ")
                        }
                    }
                }
            },
        )
    }
}

@Composable
fun SetupSnackBar(snackState: SnackbarHostState) {
    val modifier = Modifier.wrapContentSize()
    Box(
        modifier = modifier,
    ) {
        SnackbarHost(
            hostState = snackState,
            modifier = modifier,
        ) { snackbarData: SnackbarData ->
            if (snackbarData.visuals.withDismissAction) {
                CenterSnackBar(snackbarData.visuals.message)
            } else {
                CustomSnackBar(snackbarData.visuals.message)
            }
        }
    }
}

@Composable
fun CustomSnackBar(message: String) {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(8.dp, 72.dp),
    ) {
        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .alpha(0.5f)
                    .clip(
                        RoundedCornerShape(8.dp),
                    ),
            colors =
                CardDefaults.cardColors(
                    containerColor = Color.Black,
                ),
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = message,
                color = Color.White,
            )
        }
    }
}

@Composable
fun CenterSnackBar(message: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier =
                Modifier
                    .padding(64.dp)
                    .align(Alignment.Center)
                    .alpha(0.5f)
                    .clip(
                        RoundedCornerShape(8.dp),
                    ),
            colors =
                CardDefaults.cardColors(
                    containerColor = Color.Black,
                ),
        ) {
            Column(
                Modifier.padding(horizontal = 32.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier =
                        Modifier
                            .padding(16.dp)
                            .defaultMinSize(72.dp, 72.dp),
                    imageVector = Icons.Filled.CheckCircleOutline,
                    colorFilter = ColorFilter.tint(Color.White),
                    contentDescription = stringResource(id = R.string.contentDescription),
                )
                Text(
                    text = message,
                    color = Color.White,
                )
            }
        }
    }
}
