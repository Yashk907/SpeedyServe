package com.example.speedyserve.frontend.SignUpAndSignIn.SignIn

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.speedyserve.frontend.SignUpAndSignIn.AuthUiEvent
import com.example.speedyserve.frontend.SignUpAndSignIn.AuthViewmodel
import com.example.speedyserve.frontend.SignUpAndSignIn.RoundedTextField


@Composable
fun SignInScreen(modifier: Modifier = Modifier ,
                 viewmodel: AuthViewmodel= hiltViewModel(),
                 onSignInClick : ()-> Unit,
                 onSkipClick : ()-> Unit
){

    val onevent =viewmodel::onEvent
    val state=viewmodel.state
    Column(
        modifier = modifier.fillMaxSize().background(color = Color(0xFFECEFF1)),
        // verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        TextButton(onSkipClick, modifier = Modifier.align(Alignment.Start)
            .padding(10.dp)) {
            Text("Skip",
                fontSize = 19.sp  , fontWeight = FontWeight.SemiBold,
                modifier= Modifier
                    .padding(0.dp)
                    )
        }

        Spacer(modifier = Modifier.height(90.dp))
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Welcome to" , fontSize = 14.sp  , fontWeight = FontWeight.SemiBold)
            Text(text = "SpeedyServe" , fontSize = 32.sp , color = Color(0xFFFF9800), fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 12.dp))
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text("SignIn" ,
            fontSize = 18.sp ,
            fontWeight = FontWeight.Bold ,
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(start = 0.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))
        RoundedTextField(value = state.signInUsername ,
            onValueChange = {onevent(AuthUiEvent.SignInUsernameChanged(it))}
            , placeHolderText = "Username"
            , singleLine = true,
            modifier = Modifier.padding(start = 40.dp , end = 40.dp) ,
            isPasswordTextField = false )

        Spacer(modifier = Modifier.height(25.dp))
        RoundedTextField(value = state.signInPassword ,
            onValueChange = {onevent(AuthUiEvent.SignInPasswordChanged(it))} ,
            placeHolderText = "Password" ,
            singleLine = true,
            modifier = Modifier.padding(start = 40.dp , end = 40.dp) ,
            isPasswordTextField = true )
        Spacer(modifier = Modifier.height(25.dp))
        Button(onClick = {
            onevent(AuthUiEvent.SignIn)
        },
            modifier = Modifier.fillMaxWidth().height(50.dp).padding(start = 40.dp , end = 40.dp).background(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFF9800), Color(0xFF995B00)),
                    center = androidx.compose.ui.geometry.Offset(0f, 0f), // Gradient center
                    radius = 400f // Radius of the gradient
                ),
                shape = RoundedCornerShape(12.dp) // Rounded corners
            ),
            colors = ButtonDefaults.buttonColors(Color.Transparent),
        ) {
            Text(
                text = "SignIn",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFFFFF),
                ) )
        }
        Spacer(modifier = Modifier.height(25.dp))
        Row {Text("Not register ? Sign up first" ,
//            modifier = Modifier.clickable {onSignInClick }

            fontSize = 14.sp  ,
            fontWeight = FontWeight.Medium ,
            color = Color(0xFFFF9800),
            modifier= Modifier.align(Alignment.CenterVertically))
            IconButton(
                onClick = onSignInClick,
             modifier = Modifier.align(Alignment.CenterVertically))
         {
             Icon(imageVector = Icons.AutoMirrored.Filled.ArrowRightAlt,
                 "icons",
                 modifier= Modifier.align(Alignment.CenterVertically))
         }}

    }
}