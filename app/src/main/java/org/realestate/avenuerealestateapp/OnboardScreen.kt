package org.realestate.avenuerealestateapp
//Frontend and Backend  by Gift Chimwendo(BIT-032-16)

import androidx.annotation.FloatRange
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState


@ExperimentalPagerApi
@Composable
fun OnboardScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screen.onboard.route) {
        composable(route = Screen.onboard.route) {
            Surface(modifier=Modifier.fillMaxSize()){
                onboard(navController = navController)
            }


        }

        composable(route=Screen.SignInScreen.route){
            SignInScreen()
        }
    }

}


@ExperimentalPagerApi
@Composable
fun onboard(navController: NavController){
    val items = ArrayList<OnBoardingData>()

    items.add(
        OnBoardingData(R.drawable.onboardscreen1,
            "Looking to buy a home?",
            "Oh You.",
            "Can.","We know you have alot to do.So we make it easy to buy homes anytime,anywhere."

        )
    )

    items.add(
        OnBoardingData(
            R.drawable.onboardscreen2,
            "Sell", ""
            ,"Anytime.",""
        )
    )

    items.add(
        OnBoardingData(
            R.drawable.onboardscreen3,
            "", "Avenue"
          ,"","Avenue lets you search homes and apartments,list yours for sale or rent ,tour the home through pictures,connect with agents,save your searches and receive push notifications of updates to your search criteria."
        )
    )

    val pagerState = rememberPagerState(
        pageCount = items.size,
        initialOffscreenLimit = 2,
        infiniteLoop = false,
        initialPage = 0,
    )

    OnBoardingPager(
        item = items, pagerState = pagerState, modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),navController
    )

}




@ExperimentalPagerApi
@Composable
fun OnBoardingPager(
    item: List<OnBoardingData>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,navController: NavController
) {
    Box(modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HorizontalPager(state = pagerState) { page ->
                Column(
                    modifier = Modifier
                        .padding(top = 60.dp)
                        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(id = item[page].image),
                        contentDescription = item[page].title,
                        modifier = Modifier
                            .height(250.dp)
                            .fillMaxWidth()
                    )

                    Text(
                        text = item[page].title,
                        modifier = Modifier.padding(top = 20.dp),
                        style= TextStyle(color = Color(0,0,0),
                            fontSize = 30.sp,fontWeight=FontWeight.W700,

                            textAlign = TextAlign.Center)
                    )

                    Text(
                        text = item[page].desc,
                        modifier = Modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp),
                        style= TextStyle(color = Color(221,88,88),
                            fontSize = 18.sp,fontWeight=FontWeight.W700,

                            textAlign = TextAlign.Center)
                    )
                    Text(
                        text = item[page].desc2,
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp),
                        style= TextStyle(color = Color(0,0,0),
                            fontSize = 18.sp,fontWeight=FontWeight.W700,

                            textAlign = TextAlign.Center)

                    )
                    Icon(painter = painterResource(R.drawable.ic_line_1),
                        contentDescription ="",
                        tint=Color(221,88,88) )
                    Text(
                        text = item[page].desc3,
                        modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp),

                        style= TextStyle(color = Color(151,151,151),
                            fontSize = 18.sp,fontWeight=FontWeight.Medium,

                            textAlign = TextAlign.Center)

                    )

                }
            }

            PagerIndicator(item.size, pagerState.currentPage)
        }

        Box(modifier = Modifier.align(Alignment.BottomCenter)){
            BottomSection(pagerState.currentPage,navController)
        }
    }
}

@ExperimentalPagerApi
@Composable
fun rememberPagerState(
    @androidx.annotation.IntRange(from = 0) pageCount: Int,
    @androidx.annotation.IntRange(from = 0) initialPage: Int = 0,
    @FloatRange(from = 0.0, to = 1.0) initialPageOffset: Float = 0f,
    @androidx.annotation.IntRange(from = 1) initialOffscreenLimit: Int = 1,
    infiniteLoop: Boolean = false
): PagerState = rememberSaveable(saver = PagerState.Saver) {
    PagerState(
        pageCount = pageCount,
        currentPage = initialPage,
        currentPageOffset = initialPageOffset,
        offscreenLimit = initialOffscreenLimit,
        infiniteLoop = infiniteLoop
    )
}

@Composable
fun PagerIndicator(size: Int, currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(top = 60.dp)
    ) {
        repeat(size) {
            Indicator(isSelected = it == currentPage)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(targetValue = if (isSelected) 25.dp else 10.dp)

    Box(
        modifier = Modifier
            .padding(1.dp)
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) Color(221, 88, 88) else Color.Gray.copy(alpha = 0.5f)
            )
    )
}

@Composable
fun BottomSection(currentPager: Int,navController: NavController) {

    Row(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = if (currentPager != 2) Arrangement.SpaceBetween else Arrangement.Center
    ) {

        if (currentPager == 2){
            OutlinedButton(
                onClick = {navController.navigate(Screen.SignInScreen.route) },border= BorderStroke(1.dp, Color(221,88,88)),
                shape = RoundedCornerShape(50), // = 40% percent
            ) {
                Text(
                    text = "Get Started",
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp),
                    color = Color(151,151,151), fontSize = 18.sp
                )
            }
        }
        else{
            SkipNextButton(navController)

        }

    }
}

@Composable
fun SkipNextButton(navController: NavController) {
    OutlinedButton(modifier = Modifier.padding(start= 10.dp),
        onClick = {navController.navigate(Screen.SignInScreen.route) },
        border= BorderStroke(1.dp, Color(221,88,88)),
        shape = RoundedCornerShape(50), // = 40% percent
    ) {
        Text(
            text = "Skip",
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp),
            color = Color(151,151,151), fontSize = 18.sp
        )
    }



}

