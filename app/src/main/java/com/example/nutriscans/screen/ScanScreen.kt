package com.example.nutriscans.screen


import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ScanScreen() {
    val context = LocalContext.current

    context.startActivity(Intent(context, ScanActivity::class.java))
    HistoryScreen()

}

