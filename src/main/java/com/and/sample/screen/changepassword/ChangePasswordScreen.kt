package com.and.sample.screen.changepassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.and.sample.PasswordState
import com.and.sample.screen.login.ErrorView
import com.and.sample.screen.login.Password
import com.and.sample.ui.theme.SampleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen(
    onBack: () -> Unit,
    onNext: () -> Unit,
) {
    Scaffold(
        scaffoldState = rememberScaffoldState(),
        topBar = {
            CenterAlignedTopAppBar(

                title = { Text(text = "パスワード変更", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.Filled.ArrowBackIosNew, "")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TopAppBarColors(
                    containerColor = Color.White,
                    scrolledContainerColor = Color.White,
                    navigationIconContentColor = Color.Black,
                    titleContentColor = Color.Black,
                    actionIconContentColor = Color.Black
                )
            )
        },
        bottomBar = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp, start = 24.dp, end = 24.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    onNext()
                }) {
                Text("変更する")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues).padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            val focusRequester = remember { FocusRequester() }
            val passwordState = remember { PasswordState() }

            val focusRequester2 = remember { FocusRequester() }
            val passwordState2 = remember { PasswordState() }
            Text(
                text = "現在のパスワードを入力してください。",
                modifier = Modifier.padding(top = 32.dp),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
            )
            Password(
                "現在のパスワード",
                passwordState = passwordState,
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .padding(8.dp),
                onImeAction = { }
            )
            Text(
                text = "新しいパスワードを設定してください。",
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
            )

            Text(
                text = "半角英数字記号8文字以上16文字以内（英数字混合必須）で入力してください。（空白、￥は利用できません）",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Password(
                "新しいパスワード",
                passwordState = passwordState2,
                modifier = Modifier
                    .focusRequester(focusRequester2)
                    .padding(8.dp),
                onImeAction = { }
            )
            Password(
                "新しいパスワード（確認用）",
                passwordState = passwordState2,
                modifier = Modifier
                    .focusRequester(focusRequester2)
                    .padding(8.dp),
                onImeAction = { }
            )
            ErrorView("パスワードと確認用パスワードが一致していません。")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SampleTheme {
        ChangePasswordScreen(onBack = {}, onNext = {})
    }
}