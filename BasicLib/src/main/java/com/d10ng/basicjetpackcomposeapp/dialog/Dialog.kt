package com.d10ng.basicjetpackcomposeapp.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.d10ng.basicjetpackcomposeapp.compose.AppColor
import com.d10ng.basicjetpackcomposeapp.compose.AppShape
import com.d10ng.basicjetpackcomposeapp.compose.AppText
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun LoadingDialog (
    background: Color = Color(0x97454545)
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.6f))
            .pointerInput(Unit) {
                // 拦截外部的点击
                detectTapGestures { }
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            contentAlignment= Alignment.Center,
            modifier = Modifier
                .size(100.dp)
                .background(background, shape = AppShape.RC.v8)
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    }
}

@Composable
fun Dialog(
    onDismiss: () -> Unit = {},
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.6f))
            .pointerInput(Unit) {
                // 拦截外部的点击
                detectTapGestures { onDismiss.invoke() }
            }
            .statusBarsPadding()
            .navigationBarsWithImePadding(),
        content = content,
        contentAlignment = contentAlignment
    )
}

@Composable
fun DialogColumn(
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp)
            .wrapContentHeight()
            .background(AppColor.System.surface, AppShape.RC.v16)
            .padding(25.dp),
        content = content
    )
}

@Composable
fun DialogTitle(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = AppColor.Text.title
) {
    Text(
        text = text,
        style = AppText.Bold.Title.v24,
        modifier = modifier,
        color = color
    )
}

@Composable
fun DialogMessage(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = AppColor.Text.body
) {
    Text(
        text = text,
        style = AppText.Normal.Body.v14,
        modifier = modifier,
        color = color
    )
}