package com.finddreams.stockmarketdashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.min

/**
 * 涨跌柱状图
 */
@Composable
fun UpDownHorizontalBar(
    progress1: Float,
    color1: Color,
    progress2: Float,
    color2: Color,
    progress3: Float,
    color3: Color,
    modifier: Modifier = Modifier,
    spacing: Dp
) {
    Canvas(modifier = modifier) {
        val totalWidth = size.width
        val height = size.height
        val spacingPx = spacing.toPx()

        val width1 = totalWidth * progress1
        val width2 = totalWidth * progress2
        val width3 = totalWidth * progress3

        val slantOffset = 13.dp.toPx()// 设置斜边偏移量
        val cornerRadius = min(height / 2, 15.dp.toPx())

        val path1 = Path().apply {
            moveTo(cornerRadius, 0f)
            lineTo(width1, 0f)
            lineTo(width1 - slantOffset, height)
            lineTo(cornerRadius, height)
            arcTo(
                rect = Rect(0f, 0f, 2 * cornerRadius, height),
                startAngleDegrees = 90f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )
            close()
        }
        drawPath(path1, color1)

        val path2 = Path().apply {
            moveTo(width1 + spacingPx, 0f)
            lineTo(width1 + width2 + spacingPx, 0f)
            lineTo(width1 + width2 - slantOffset + spacingPx, height)
            lineTo(width1 + spacingPx - slantOffset, height)
            close()
        }
        drawPath(path2, color2)

        val path3 = Path().apply {
            moveTo(width1 + width2 + 2 * spacingPx, 0f)
            lineTo(totalWidth - cornerRadius, 0f)
            arcTo(
                rect = Rect(totalWidth - 2 * cornerRadius, 0f, totalWidth, height),
                startAngleDegrees = -90f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )
            lineTo(width1 + width2 - slantOffset + 2 * spacingPx, height)
            close()
        }
        drawPath(path3, color3)
    }
}
@Composable
fun UpDownHorizontalBar(riseNum: Int, fallNum: Int, flatNum: Int) {
    val totalProgress = riseNum + fallNum + flatNum
    // 计算占比
    val ratioRise = (riseNum.toFloat() / totalProgress)
    val ratioFall = (fallNum.toFloat() / totalProgress)
    val ratioFlat = (flatNum.toFloat() / totalProgress)
    UpDownHorizontalBar(
        progress1 = ratioRise,
        color1 = Color(0xFFE53935),
        progress2 = ratioFlat,
        color2 = Color(0xff98A1B2),
        progress3 = ratioFall,
        color3 = Color(0xFF00C853),
        modifier = Modifier
            .fillMaxWidth()
            .height(12.dp),
        spacing = 6.dp
    )
}

@Preview
@Composable
private fun PreviewBar() {
    val riseNum = 3007
    val flatNum = 201
    val fallNum = 2180
    // 总 progress
    UpDownHorizontalBar(riseNum, fallNum, flatNum)
}

