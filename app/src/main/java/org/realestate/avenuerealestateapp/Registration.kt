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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.database.*



@Composable
fun RegistrationScreen(email:String?,password:String?) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RegistrationScreenPager.route) {
        composable(route = Screen.RegistrationScreenPager.route) {

            RegistrationScreenPager(navController = navController, email, password)
        }


        composable(route = Screen.RegistrationSuccess.route) {
            RegistrationSuccess()

        }
    }

}

@Composable
fun RegistrationScreenPager(navController: NavController,email:String?,password:String?) {
    val hideKeyboardController= LocalFocusManager.current

    val password=password

    var phoneNumber="+265"

    var firstname by rememberSaveable {
        mutableStateOf("")

    }

    var lastname by rememberSaveable {
        mutableStateOf("")

    }
    var address by rememberSaveable {
        mutableStateOf("")
    }
    var phone by rememberSaveable {
        mutableStateOf("")
    }
    var NonErrorState by rememberSaveable {
        mutableStateOf(false)
    }

    var errorMessage by rememberSaveable {
        mutableStateOf("")
    }

    var nonErrorState2 by rememberSaveable {
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

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }


    val isFormValid by derivedStateOf {
        NonErrorState && nonErrorState2 && errorState3 && errorState4

    }


    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White, RectangleShape),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    )
    {
        Text(text ="Sign up", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Complete Profile", fontWeight = FontWeight.Bold, fontSize = 32.sp)
        Spacer(modifier = Modifier.height(18.dp))
        Text(text = "Complete your details or continue",  style = TextStyle(
            color = Color(151, 151, 151),
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp, textAlign = TextAlign.Center))

        Text(text = "with social media", style = TextStyle(
            color = Color(151, 151, 151),
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp, textAlign = TextAlign.Center))

        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier=Modifier.verticalScroll(rememberScrollState()).weight(weight=1f,fill=false),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )

        {
            Spacer(modifier = Modifier.weight(0.4f))

            OutlinedTextField(
                modifier = Modifier.width(350.dp),
                shape = CircleShape,
                value = firstname,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(221, 88, 88),
                    unfocusedBorderColor = Color.Gray,
                    textColor = Color.Black,
                    focusedLabelColor = Color(221, 88, 88),
                    unfocusedLabelColor = Color.Gray,
                    placeholderColor = Color.Gray
                ),

                onValueChange = {
                    firstname = it
                    when {
                        firstname.isEmpty() -> {
                            NonErrorState = false
                            errorMessage = "Firstname should not be blank!"
                        }
                        firstname.startsWith("0") -> {
                            NonErrorState = false
                            errorMessage = "Firstname should not start with zero"
                        }

                        else -> {
                            NonErrorState = true
                            errorMessage = ""
                        }
                    }
                },
                placeholder = { Text(text = "Enter your first name", fontSize = 16.sp) },
                label = { Text(text = "First Name", fontSize = 16.sp) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),keyboardActions= KeyboardActions( onDone = { hideKeyboardController.clearFocus() }),
                trailingIcon = {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_ant_design_user_outlined),
                        contentDescription = "",
                        tint = Color(221, 88, 88),
                        modifier = Modifier
                            .size(45.dp)
                            .padding(end = 20.dp)
                    )
                }
            )
            Text(text = errorMessage, color = Color(221, 88, 88))
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier.width(350.dp),
                value = lastname,
                shape = CircleShape,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(221, 88, 88),
                    unfocusedBorderColor = Color.Gray,
                    textColor = Color.Black,
                    focusedLabelColor = Color(221, 88, 88),
                    unfocusedLabelColor = Color.Gray,
                    placeholderColor = Color.Gray
                ),

                onValueChange = { lastname = it
                    when {
                        firstname.isEmpty() -> {
                            nonErrorState2 = false
                            errorMessage2 = "Lastname should not be blank!"
                        }
                        firstname.startsWith("0") -> {
                            nonErrorState2 = false
                            errorMessage2 = "Lastname should not start with zero"
                        }

                        else -> {
                            nonErrorState2 = true
                            errorMessage2 = ""
                        }
                    }},
                label = { Text(text = "Last Name", fontSize = 16.sp) },
                placeholder = { Text(text = "Enter your last name", fontSize = 16.sp) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),keyboardActions= KeyboardActions( onDone = { hideKeyboardController.clearFocus() }),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_ant_design_user_outlined),
                        contentDescription = "",
                        tint = Color(221, 88, 88),
                        modifier = Modifier
                            .size(50.dp)
                            .padding(end = 20.dp)
                    )
                }

            )
            Text(text = errorMessage2, color = Color(221, 88, 88))
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(

                modifier = Modifier.width(350.dp),
                value = phone,
                shape = CircleShape,
                leadingIcon={Text(text="+265",color=Color.Black)},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(221, 88, 88),
                    unfocusedBorderColor = Color.Gray,
                    textColor = Color.Black,
                    focusedLabelColor = Color(221, 88, 88),
                    unfocusedLabelColor = Color.Gray,
                    placeholderColor = Color.Gray
                ),

                onValueChange = { phone = it

                    when{
                        phone.isEmpty() -> {
                        errorState3 = false
                        errorMessage3 = "Phone should not be blank!"

                    }
                        phone.length!=9 -> {
                        errorState3 = false
                        errorMessage3 = "Invalid Phone number"

                    }


                    else -> {

                        errorState3 = true
                        errorMessage3 = ""
                    }
                    } },
                label = { Text(text = "Phone number", fontSize = 16.sp) },
                placeholder = { Text(text = "Enter your phone number", fontSize = 16.sp) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done
                ),keyboardActions= KeyboardActions( onDone = { hideKeyboardController.clearFocus() }),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bi_phone),
                        contentDescription = "",
                        tint = Color(221, 88, 88),
                        modifier = Modifier
                            .size(50.dp)
                            .padding(end = 20.dp)
                    )
                }

            )
            phoneNumber+=phone
            Text(text = errorMessage3, color = Color(221, 88, 88))
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier.width(350.dp),
                shape = CircleShape,
                value = address,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(221, 88, 88),
                    unfocusedBorderColor = Color.Gray,
                    textColor = Color.Black,
                    focusedLabelColor = Color(221, 88, 88),
                    unfocusedLabelColor = Color.Gray,
                    placeholderColor = Color.Gray
                ),

                onValueChange = { address = it
                    when {
                        address.isEmpty() -> {
                            errorState4 = false
                            errorMessage4 = "Address should not be blank!"
                        }


                        else -> {
                            errorState4 = true
                            errorMessage4 = ""
                        }
                    }},
                placeholder = { Text(text = "Enter your address", fontSize = 16.sp) },
                label = { Text(text = "Address", fontSize = 16.sp) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),keyboardActions= KeyboardActions( onDone = { hideKeyboardController.clearFocus() }),
                trailingIcon = {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_entypo_address),
                        contentDescription = "",
                        tint = Color(221, 88, 88),
                        modifier = Modifier
                            .size(45.dp)
                            .padding(end = 20.dp)
                    )
                }
            )
            Text(text = errorMessage4, color = Color(221, 88, 88))
            Spacer(modifier = Modifier.height(8.dp))


            Spacer(modifier = Modifier.height(32.dp))


            Button(
                onClick = {
                    val databaseRef:DatabaseReference= databaseReference.child("users").child(phoneNumber)
                    databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {

                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            if(dataSnapshot.exists()){
                                errorState3=false
                                errorMessage3="Phone is already associated with existing account"


                        }
                            else{
                                if (email != null) {
                                    databaseReference.child("users").child(phoneNumber).child("Firstname")
                                        .setValue(firstname)
                                }
                                if (email != null) {
                                    databaseReference.child("users").child(phoneNumber).child("Lastname")
                                        .setValue(lastname)
                                }
                                if (email != null) {
                                    databaseReference.child("users").child(phoneNumber).child("password")
                                        .setValue(password)
                                }
                                if (email != null) {
                                    databaseReference.child("users").child(phoneNumber).child("email")
                                        .setValue(email)
                                }
                                if (email != null) {
                                    databaseReference.child("users").child(phoneNumber).child("address")
                                        .setValue(address)
                                }
                                navController.navigate(Screen.RegistrationSuccess.route)

                            }
                        }

                        override fun onCancelled(error: DatabaseError) {}

                    })

                    //Sending data to firebase Realtime Database
                    //we are using email address as unique identity of every user
                    //so all the other details of user comes under email address

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
                    style = TextStyle(color=if (isFormValid) Color.White else Color.Gray, fontSize = 20.sp)
                )
            }
            Spacer(modifier = Modifier.weight(0.6f))
            Spacer(modifier = Modifier.height(45.dp))




            Text(
                text = "By continuing you confirm that you",
                style = TextStyle(
                    color = Color(151, 151, 151),
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp, textAlign = TextAlign.Center)
            )
            Text(
                text = "agree with our terms and conditions",
                style = TextStyle(
                    color = Color(151, 151, 151),
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp, textAlign = TextAlign.Center)
            )




        }

    }

}
