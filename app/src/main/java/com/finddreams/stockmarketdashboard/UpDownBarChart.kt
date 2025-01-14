package com.finddreams.stockmarketdashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/**
 *涨跌分布柱状图
 *Copyright (c) finddreams https://github.com/finddreams
 */
@Composable
fun UpDownBarChart(
    modifier: Modifier = Modifier,
    barList: List<BarData>
) {
    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = modifier
            .height(130.dp)
            .fillMaxWidth()
    ) {
        val chartWidth = size.width
        val chartHeight = size.height
        val chartBottom = chartHeight

        // 动态计算柱子的宽度和间距
        val totalBars = barList.size
        val barWidth = chartWidth / (totalBars * 1.5f) // 柱子宽度为总宽度的1/1.5
        val barSpacing = (chartWidth - (barWidth * totalBars)) / (totalBars - 1)

        // 动态计算比例尺
        val max = barList.maxOf { it.zdNum }
        val itemHeight = if (max.toFloat() == 0f) 0f else chartHeight / max

        barList.forEachIndexed { index, item ->
            val barHeight = item.zdNum * itemHeight
            val barSize = Size(barWidth, barHeight)

            // 将柱子的左上角对齐到最左侧，调整间距分布
            val barLeft = index * (barWidth + barSpacing)
            val barTop = chartBottom - barHeight // 柱子顶部Y
            val topLeft = Offset(barLeft, barTop)

            // 绘制柱状图
            drawRoundRect(color = item.barColor, topLeft = topLeft, size = barSize)

            // 绘制顶部文字 (居中偏上)
            drawCenteredText(
                textMeasurer,
                text = item.zdNum.toString(),
                color = item.topTextColor,
                centerX = barLeft + barWidth / 2, // 中心点位于柱子的中间
                centerY = barTop,
                offsetY = -12.dp.toPx(), // 距离顶部偏移
            )

            // 绘制底部文字 (居中偏下)
            drawCenteredText(
                textMeasurer,
                text = item.zdfRange.toString(),
                color = item.bottomTextColor,
                centerX = barLeft + barWidth / 2, // 中心点位于柱子的中间
                centerY = chartBottom,
                offsetY = 4.dp.toPx() // 距离底部偏移
            )
        }
    }
}


// 帮助函数：绘制文字
fun DrawScope.drawCenteredText(
    textMeasurer: TextMeasurer,
    text: String,
    color: Color,
    centerX: Float,
    centerY: Float,
    offsetY: Float = 0f,
    fontSize: TextUnit = 10.sp
) {
    val textLayoutResult = textMeasurer.measure(
        text = AnnotatedString(text),
        style = TextStyle(fontSize = fontSize),
    )
    val textWidth = textLayoutResult.size.width.toFloat()
    val textOffset = Offset(
        x = centerX - textWidth / 2,
        y = centerY + offsetY
    )
    drawText(textLayoutResult = textLayoutResult, color = color, topLeft = textOffset)
}