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
import com.finddreams.stockmarketdashboard.ui.theme.ColorStockGray
import com.finddreams.stockmarketdashboard.ui.theme.ColorStockGreen
import com.finddreams.stockmarketdashboard.ui.theme.ColorStockRed
import kotlin.math.max
import kotlin.math.min

/**
 * 涨跌数量 横向对比图
 *Copyright (c) finddreams https://github.com/finddreams
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
    spacing: Dp,
    minBarWidth: Dp = 4.dp // 设置最小宽度
) {
    Canvas(modifier = modifier) {
        val totalWidth = size.width
        val height = size.height
        val spacingPx = spacing.toPx()
        val minBarWidthPx = minBarWidth.toPx()

        // 初步计算每段的宽度
        var width1 = max(totalWidth * progress1, minBarWidthPx)
        var width2 = max(totalWidth * progress2, minBarWidthPx)
        val remainingWidth = totalWidth - (width1 + width2 + spacingPx * 2)

        // 确保 width3 不低于最小宽度
        if (remainingWidth >= minBarWidthPx) {
            remainingWidth
        } else {
            // 如果剩余空间不足，则压缩前两段的宽度
            val deficit = minBarWidthPx - remainingWidth
            val adjustmentFactor = deficit / (width1 + width2)
            width1 *= (1 - adjustmentFactor)
            width2 *= (1 - adjustmentFactor)
            minBarWidthPx
        }

        val slantOffset = 13.dp.toPx() // 设置斜边偏移量
        val cornerRadius = min(height / 2, 13.dp.toPx())

        // 第一段路径
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

        // 第二段路径
        val path2 = Path().apply {
            moveTo(width1 + spacingPx, 0f)
            lineTo(width1 + width2 + spacingPx, 0f)
            lineTo(width1 + width2 - slantOffset + spacingPx, height)
            lineTo(width1 + spacingPx - slantOffset, height)
            close()
        }
        drawPath(path2, color2)

        // 第三段路径
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
        color1 = ColorStockRed,
        progress2 = ratioFlat,
        color2 = ColorStockGray,
        progress3 = ratioFall,
        color3 = ColorStockGreen,
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

