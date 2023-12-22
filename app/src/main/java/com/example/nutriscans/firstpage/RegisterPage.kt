package com.example.nutriscans.firstpage

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nutriscans.R
import com.example.nutriscans.navigation.NavScreen
import com.example.nutriscans.ui.theme.provider

@Composable
fun RegisterPage(navController: NavController) {

    val usernameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val passwordConfig = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        ScrollableColumnReg(usernameValue, emailValue, passwordValue, passwordConfig, passwordVisibility, navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScrollableColumnReg(
    emailValue:MutableState<String>,
    usernameValue:MutableState<String>,
    passwordValue:MutableState<String>,
    passwordConfig:MutableState<String>,
    passwordVisibility:MutableState<Boolean>,

    modifier: Modifier = Modifier,
    navController: NavController,
    scrollState: ScrollState = rememberScrollState(0),
) {
    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            text = "Signup",
            fontFamily = FontFamily( Font(
                googleFont = GoogleFont("Poppins"),
                fontProvider = provider,
            )
            )
        )
        Spacer(modifier = Modifier.padding(30.dp))
        OutlinedTextField(
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f),
            label = { Text(text = "Enter Your Username") },
            placeholder = { Text(text = "Username") },
            value = usernameValue.value,
            onValueChange = {usernameValue.value = it}
        )
        OutlinedTextField(
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f),
            label = { Text(text = "Enter Your Email") },
            placeholder = { Text(text = "Email") },
            value = emailValue.value,
            onValueChange = {emailValue.value = it}
        )
        OutlinedTextField(
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.password_eye),
                        contentDescription = "PassEye",
                        tint = if (passwordVisibility.value) Color.Cyan else Color.Gray
                    )
                }
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f),
            label = { Text(text = "Enter Your Password") },
            placeholder = { Text(text = "Password") },
            value = passwordValue.value,
            visualTransformation = if (passwordVisibility.value) VisualTransformation.None
            else PasswordVisualTransformation(),
            onValueChange = {passwordValue.value = it}

        )
        OutlinedTextField(
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.password_eye),
                        contentDescription = "PassEye",
                        tint = if (passwordVisibility.value) Color.Cyan else Color.Gray
                    )
                }
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f),
            label = { Text(text = "Confirm Your Password") },
            placeholder = { Text(text = "Password") },
            value = passwordConfig.value,
            visualTransformation = if (passwordVisibility.value) VisualTransformation.None
            else PasswordVisualTransformation(),
            onValueChange = {passwordConfig.value = it}

        )
        Spacer(modifier = Modifier.padding(50.dp))
        Button(
            onClick = {
                navController.navigate(route = NavScreen.Login.route)
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp)
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily( Font(
                    googleFont = GoogleFont("Poppins"),
                    fontProvider = provider,
                )
                ),
                text = "Sign Up", fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row{
            Text(
                fontSize = 12.sp,
                text = "Already have an account?",
                fontFamily = FontFamily( Font(
                    googleFont = GoogleFont("Poppins"),
                    fontProvider = provider,
                )
                )
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                modifier = Modifier.clickable(onClick = {
                        navController.navigate(route = NavScreen.Login.route)
                }),
                fontWeight = FontWeight.Bold,
                color = Color.Cyan,
                fontSize = 12.sp,
                text = "Login",
                fontFamily = FontFamily( Font(
                    googleFont = GoogleFont("Poppins"),
                    fontProvider = provider,
                )
                )
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun RegisterPagePreview() {
    RegisterPage(navController = rememberNavController())
}