package com.finddreams.stockmarketdashboard

import androidx.compose.ui.graphics.Color
import com.finddreams.stockmarketdashboard.ui.theme.ColorStockGray
import com.finddreams.stockmarketdashboard.ui.theme.ColorStockGreen
import com.finddreams.stockmarketdashboard.ui.theme.ColorStockRed

fun getBarDataList(offset:Float=1f): List<BarData> {
    val marketData = arrayListOf<BarData>()
    marketData.add(
        BarData("跌停", 11 * offset.toInt(),
        ColorStockGreen
    )
    )
    marketData.add(
        BarData(">7", 55 * offset.toInt(),
        ColorStockGreen
    )
    )
    marketData.add(BarData("5-7", 196, ColorStockGreen))
    marketData.add(BarData("3-5", 979, ColorStockGreen))
    marketData.add(BarData("0-3", 2965, ColorStockGreen))
    marketData.add(BarData("0", 76, ColorStockGray))
    marketData.add(BarData("0-3", 502, ColorStockRed))
    marketData.add(BarData("3-5", 116, ColorStockRed))
    marketData.add(BarData("5-7", 20, ColorStockRed))
    marketData.add(BarData(">7", 9, ColorStockRed))
    marketData.add(BarData("涨停", 31, ColorStockRed))
    return marketData
}

data class BarData(val zdfRange: String, val zdNum: Int, val color: Color)