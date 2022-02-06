package org.realestate.avenuerealestateapp
//Component  by Precious Tsetekani(BIT-029-18)

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp


@Composable
fun RoundImage(
    image: Painter,
    contentScale: ContentScale= ContentScale.Crop,
    modifier: Modifier = Modifier,


) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier.size(86.dp)
            .clip(CircleShape).background(Color.White), contentScale = ContentScale.Crop
    )
}
