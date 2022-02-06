package org.realestate.avenuerealestateapp
//Frontend and Backend  by Precious Tsetekani(BIT-029-18)

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun RegistrationSuccess() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screen.onboard.route) {
        composable(route = Screen.onboard.route) {
            Surface(modifier=Modifier.fillMaxSize()){
                RegistrationSuccessScreen(navController = navController)
            }


        }

        composable(route=Screen.SignInScreen.route){
            SignInScreen()
        }
    }

}

@Composable
fun RegistrationSuccessScreen(navController: NavController){
    Box(modifier = Modifier
        .background(Color.White).padding(top=80.dp,start=40.dp,end=40.dp)
        .fillMaxSize(),contentAlignment = Alignment.TopCenter)
    {
        Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

            Text(text="Success!",
                style = TextStyle(color=Color(0,0,0),
                    textAlign=TextAlign.Center,
                    fontWeight = FontWeight.W700,
                    fontStyle = FontStyle.Normal,
                    fontSize = 40.sp))
            Spacer(modifier = Modifier.height(20.dp))

            Text(text="Congratulations,your account has been created successfully created",
                style = TextStyle(color=Color(0,0,0),
                    textAlign=TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                    fontSize = 17.sp))
            Spacer(modifier = Modifier.height(100.dp))

            Icon(painter = painterResource(id = R.drawable.ic_clarity_success_standard_line),
                tint= Color(221,88,88),
                contentDescription = "Success icon")

            Spacer(modifier = Modifier.height(100.dp))

            Button(
                enabled = true,
                onClick = {navController.navigate(Screen.SignInScreen.route)},
                modifier = Modifier
                    .width(350.dp)
                    .height(55.dp),
                colors= ButtonDefaults.buttonColors(backgroundColor = Color(221,88,88)),
                shape = RoundedCornerShape(40.dp)
            ) {
                Text(text = "Continue",
                    style = TextStyle(color= Color.White, fontSize = 20.sp))
            }

        }
    }
}