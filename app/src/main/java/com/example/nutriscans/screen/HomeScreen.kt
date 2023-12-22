package com.example.nutriscans.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nutriscans.R
import com.example.nutriscans.ui.theme.provider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                text = "Hello!",
                fontFamily = FontFamily( Font(
                    googleFont = GoogleFont("Poppins"),
                    fontProvider = provider,
                )
                )
            )
        }
        Spacer(modifier = Modifier.height(112.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.Logo),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(80.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
                ),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(8.dp),
                onClick = {

                },
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.scan_icon),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Scan")
                    }
                }

            )

            Spacer(modifier = Modifier.width(16.dp))

            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.history_icon),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "History")
                }
            }
        }
    }
}