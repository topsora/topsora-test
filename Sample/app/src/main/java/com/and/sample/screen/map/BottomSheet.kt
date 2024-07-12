package com.and.sample.screen.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.and.sample.R
import com.and.sample.screen.login.ErrorView

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomFinalizedDemoScreen() {
    var isInitialState by rememberSaveable { mutableStateOf(true) }

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed,
//        defineValues = {
//            // Bottom sheet height is 100 dp.
////            BottomSheetValue.Collapsed at height(100.dp)
//            if (isInitialState) {
//                // Offset is 60% which means the bottom sheet takes 40% of the screen.
////                SheetValue.PartiallyExpanded at offset(percent = 60)
//            }
//            // Bottom sheet height is equal to the height of its content.
//            SheetValue.Expanded at contentHeight
//        }, confirmValueChange = {
//            if (isInitialState) {
//                isInitialState = false
//                refreshValues()
//            }
//            true
//        }
    )
    val scaffoldState = rememberBottomSheetScaffoldState(sheetState)

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "ロゴ",
                    modifier = Modifier.padding(top = 32.dp),
                    style = MaterialTheme.typography.displaySmall,
                )

                ErrorView("パスワードを6回連続で間違えたため、アカウントが一時的にロックされました。ロックを解除するためには管理者に解除申請を行ってください。")
            }
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.map),
                    contentDescription = stringResource(id = R.string.contentDescription)
                )
            }
//            val bottomPadding by remember {
//                derivedStateOf { sheetState.requireSheetVisibleHeightDp() }
//            }
//            val isBottomSheetMoving by remember {
//                derivedStateOf { sheetState.currentValue != sheetState.targetValue }
//            }
//            MapScreenContent(
//                bottomPadding = bottomPadding,
//                isBottomSheetMoving = isBottomSheetMoving,
//                layoutHeight = sheetState.layoutHeightDp
//            )
        },
        modifier = Modifier.fillMaxSize(),
    )
}