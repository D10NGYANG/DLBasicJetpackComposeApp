package com.d10ng.basicjetpackcomposeapp.demo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.d10ng.basicjetpackcomposeapp.BaseActivity
import com.d10ng.basicjetpackcomposeapp.compose.AppColor
import com.d10ng.basicjetpackcomposeapp.compose.AppShape
import com.d10ng.basicjetpackcomposeapp.compose.AppText
import com.d10ng.basicjetpackcomposeapp.compose.AppTheme
import com.d10ng.basicjetpackcomposeapp.dialog.builder.*
import com.d10ng.basicjetpackcomposeapp.view.SolidButtonWithText
import com.d10ng.coroutines.launchIO
import com.d10ng.datelib.*
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.insets.statusBarsHeight
import kotlinx.coroutines.delay

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AppTheme(app = app) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsHeight()
                        .background(AppColor.System.primaryVariant))
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(AppColor.System.primary)
                        .padding(16.dp, 8.dp)
                    ) {
                        Text(text = "??????", style = AppText.Bold.OnPrimary.v18)
                    }

                    LazyColumn(modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(16.dp)
                    ) {
                        item {
                            SolidButtonWithText(text = "?????????????????????", onClick = {
                                app.showLoading()
                                launchIO {
                                    delay(1000L)
                                    app.hideLoading()
                                }
                            })
                        }

                        item {
                            SolidButtonWithText(text = "????????????", onClick = {
                                app.showDialog(WarningDialogBuilder(
                                    message = "?????????????????????????????????",
                                    onClickButton = {
                                        app.hideDialog()
                                    }
                                ))
                            })
                        }

                        item {
                            SolidButtonWithText(text = "????????????1", onClick = {
                                app.showDialog(BaseDialogBuilder(
                                    message = "??????????????????????????????????????????????????????????????????????????????????????????",
                                    onClickSure = {
                                        app.hideDialog()
                                        app.showToast("???????????????????????????")
                                    }
                                ))
                            })
                        }

                        item {
                            SolidButtonWithText(text = "????????????2", onClick = {
                                app.showDialog(BaseDialogBuilder(
                                    message = "??????????????????????????????????????????????????????????????????????????????????????????",
                                    onClickSure = {
                                        app.hideDialog()
                                        app.showToast("???????????????????????????")
                                    },
                                    onClickCancel = {
                                        app.hideDialog()
                                        app.showToast("???????????????????????????")
                                    }
                                ))
                            })
                        }

                        item {
                            SolidButtonWithText(text = "????????????3", onClick = {
                                val msg = StringBuilder().apply {
                                    for (i in 0 .. 100) {
                                        append("??????????????????????????????????????????????????????????????????????????????????????????")
                                    }
                                }
                                app.showDialog(getErrorDialogBuilder(
                                    message = msg.toString(),
                                    onClickSure = {
                                        app.hideDialog()
                                        app.showToast("???????????????????????????")
                                    },
                                    onClickCancel = {
                                        app.hideDialog()
                                        app.showToast("???????????????????????????")
                                    }
                                ))
                            })
                        }

                        item {
                            SolidButtonWithText(text = "???????????????1", onClick = {
                                app.showDialog(InputDialogBuilder(
                                    message = "??????????????????????????????",
                                    inputs = listOf(
                                        InputDialogBuilder.Input(
                                            keyboardType = KeyboardType.Number,
                                            verify = { value ->
                                                if (value.isEmpty()) {
                                                    InputDialogBuilder.Verify(false, "?????????????????????")
                                                } else if (!value.matches("[0-9X]+".toRegex())) {
                                                    InputDialogBuilder.Verify(false, "????????????????????????")
                                                } else {
                                                    InputDialogBuilder.Verify(true)
                                                }
                                            }
                                        ),
                                    ),
                                    onClickSure = {
                                        app.hideDialog()
                                        app.showToast("????????????${it[0]}")
                                    },
                                    onClickCancel = {
                                        app.hideDialog()
                                    }
                                ))
                            })
                        }

                        item {
                            var selectStr by remember {
                                mutableStateOf("1080*1920px")
                            }
                            SolidButtonWithText(text = "??????????????????", onClick = {
                                app.showDialog(RadioDialogBuilder(
                                    title = "???????????????",
                                    map = mutableMapOf(
                                        Pair("360*640px", "360P"),
                                        Pair("720*1280px", "720P"),
                                        Pair("1080*1920px", "1080P"),
                                        Pair("1440*2560px", "2K"),
                                    ),
                                    select = selectStr,
                                    onSelect = {
                                        app.hideDialog()
                                        app.showToast("???${it.second}???= ${it.first}")
                                        selectStr = it.first
                                    }
                                ))
                            })
                        }

                        item {
                            var selectStr by remember {
                                mutableStateOf("1080*1920px")
                            }
                            SolidButtonWithText(text = "??????????????????-?????????", onClick = {
                                app.showDialog(RadioDialogBuilder(
                                    title = "???????????????",
                                    map = mutableMapOf(
                                        Pair("360*640px", "360P"),
                                        Pair("720*1280px", "720P"),
                                        Pair("1080*1920px", "1080P"),
                                        Pair("1440*2560px", "2K"),
                                    ),
                                    select = selectStr,
                                    customItemView = { isSelect, info, onClick -> CustomRadioDialogItem(isSelect, info, onClick) },
                                    isRow = true,
                                    mainAxisSpacing = 16.dp,
                                    mainAxisAlignment = FlowMainAxisAlignment.Center,
                                    crossAxisSpacing = 16.dp,
                                    crossAxisAlignment = FlowCrossAxisAlignment.Center,
                                    onSelect = {
                                        app.hideDialog()
                                        app.showToast("???${it.second}???= ${it.first}")
                                        selectStr = it.first
                                    }
                                ))
                            })
                        }

                        item {
                            var selectTime by remember {
                                mutableStateOf(curTime)
                            }
                            SolidButtonWithText(text = "??????????????????", onClick = {
                                app.showDialog(DatePickerDialogBuilder(
                                    title = "????????????",
                                    message = "???????????????????????????",
                                    initValue = selectTime,
                                    onClickSure = {
                                        selectTime = it
                                        app.hideDialog()
                                        app.showToast("?????????????????????${it.toDateStr("yyyy-MM-dd")}")
                                    },
                                    onClickCancel = {
                                        app.hideDialog()
                                    }
                                ))
                            })
                        }

                        item {
                            var selectHour by remember {
                                mutableStateOf(curHour)
                            }
                            var selectMinute by remember {
                                mutableStateOf(curMinute)
                            }
                            var selectSecond by remember {
                                mutableStateOf(curSecond)
                            }
                            SolidButtonWithText(text = "??????????????????", onClick = {
                                app.showDialog(TimePickerDialogBuilder(
                                    title = "????????????",
                                    message = "???????????????????????????",
                                    hour = selectHour,
                                    minute = selectMinute,
                                    second = selectSecond,
                                    onClickSure = { h,m,s ->
                                        selectHour = h
                                        selectMinute = m
                                        selectSecond = s
                                        app.hideDialog()
                                        app.showToast("????????????????????? $selectHour:$selectMinute:$selectSecond")
                                    },
                                    onClickCancel = {
                                        app.hideDialog()
                                    }
                                ))
                            })
                        }

                        item {
                            var select by remember {
                                mutableStateOf(1)
                            }
                            SolidButtonWithText(text = "????????????????????????", onClick = {
                                app.showDialog(IntNumberPickerDialogBuilder(
                                    title = "??????",
                                    message = "???????????????????????????",
                                    value = select,
                                    start = 0,
                                    endInclude = 22,
                                    onClickSure = { v ->
                                        select = v
                                        app.hideDialog()
                                        app.showToast("?????????????????????????????? $select")
                                    },
                                    onClickCancel = {
                                        app.hideDialog()
                                    }
                                ))
                            })
                        }

                        item {
                            var select by remember {
                                mutableStateOf(1.0)
                            }
                            SolidButtonWithText(text = "???????????????????????????", onClick = {
                                app.showDialog(DoubleNumberPickerDialogBuilder(
                                    title = "??????",
                                    message = "???????????????????????????",
                                    value = select,
                                    start = 0.0,
                                    endInclude = 10.0,
                                    step = 0.5,
                                    onClickSure = { v ->
                                        select = v
                                        app.hideDialog()
                                        app.showToast("?????????????????????????????? $select")
                                    },
                                    onClickCancel = {
                                        app.hideDialog()
                                    }
                                ))
                            })
                        }

                        item {
                            val list = remember {
                                listOf(
                                    CustomPickInfo("360*640px", "360P"),
                                    CustomPickInfo("720*1280px", "720P"),
                                    CustomPickInfo("1080*1920px", "1080P"),
                                    CustomPickInfo("1440*2560px", "2K"),
                                )
                            }
                            var select by remember {
                                mutableStateOf(list[0])
                            }
                            SolidButtonWithText(text = "?????????????????????", onClick = {
                                app.showDialog(PickerDialogBuilder(
                                    title = "??????",
                                    message = "????????????????????????",
                                    label = { it.text },
                                    value = select,
                                    list = list,
                                    onClickSure = { v ->
                                        select = v
                                        app.hideDialog()
                                        app.showToast("??????????????????????????? ${select.text} : ${select.name}")
                                    },
                                    onClickCancel = {
                                        app.hideDialog()
                                    }
                                ))
                            })
                        }

                        item {
                            SolidButtonWithText(text = "????????????", onClick = {
                                app.showDialog(
                                    ProgressDialogBuilder(
                                        title = "?????????",
                                        message = "???????????????",
                                        progress = 0,
                                        max = 100
                                    )
                                )
                                launchIO {
                                    var index = 0
                                    while (index < 100) {
                                        index ++
                                        val builder = (app.dialogBuilder.value as ProgressDialogBuilder).copy(progress = index.toLong())
                                        app.showDialog(builder)
                                        delay(100)
                                    }
                                    app.hideDialog()
                                }
                            })
                        }

                        item {
                            SolidButtonWithText(text = "????????????", onClick = {
                                app.showDialog(SuccessOrFalseDialogBuilder(
                                    title = "????????????",
                                    isSuccess = true,
                                    onClickButton = {
                                        app.hideDialog()
                                    }
                                ))
                            })
                        }

                        item {
                            SolidButtonWithText(text = "????????????", onClick = {
                                app.showDialog(SuccessOrFalseDialogBuilder(
                                    title = "????????????",
                                    isSuccess = false,
                                    onClickButton = {
                                        app.hideDialog()
                                    }
                                ))
                            })
                        }
                    }
                }
            }
        }
    }
}

data class CustomPickInfo(
    var text: String = "",
    var name: String = ""
)

@Composable
fun CustomRadioDialogItem(
    isSelect: Boolean,
    info: Pair<String, Any>,
    onClick: () -> Unit
) {
    val background = remember(isSelect) {
        if (isSelect) AppColor.System.secondary else Color.Transparent
    }
    val borderWidth = remember(isSelect) {
        if (isSelect) 0.dp else 1.dp
    }
    val textColor = remember(isSelect) {
        if (isSelect) AppColor.On.secondary else AppColor.Text.body
    }
    Box(
        modifier = Modifier
            .size(width = 104.dp, height = 40.dp)
            .background(background, AppShape.RC.Cycle)
            .border(borderWidth, AppColor.Text.hint, AppShape.RC.Cycle)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = info.first, style = AppText.Medium.OnSecondary.v14, color = textColor)
    }
}