package com.example.nutriscans.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.nutriscans.ui.theme.provider

@Composable
fun HistoryScreen() {
    Text(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        text = "HistoryScreen",
        fontFamily = FontFamily( Font(
            googleFont = GoogleFont("Poppins"),
            fontProvider = provider,
        )
        )
    )
}