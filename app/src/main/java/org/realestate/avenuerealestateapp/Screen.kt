package org.realestate.avenuerealestateapp
//Component  by Precious Tsetekani(BIT-029-18)

sealed class Screen(val route:String){
    object SignUpScreenPager:Screen(route = "SignUp_screen")
    object onboard:Screen(route="Onboard")
    object OnboardScreen:Screen(route="Onboard_screen")
    object SignInScreenPager:Screen(route = "SignInPager_screen")
    object AnimatedSplashScreen:Screen(route="AnimatedSplashScreen")
    object RegistrationScreenPager:Screen(route = "RegistrationPager_screen")
    object RegistrationScreen:Screen(route = "Registration_screen")
    object PropertyView:Screen(route = "property_screen")
    object SignInScreen:Screen(route = "SignIn_screen")
    object SignUpScreen:Screen(route = "SignUp_screen")
    object RegistrationSuccessScreen:Screen(route="RegistrationSuccess_Screen")
    object RegistrationSuccess:Screen(route="RegistrationSuccess")



    fun withArgs(vararg args:String):String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}