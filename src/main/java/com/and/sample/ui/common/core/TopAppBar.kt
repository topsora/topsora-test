package com.and.sample.ui.common.core

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.and.sample.R
import com.and.sample.TextFieldState
import com.and.sample.screen.search.SearchScreen
import com.and.sample.ui.theme.SampleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBar(text: String, modifier: Modifier = Modifier, closeButtonClick: () -> Unit) {
    CenterAlignedTopAppBar(
        modifier = modifier
            .padding(0.dp)
            .fillMaxWidth()
            .padding(0.dp),
        title = {
            Text(
                text = text, color = Color.White,
                style = MaterialTheme.typography.headlineSmall
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Gray),
        actions = {
            IconButton(
                modifier = modifier.padding(end = 8.dp),
                onClick = { closeButtonClick() }
            ) {
                Icon(
                    Icons.Filled.Close,
                    modifier = modifier
                        .width(54.dp)
                        .height(54.dp)
                        .padding(6.dp),
                    contentDescription = stringResource(id = R.string.contentDescription),
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
fun SearchTopAppBar2(
    text: String,
    modifier: Modifier = Modifier,
    closeButtonClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(0.dp)
            .fillMaxWidth()
            .height(68.dp)
            .padding(0.dp)
            .background(Color.Gray)
    ) {
        Text(
            modifier = modifier
                .align(Alignment.Center),
            text = text, color = Color.White,
            style = MaterialTheme.typography.headlineSmall
        )
        IconButton(
            modifier = modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp),
            onClick = { closeButtonClick() }
        ) {
            Icon(
                Icons.Filled.Close,
                modifier = modifier
                    .width(54.dp)
                    .height(54.dp)
                    .padding(6.dp),
                contentDescription = stringResource(id = R.string.contentDescription),
                tint = Color.White
            )
        }
    }
}

@Composable
fun SearchMenuButton(text: String, modifier: Modifier, buttonClick: () -> Unit) {
    TextButton(onClick = buttonClick, modifier = modifier) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )
    }
}

@Composable
fun SearchTitle(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun FirstName(
    placeholder: String,
    passwordState: TextFieldState,
    modifier: Modifier = Modifier,
    onImeAction: () -> Unit = {},
) {
    OutlinedTextField(
        value = passwordState.text,
        onValueChange = {
            passwordState.text = it
        },
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                passwordState.onFocusChange(focusState.isFocused)
                if (!focusState.isFocused) {
                    passwordState.enableShowErrors()
                }
            },
        placeholder = {
            Text(
                placeholder,
                modifier = modifier.padding(0.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray

            )
        },
        textStyle = MaterialTheme.typography.bodyLarge,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
            }
        ),
        shape = RoundedCornerShape(12.dp),
        singleLine = true
    )
}

@Composable
fun LastName(
    placeholder: String,
    passwordState: TextFieldState,
    modifier: Modifier = Modifier,
    onImeAction: () -> Unit = {},
) {
    OutlinedTextField(
        value = passwordState.text,
        onValueChange = {
            passwordState.text = it
        },
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                passwordState.onFocusChange(focusState.isFocused)
                if (!focusState.isFocused) {
                    passwordState.enableShowErrors()
                }
            },
        placeholder = {
            Text(placeholder, style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
        },
        textStyle = MaterialTheme.typography.bodyLarge,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
            }
        ),
        shape = RoundedCornerShape(12.dp),
        singleLine = true
    )
}

@Composable
fun DropdownList(
    itemList: List<String> = listOf(
        "A",
        "B",
        "C",
        "D",
    ),
    modifier: Modifier,
    placeholder: String
) {

    var mExpanded by remember { mutableStateOf(false) }
    var mSelectedText by remember { mutableStateOf("") }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(modifier) {

        // Create an Outlined Text Field
        // with icon and not expanded
        OutlinedTextField(
            value = mSelectedText,
            onValueChange = { },
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    mExpanded = !mExpanded
                }
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    mTextFieldSize = coordinates.size.toSize()
                },
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                // works like onClick
                                mExpanded = !mExpanded
                            }
                        }
                    }
                },
            placeholder = { Text(placeholder) },
            trailingIcon = {
                Icon(icon, "")
            },
            shape = RoundedCornerShape(12.dp),
            readOnly = true,
        )
        // Create a drop-down menu with list of cities,
        // when clicked, set the Text Field text as the city selected
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            itemList.forEach { label ->
                DropdownMenuItem(onClick = {
                    mSelectedText = label
                    mExpanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchDropDown(
    itemList: List<String> = listOf(
        "A",
        "B",
        "C",
        "D",
    ),
    modifier: Modifier,
    placeholder: String,
    onValueChange: (String) -> Unit
) {

    var mExpanded by rememberSaveable { mutableStateOf(false) }

    var mSelectedText by rememberSaveable { mutableStateOf("") }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = Icons.Filled.ArrowDropDown

    var offset = Offset.Zero
    val rotationState by animateFloatAsState(
        targetValue = if (mExpanded) 180f else 0f, label = ""
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    mTextFieldSize = coordinates.size.toSize()
                }
        ) {
            Card(
                modifier = modifier
                    .wrapContentSize()
                    .padding(4.dp)
                    .clip(MaterialTheme.shapes.small)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }) {
                        mExpanded = !mExpanded
                    }
                    .pointerInteropFilter {
                        offset = Offset(it.x, it.y)
                        false
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent,
                ),
                shape = MaterialTheme.shapes.small,
                border = BorderStroke(1.dp, Color.Gray)
            ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    BasicTextField(
                        readOnly = true,
                        modifier = modifier
                            .align(Alignment.TopStart)
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp, end = 48.dp),
                        value = mSelectedText,
                        onValueChange = { /*no operation*/ },
                        interactionSource = remember { MutableInteractionSource() }
                            .also { interactionSource ->
                                LaunchedEffect(interactionSource) {
                                    interactionSource.interactions.collect {
                                        if (it is PressInteraction.Release) {
                                            // works like onClick
                                            mExpanded = !mExpanded
                                        }
                                    }
                                }
                            },
                        textStyle = MaterialTheme.typography.bodySmall,
                        singleLine = true,
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = modifier.fillMaxWidth(),
                            ) {

                                if (mSelectedText.isEmpty()) {
                                    Text(
                                        text = placeholder,
                                        color = Color.Gray,
                                        style = MaterialTheme.typography.bodySmall,
                                    )
                                }
                            }
                            innerTextField()
                        }
                    )
                    Box(modifier = modifier.align(Alignment.TopEnd)) {
                        Row {
                            Icon(
                                modifier = modifier
                                    .padding(4.dp)
                                    .rotate(rotationState),
                                imageVector = icon,
                                contentDescription = stringResource(id = R.string.contentDescription)
                            )
                            Spacer(modifier = modifier.width(8.dp))
                        }
                    }
                }
            }
        }
        // Create a drop-down menu with list of cities,
        // when clicked, set the Text Field text as the city selected
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            offset = DpOffset(offset.x.dp + 8.dp, offset.y.dp),
            modifier = modifier
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() - 16.dp })
                .padding(start = 8.dp)
        ) {
            itemList.forEach { label ->
                DropdownMenuItem(onClick = {
                    mSelectedText = label
                    onValueChange(mSelectedText)
                    mExpanded = false
                }) {
                    Text(text = label, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }

}

@Composable
fun PostOfficeTextBox(
    placeholder: String,
    passwordState: TextFieldState,
    modifier: Modifier = Modifier,
    onImeAction: () -> Unit = {},
) {
    OutlinedTextField(
        value = passwordState.text,
        onValueChange = {
            passwordState.text = it
        },
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                passwordState.onFocusChange(focusState.isFocused)
                if (!focusState.isFocused) {
                    passwordState.enableShowErrors()
                }
            },
        placeholder = {
            Text(
                placeholder,
                modifier = modifier.padding(0.dp),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray

            )
        },
        textStyle = MaterialTheme.typography.bodyLarge,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
            }
        ),
        shape = RoundedCornerShape(12.dp),
        singleLine = true
    )
}

@Composable
fun SearchTextField(
    placeholder: String,
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
//    var isError by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
    ) {
        Card(
            modifier = modifier
                .padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            shape = MaterialTheme.shapes.small,
            border =
//            if (isError) BorderStroke(
//                1.dp,
//                MaterialTheme.colorScheme.primary
//            ) else
            BorderStroke(1.dp, Color.Gray)
        ) {
            BasicTextField(
                modifier = modifier
//                    .onFocusChanged { focusState ->
//                        isError = focusState.isFocused
//                    }
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                value = value,
                onValueChange = {
                    onValueChange(it)
                },
                textStyle = MaterialTheme.typography.bodyMedium,
                singleLine = true,
                decorationBox = { innerTextField ->
                    Box(
                        modifier = modifier,
                    ) {

                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                color = Color.Gray,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                    innerTextField()
                }
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