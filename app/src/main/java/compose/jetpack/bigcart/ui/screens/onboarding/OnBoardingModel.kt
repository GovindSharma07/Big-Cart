package compose.jetpack.bigcart.ui.screens.onboarding

import androidx.annotation.DrawableRes
import compose.jetpack.bigcart.R

sealed class OnBoardingModel(
    @DrawableRes val image: Int,
    val title: String,
    val desc: String
) {
    data object FirstPage : OnBoardingModel(
        image = R.drawable.on_boarding_1,
        title = "Welcome to Big Cart",
        desc = "Get Fast Grocery at your Door Step"
    )

    data object SecondPage : OnBoardingModel(
        image = R.drawable.on_boarding_2,
        title = "Buy Quality Dairy Product",
        desc = "Fresh, pure, and delicious – delivered right to your door!"
    )

    data object ThirdPage : OnBoardingModel(
        image = R.drawable.on_boarding_3,
        title = "Buy Premium Quality Fruits",
        desc = "Fresh, juicy, and handpicked for you!"
    )

    data object FourthPage : OnBoardingModel(
        image = R.drawable.on_boarding_4,
        title = "Get Discounts On All Products ",
        desc = "Shop more, save more – don't miss out!"
    )

}