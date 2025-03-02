package compose.jetpack.bigcart.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import compose.jetpack.bigcart.ui.theme.ArchShape
import compose.jetpack.bigcart.ui.utils.ButtonUI
import compose.jetpack.bigcart.ui.utils.IndicatorUI

@Composable
fun OnBoardingGraphUI(
    modifier: Modifier = Modifier,
    onBoardingModel: OnBoardingModel,
    pageSize: Int,
    currentPage: Int,
    onFinishedClicked: () -> Unit,
    onNextClicked: () -> Unit
) {
    var screenSize: Size by remember { mutableStateOf(Size.Zero) }
    var imageSize: Size by remember { mutableStateOf(Size.Zero) }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                pageSize = pageSize,
                currentPage = currentPage,
                onFinishedClicked = onFinishedClicked,
                onNextClicked = onNextClicked
            )
        }
    ) { innerPadding ->
        Box(modifier
            .fillMaxSize()
            .padding(innerPadding)
            .onGloballyPositioned { layoutCoordinates ->
                screenSize = layoutCoordinates.size.toSize()
            }) {

            //image of the onBoarding Screen
            Image(painter = painterResource(onBoardingModel.image),
                contentScale = ContentScale.Crop,
                contentDescription = onBoardingModel.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { layoutCoordinates ->
                        imageSize = layoutCoordinates.size.toSize()
                    })

            //Bottom sheet of the on Boarding ui
            OnBoardingBottomSheet(
                modifier = Modifier.padding(top = 25.dp),
                onBoardingModel = onBoardingModel,
                imageSize = imageSize,
                screenSize = screenSize,
            )


        }
    }
}

@Composable
fun OnBoardingBottomSheet(

    onBoardingModel: OnBoardingModel,
    imageSize: Size,
    screenSize: Size,
    modifier: Modifier = Modifier
) {

    val density = LocalDensity.current.density
    Column(
        modifier
            .fillMaxWidth()
            .height(((screenSize.height - imageSize.height + 0.25f * imageSize.width) / density).dp)
            .offset(y = (imageSize.height / density - (0.25f) * imageSize.width / density).dp)
            .clip(ArchShape())
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //Title Text
        Text(
            text = onBoardingModel.title,
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center
        )

        //Description Text
        Text(
            text = onBoardingModel.desc,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun BottomAppBar(
    modifier: Modifier = Modifier,
    pageSize: Int,
    currentPage: Int,
    onFinishedClicked: () -> Unit,
    onNextClicked: () -> Unit
) {

    //Buttons to skip or continue
    if (pageSize - currentPage != 1) Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ButtonUI(
            text = "Skip",
            onClick = onFinishedClicked,
            backgroundColor = Color.Transparent,
            textColor = Color.Gray,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 13
        )

        //Indicator to display the page position
        IndicatorUI(pageSize, currentPage)

        ButtonUI(text = "Next", onClick = onNextClicked)
    }
    else ButtonUI(
        text = "Start", onClick = onFinishedClicked, modifier = Modifier.fillMaxWidth()
    )
}

