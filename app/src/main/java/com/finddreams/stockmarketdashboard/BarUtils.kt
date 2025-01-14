package com.finddreams.stockmarketdashboard

import androidx.compose.ui.graphics.Color
import com.finddreams.stockmarketdashboard.ui.theme.ColorStockGray
import com.finddreams.stockmarketdashboard.ui.theme.ColorStockGreen
import com.finddreams.stockmarketdashboard.ui.theme.ColorStockGreen1
import com.finddreams.stockmarketdashboard.ui.theme.ColorStockGreen2
import com.finddreams.stockmarketdashboard.ui.theme.ColorStockGreen3
import com.finddreams.stockmarketdashboard.ui.theme.ColorStockGreen4
import com.finddreams.stockmarketdashboard.ui.theme.ColorStockGreen5
import com.finddreams.stockmarketdashboard.ui.theme.ColorStockRed
import com.finddreams.stockmarketdashboard.ui.theme.ColorStockRed1
import com.finddreams.stockmarketdashboard.ui.theme.ColorStockRed2
import com.finddreams.stockmarketdashboard.ui.theme.ColorStockRed3
import com.finddreams.stockmarketdashboard.ui.theme.ColorStockRed4
import com.finddreams.stockmarketdashboard.ui.theme.ColorStockRed5
import com.finddreams.stockmarketdashboard.ui.theme.ColorTextColor

fun getBarDataList(): List<BarData> {
    val barList = arrayListOf<BarData>()
    barList.add(
        BarData(
            "涨停",
            53,
            ColorStockRed1,
            topTextColor = ColorStockRed,
            bottomTextColor = ColorStockRed
        )
    )
    barList.add(BarData(">7%", 43, ColorStockRed2, topTextColor = ColorStockRed))
    barList.add(BarData("7-5%", 75, ColorStockRed3, topTextColor = ColorStockRed))
    barList.add(BarData("5-2%", 474, ColorStockRed4, topTextColor = ColorStockRed))
    barList.add(BarData("2-0%", 2362, ColorStockRed5, topTextColor = ColorStockRed))
    barList.add(BarData("平", 201, ColorStockGray, topTextColor = ColorStockGray))
    barList.add(BarData("0-2%", 1400, ColorStockGreen1, topTextColor = ColorStockGreen))
    barList.add(BarData("2-5%", 603, ColorStockGreen2, topTextColor = ColorStockGreen))
    barList.add(BarData("5-7%", 76, ColorStockGreen3, topTextColor = ColorStockGreen))
    barList.add(BarData("7%<", 59, ColorStockGreen4, topTextColor = ColorStockGreen))
    barList.add(
        BarData(
            "跌停", 42, ColorStockGreen5,
            topTextColor = ColorStockGreen,
            bottomTextColor = ColorStockGreen
        )
    )
    return barList
}

data class BarData(
    val zdfRange: String,
    val zdNum: Int,
    val barColor: Color,
    val topTextColor: Color = ColorTextColor,
    val bottomTextColor: Color = ColorTextColor
)