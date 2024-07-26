package com.and.sample.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.and.sample.PasswordState
import com.and.sample.R
import com.and.sample.TextFieldState

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onNext: () -> Unit,
) {
    Scaffold(
        content = { contentPadding ->
            val focusRequester = remember { FocusRequester() }
            val passwordState = remember { PasswordState() }

            val focusRequester2 = remember { FocusRequester() }
            val passwordState2 = remember { PasswordState() }

            Column(
                modifier =
                    modifier
                        .fillMaxSize()
                        .padding(contentPadding)
                        .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "ロゴ",
                    modifier = Modifier.padding(top = 32.dp),
                    style = MaterialTheme.typography.displaySmall,
                )
                UserName(
                    "ユーザーID",
                    passwordState = passwordState,
                    modifier =
                        Modifier
                            .focusRequester(focusRequester)
                            .padding(8.dp),
                    onImeAction = { },
                )
                Password(
                    "パスワード",
                    passwordState = passwordState2,
                    modifier =
                        Modifier
                            .focusRequester(focusRequester2)
                            .padding(8.dp),
                    onImeAction = { },
                )
                ErrorView("パスワードを6回連続で間違えたため、アカウントが一時的にロックされました。ロックを解除するためには管理者に解除申請を行ってください。")
            }
        },
        bottomBar = {
            FilledButtonExample {
                onNext()
            }
        },
    )
}

@Composable
fun Password(
    placeholder: String,
    passwordState: TextFieldState,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {},
) {
    val showPassword = rememberSaveable { mutableStateOf(false) }
    val maxChar = 16
    OutlinedTextField(
        value = passwordState.text,
        onValueChange = {
            if (it.length <= maxChar) {
                passwordState.text = it
            }
        },
        modifier =
            modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    passwordState.onFocusChange(focusState.isFocused)
                    if (!focusState.isFocused) {
                        passwordState.enableShowErrors()
                    }
                },
        placeholder = {
            Text(placeholder)
        },
        textStyle = MaterialTheme.typography.bodyMedium,
        trailingIcon = {
            if (showPassword.value) {
                IconButton(onClick = { showPassword.value = false }) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = stringResource(id = R.string.contentDescription),
                    )
                }
            } else {
                IconButton(onClick = { showPassword.value = true }) {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = stringResource(id = R.string.contentDescription),
                    )
                }
            }
        },
        visualTransformation =
            if (showPassword.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
        keyboardOptions =
            KeyboardOptions.Default.copy(
                imeAction = imeAction,
                keyboardType = KeyboardType.Password,
            ),
        keyboardActions =
            KeyboardActions(
                onDone = {
                    onImeAction()
                },
            ),
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
    )
}

@Composable
fun UserName(
    placeholder: String,
    passwordState: TextFieldState,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {},
) {
    OutlinedTextField(
        value = passwordState.text,
        onValueChange = {
            passwordState.text = it
        },
        modifier =
            modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    passwordState.onFocusChange(focusState.isFocused)
                    if (!focusState.isFocused) {
                        passwordState.enableShowErrors()
                    }
                },
        placeholder = {
            Text(placeholder)
        },
        textStyle = MaterialTheme.typography.bodyMedium,
        keyboardOptions =
            KeyboardOptions.Default.copy(
                imeAction = imeAction,
                keyboardType = KeyboardType.Password,
            ),
        keyboardActions =
            KeyboardActions(
                onDone = {
                    onImeAction()
                },
            ),
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
    )
}

@Composable
fun FilledButtonExample(
    text: String = "ログイン",
    onClick: () -> Unit,
) {
    Button(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp, start = 24.dp, end = 24.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = { onClick() },
    ) {
        Text(text)
    }
}

@Composable
fun ErrorView(text: String) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.alpha(0.5f),
                imageVector = Icons.Filled.Info,
                contentDescription = stringResource(id = R.string.contentDescription),
            )
            Text(
                text = text,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
            )
        }
    }
}
