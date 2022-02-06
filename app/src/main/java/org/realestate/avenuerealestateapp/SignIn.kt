package org.realestate.avenuerealestateapp
//Frontend and Backend  by Alinafe Mphepo(BIT-009-18)


import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.database.*


@Composable
fun RememberMe() {
    Spacer(modifier = Modifier.size(16.dp))
    Row {
        val isChecked = remember { mutableStateOf(false) }
        Checkbox(
            checked = isChecked.value,
            colors = CheckboxDefaults.colors(checkedColor = Color(221,88,88)),
            onCheckedChange = {
                isChecked.value = it
            }
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text("Remember me",
            style=TextStyle(fontWeight = FontWeight.W700,
                color = Color.Black,
                fontStyle = FontStyle.Normal,
                fontSize = 14.sp))
        }
    }



@Composable
fun SignInScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screen.SignInScreenPager.route) {
        composable(route = Screen.SignInScreenPager.route) {

            SignInScreenPager(navController = navController)
        }


        composable(route = Screen.PropertyView.route) {
            PropertyView()

        }

        composable(route=Screen.SignUpScreen.route){
            SignUpScreen()
        }
    }

}

@Composable
fun SignInScreenPager(navController: NavController) {
    val context=LocalContext.current
    val hideKeyboardController= LocalFocusManager.current
    val databaseReference = FirebaseDatabase.getInstance()
        .getReferenceFromUrl("https://avenue-e8241-default-rtdb.firebaseio.com/")

    var errorState by rememberSaveable {
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

    var errorState4 by rememberSaveable {
        mutableStateOf(false)
    }

    var errorMessage4 by rememberSaveable {
        mutableStateOf("")
    }

    var errorState5 by rememberSaveable {
        mutableStateOf(false)
    }

    var errorMessage5 by rememberSaveable {
        mutableStateOf("")
    }


    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }


    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    val isFormValid by derivedStateOf {
        errorState && errorState2
    }
    val focusColor = remember {
        mutableStateOf(Color(0, 0, 0))
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White, RectangleShape)
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    )
    {

        Text(
            text = "Sign in", color = Color(0, 0, 0), style = TextStyle(
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W700,
                fontSize = 22.sp
            )
        )
        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Welcome Back", style = TextStyle(
                color = Color(0, 0, 0),
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W700,
                fontSize = 26.sp
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Sign in with your email and Password and continue with social media",
            style = TextStyle(
                color = Color.Gray,
                fontStyle = FontStyle.Normal,
                fontWeight=FontWeight.Medium,

                fontSize = 18.sp, textAlign = TextAlign.Center
            )
        )





        Spacer(modifier = Modifier.height(60.dp))



        Column(
            modifier= Modifier
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f, fill = false),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {

            OutlinedTextField(
                modifier = Modifier.width(350.dp),
                shape = CircleShape,
                value = email,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(221, 88, 88),
                    unfocusedBorderColor = Color.Gray,
                    textColor = Color.Black,
                    focusedLabelColor = Color(221, 88, 88),
                    unfocusedLabelColor = Color.Gray,
                    placeholderColor = Color.Gray
                ),

                onValueChange = {
                    email = it
                    when {
                        email.isEmpty() -> {
                            errorState = false
                            errorMessage = "Email should not be blank!"
                        }
                        !isEmailValid(email) -> {
                            errorState = false
                            errorMessage = "Invalid email!"
                        }


                        else -> {
                            errorState = true
                            errorMessage = ""
                        }
                    }


                },
                placeholder = { Text(text = "Enter your email", fontSize = 16.sp) },
                label = { Text(text = "Email", fontSize = 16.sp) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                ),keyboardActions= KeyboardActions( onDone = { hideKeyboardController.clearFocus() }),
                trailingIcon = {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "",
                        tint = Color(221, 88, 88),
                        modifier = Modifier
                            .size(45.dp)
                            .padding(end = 20.dp)
                    )
                }
            )
            Text(text = errorMessage, color = Color(221, 88, 88))
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                modifier = Modifier.width(350.dp),
                value = password,
                shape = CircleShape,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(221, 88, 88),
                    unfocusedBorderColor = Color.Gray,
                    textColor = Color.Black,
                    focusedLabelColor = Color(221, 88, 88),
                    unfocusedLabelColor = Color.Gray,
                    placeholderColor = Color.Gray
                ),
                onValueChange = {
                    password = it
                    when {
                        password.isEmpty() -> {
                            errorState2 = false
                            errorMessage2 = "Password should not be blank!"
                        }
                        !isValidPassword(password) -> {
                            errorState2 = false
                            errorMessage2 = "Password should be minimum 8 characters." +
                                    "Contain at least one number." +
                                    "Contain at least one capital letter." +
                                    "Contain at least one small letter." +
                                    "Contain at least one special character"
                        }

                        else -> {
                            errorState2 = true
                            errorMessage2 = ""
                        }
                    }
                },
                label = { Text(text = "Password", fontSize = 16.sp) },
                placeholder = { Text(text = "Enter your password", fontSize = 16.sp) },
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
            Text(text = errorMessage2, color = Color(221, 88, 88))
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically

            ) {
                RememberMe()
                TextButton(onClick = {
                    val intent= Intent(context,ForgetPassword::class.java)
                    context.startActivity(intent)
               })
                {
                    Text(
                        text = "Forgot Password?",
                        style = TextStyle(
                            fontWeight = FontWeight.W700,
                            color = Color.Black,
                            fontStyle = FontStyle.Normal,
                            fontSize = 14.sp
                        )
                    )
                }

            }
            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick = {
                    val query: Query =
                        databaseReference.child("users").orderByChild("email").equalTo(email.trim())

                    query.addListenerForSingleValueEvent(object : ValueEventListener {

                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (user in dataSnapshot.children) {
                                    val passSnapshot=user.child("password").value


                                    if (passSnapshot!= null && passSnapshot == password) {
                                        //navController.navigate(Screen.PropertyView.route)
                                        val intent= Intent(context,HomePageActivity::class.java)
                                        context.startActivity(intent)

                                    } else {
                                        errorState3 = false
                                        errorMessage3 = "Password is incorrect"

                                    }
                                }
                            } else {
                                errorState4 = false
                                errorMessage4 = "User not found"

                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            errorState5 =false
                            errorMessage5 = "error!" + error.toException()

                        }

                    })
                },
                enabled = isFormValid,
                modifier = Modifier
                    .width(350.dp)
                    .height(55.dp),border= BorderStroke(2.dp,color=Color(221,88,88)),
                colors = ButtonDefaults.buttonColors(
                    disabledBackgroundColor = Color.White,
                    backgroundColor = Color(221, 88, 88)
                ),


                shape = RoundedCornerShape(40.dp)
            ) {
                Text(
                    text = "Continue",
                    style = TextStyle(
                        color = if (isFormValid) Color.White else Color.Gray,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W700
                    )
                )
            }
            Text(text = errorMessage4, color = Color(221, 88, 88))
            Spacer(modifier = Modifier.height(10.dp))


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Spacer(modifier = Modifier.weight(0.2f))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,verticalAlignment = Alignment.CenterVertically
                )
                {
                    RoundImage(painterResource(id = R.drawable.ic_flat_color_icons_google))
                    RoundImage(painterResource(id = R.drawable.ic_ci_facebook))
                    RoundImage(painterResource(id = R.drawable.ic_ci_twitter))



                }
                Spacer(modifier = Modifier.height(20.dp))
                TextButton(

                    onClick = { navController.navigate(Screen.SignUpScreen.route) }

                ) {
                    Text(
                        text = "Don't have an account? Sign Up",
                        style = TextStyle(fontWeight = FontWeight.W700,
                            fontStyle = FontStyle.Normal,
                            fontSize = 18.sp,
                            color = Color(221, 88, 88))
                    )

                }


            }


        }

    }
}


