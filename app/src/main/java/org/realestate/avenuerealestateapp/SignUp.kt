package org.realestate.avenuerealestateapp
//Frontend and Backend by Precious Tsetekani(BIT-029-18)

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import java.util.regex.Pattern


fun isEmailValid(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
fun isValidPassword(password:String): Boolean {
    var valid = true


    // Password policy check
    // Password should be minimum minimum 8 characters long

    if (password.length < 8) {
        valid = false
    }
    // Password should contain at least one number
    var exp = ".*[0-9].*"
    var pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
    var matcher = pattern.matcher(password)
    if (!matcher.matches()) {
        valid = false
    }


    // Password should contain at least one capital letter
    exp = ".*[A-Z].*"
    pattern = Pattern.compile(exp)
    matcher = pattern.matcher(password)
    if (!matcher.matches()) {
        valid = false
    }


    // Password should contain at least one small letter
    exp = ".*[a-z].*"
    pattern = Pattern.compile(exp)
    matcher = pattern.matcher(password)
    if (!matcher.matches()) {
        valid = false
    }


    // Password should contain at least one special character
    // Allowed special characters : "~!@#$%^&*()-_=+|/,."';:{}[]<>?"
    exp = ".*[~!@#\$%\\^&*()\\-_=+\\|\\[{\\]};:'\",<.>/?].*"
    pattern = Pattern.compile(exp)
    matcher = pattern.matcher(password)
    if (!matcher.matches()) {
        valid = false
    }

    return valid
}


@Composable
fun SignUpScreen(){
    LocalConfiguration.current
    val navController= rememberNavController()
    NavHost(navController =navController , startDestination =Screen.SignUpScreenPager.route ){
        composable(route=Screen.SignUpScreenPager.route){
            SignUpScreenPager(navController = navController)
        }

        composable(
            route=Screen.RegistrationScreen.route + "/{email}/{password}",
            arguments = listOf(
                navArgument("email"){
                    type= NavType.StringType

                },
            navArgument("password"){
                type= NavType.StringType

            })
        ){entry->
            RegistrationScreen(email=entry.arguments?.getString("email"),password=entry.arguments?.getString("password"))
        }

    }
}



@Composable
fun SignUpScreenPager(navController: NavController) {
    val hideKeyboardController=LocalFocusManager.current
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }

    var confirmPassword by rememberSaveable {
        mutableStateOf("")
    }


    var nonErrorState by rememberSaveable {
        mutableStateOf(false)
    }

    var errorMessage by rememberSaveable {
        mutableStateOf("")
    }

    var errorState2 by rememberSaveable {
        mutableStateOf(false)
    }

    var errorMessage2 by rememberSaveable {
        mutableStateOf("")
    }
    var errorState3 by rememberSaveable {
        mutableStateOf(false)
    }

    var errorMessage3 by rememberSaveable {
        mutableStateOf("")
    }

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    var isConfirmPasswordVisible by remember {
        mutableStateOf(false)
    }
    val isFormValid by derivedStateOf {
        nonErrorState && errorState2 && errorState3
    }



    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White, RectangleShape)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    )
    {
        Text(text ="Sign up", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(15.dp))
        Text(text ="Register Account", fontWeight = FontWeight.Bold, fontSize = 32.sp)
        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Complete your details or continue with social media",
            style = TextStyle(
            color = Color.Gray,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp, textAlign = TextAlign.Center
        ))


        Spacer(modifier = Modifier.height(40.dp))




        Column(
            Modifier.verticalScroll(rememberScrollState()).weight(weight=1f,fill=false),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Spacer(modifier = Modifier.weight(0.4f))



            OutlinedTextField(
                modifier = Modifier.width(350.dp),
                shape = CircleShape,
                value = email,
                colors=TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color(221,88,88),
                    unfocusedBorderColor=Color.Gray,
                    textColor =Color.Black,
                    focusedLabelColor =Color(221,88,88),
                    unfocusedLabelColor = Color.Gray,
                    placeholderColor =Color.Gray),

                onValueChange = { email = it.trim()
                    when{
                        email.isEmpty() ->{
                            nonErrorState=false
                            errorMessage="Email should not be blank!"
                        }
                        !isEmailValid(email) ->{
                            nonErrorState=false
                            errorMessage="Invalid email!"
                        }



                        else ->{
                            nonErrorState=true
                            errorMessage=""
                        }


                }
                                },
                placeholder = { Text(text = "Enter your email",fontSize = 16.sp)},
                label = { Text(text = "Email",fontSize = 16.sp)},
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Done),
                keyboardActions= KeyboardActions( onDone = { hideKeyboardController.clearFocus() }),
               


                trailingIcon = {

                    Icon(painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "",
                        tint=Color(221,88,88),
                        modifier = Modifier
                            .size(45.dp)
                            .padding(end = 20.dp))
                }
            )

            Text(text=errorMessage,color=Color(221,88,88))
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier.width(350.dp),
                value = password,
                shape = CircleShape,
                colors=TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color(221,88,88),
                    unfocusedBorderColor=Color.Gray,
                    textColor =Color.Black,
                    focusedLabelColor =Color(221,88,88),
                    unfocusedLabelColor = Color.Gray,
                    placeholderColor =Color.Gray),

                onValueChange = { password = it
                    when{
                        password.isEmpty() ->{
                            errorState2=false
                            errorMessage2="Password should not be blank!"
                        }
                        !isValidPassword(password) ->{
                            errorState2=false
                            errorMessage2="Password should be minimum 8 characters." +
                                    "Contain at least one number." +
                                    "Contain at least one capital letter." +
                                    "Contain at least one small letter." +
                                    "Contain at least one special character"
                        }

                        else ->{
                            errorState2=true
                            errorMessage2=""
                        }}},
                label = { Text(text = "Password",fontSize = 16.sp)},
                placeholder = { Text(text = "Enter your password",fontSize = 16.sp)},
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),keyboardActions= KeyboardActions( onDone = { hideKeyboardController.clearFocus() }),
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),

                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            painter = if (isPasswordVisible) painterResource(id = R.drawable.ic_unlock) else painterResource(
                                id = R.drawable.ic_clarity_lock_line
                            ),
                            contentDescription = "Password Toggle",
                            tint = Color(221, 88, 88),
                            modifier = Modifier
                                .size(50.dp)
                                .padding(end = 20.dp)
                        )
                    }
                }
                    )




            Text(text=errorMessage2,
                modifier = Modifier.width(340.dp),
                textAlign = TextAlign.Center,
                color=Color(221,88,88))
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier.width(350.dp),
                value = confirmPassword,
                shape = CircleShape,
                colors=TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color(221,88,88),
                    unfocusedBorderColor=Color.Gray,
                    textColor =Color.Black,
                    focusedLabelColor =Color(221,88,88),
                    unfocusedLabelColor = Color.Gray,
                    placeholderColor =Color.Gray),

                onValueChange = { confirmPassword = it
                    when{
                        password.isEmpty() ->{
                            errorState3=false
                            errorMessage3="Password should not be blank!"
                        }
                        password != confirmPassword ->{
                            errorState3=false
                            errorMessage3="Passwords are not matching!"
                        }

                        else ->{
                            errorState3=true
                            errorMessage3=""
                        }}},
                label = { Text(text = "Confirm Password",fontSize = 16.sp)},
                placeholder = { Text(text = "Confirm your password",fontSize = 16.sp)},
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),keyboardActions= KeyboardActions( onDone = { hideKeyboardController.clearFocus() }),
                visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { isConfirmPasswordVisible = !isConfirmPasswordVisible }) {
                        Icon(
                            painter = if (isConfirmPasswordVisible) painterResource(id = R.drawable.ic_unlock) else painterResource(
                                id = R.drawable.ic_clarity_lock_line
                            ),
                            contentDescription = "Password Toggle",
                            tint = Color(221, 88, 88),
                            modifier = Modifier
                                .size(50.dp)
                                .padding(end = 20.dp)
                        )
                    }
                }
            )

            Text(text=errorMessage3,color=Color(221,88,88))
            Spacer(modifier = Modifier.height(16.dp))


            Button(
                enabled = isFormValid,

                onClick = {
                    val query: Query = databaseReference.child("users").orderByChild("email").equalTo(email.trim())
                    query.addListenerForSingleValueEvent(object : ValueEventListener {

                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            if(dataSnapshot.exists()){
                                nonErrorState=false
                                errorMessage="Email is already associated with existing account"

                            }
                            else{
                                navController.navigate(Screen.RegistrationScreen.withArgs(email,password))
                            }

                        }

                        override fun onCancelled(error: DatabaseError) {}

                    })},

                modifier = Modifier
                    .width(350.dp)
                    .height(55.dp),
                colors=ButtonDefaults.buttonColors(disabledBackgroundColor = Color.White,
                    backgroundColor = Color(221,88,88)),
                border=BorderStroke(2.dp,color=Color(221,88,88)),


                shape = RoundedCornerShape(40.dp)
            ) {
                Text(text = "Continue",
                    style = TextStyle(color=if (isFormValid) Color.White else Color.Gray, fontSize = 20.sp))
            }
            Spacer(modifier = Modifier.height(60.dp))


            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Row( modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly)
                {
                    RoundImage( painterResource(id = R.drawable.ic_flat_color_icons_google))

                    RoundImage( painterResource(id = R.drawable.ic_ci_facebook))

                    RoundImage( painterResource(id = R.drawable.ic_ci_twitter))


                }
                Spacer(modifier=Modifier.height(20.dp))

                Text(text="By continuing you confirm that you",
                    style=TextStyle(color=Color.Gray,
                        fontSize = 16.sp,
                    fontWeight = FontWeight.Medium)
                    )
                Text(text="agree with our terms and conditions.",
                    style=TextStyle(color=Color.Gray,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium))

            }



        }

    }

}
