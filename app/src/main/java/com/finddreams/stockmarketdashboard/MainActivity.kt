package com.finddreams.stockmarketdashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.finddreams.stockmarketdashboard.ui.theme.ColorStockGreen
import com.finddreams.stockmarketdashboard.ui.theme.ColorStockRed
import com.finddreams.stockmarketdashboard.ui.theme.StockMarketDashboardTheme
/**
 * 大盘分析UI图
 *Copyright (c) finddreams https://github.com/finddreams
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StockMarketDashboardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Content(Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    private fun Content(modifier: Modifier) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "大盘分析", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            UpDownBarChart(
                modifier = Modifier.padding(vertical = 25.dp),
                getBarDataList()
            )
            val riseNum = 3007
            val flatNum = 201
            val fallNum = 2180
            // 总 progress
            UpDownHorizontalBar(riseNum, fallNum, flatNum)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "涨${riseNum}家", fontSize = 13.sp, color = ColorStockRed)
                Text(text = "跌${fallNum}家", fontSize = 13.sp, color = ColorStockGreen)
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ContentPreview() {
        StockMarketDashboardTheme {
            Content(Modifier)
        }
    }
}
