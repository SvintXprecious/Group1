package org.realestate.avenuerealestateapp


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun PropertyView(){
    Card(modifier = Modifier.fillMaxSize(),shape= RectangleShape) {

        Image(
            painter = painterResource(id = R.drawable.property1),
            contentDescription = "Property view",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillHeight
        )

        Box(modifier = Modifier.fillMaxSize().padding(top = 15.dp))
        {

            IconButton(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start=20.dp)
            )
            {
                Icon(
                    painter = painterResource(id = R.drawable.ic_eva_arrow_ios_bac_outline),
                    contentDescription = "",
                    tint = Color(221, 88, 88),
                    modifier = Modifier.size(35.dp)
                )
            }

            IconButton(onClick = {}, modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end=20.dp))
            {
                Icon(
                    painter = painterResource(id = R.drawable.ic_carbon_overflow_menu_vertical),
                    contentDescription = "",
                    tint = Color(221, 88, 88),
                    modifier = Modifier.size(35.dp)
                )
            }

            IconButton(
                onClick = {},
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 10.dp)
            )
            {
                Box(
                    modifier = Modifier
                        .width(120.dp).height(40.dp)
                        .border(1.dp, Color.White, RoundedCornerShape(30.dp)),
                    contentAlignment = Alignment.Center
                )
                {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_eva_arrow_ios_up_outline),
                        contentDescription = "",
                        tint = Color(221, 88, 88),
                        modifier = Modifier.size(40.dp)
                    )

                }

            }
        }
    }
}

















