package compose.jetpack.bigcart.ui.screens.onboarding

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun OnBoardingScreen(onFinishedClicked: () -> Unit) {
    val pages: List<OnBoardingModel> = listOf(
        OnBoardingModel.FirstPage,
        OnBoardingModel.SecondPage,
        OnBoardingModel.ThirdPage,
        OnBoardingModel.FourthPage
    )

    val pageState = rememberPagerState(initialPage = 0) {
        pages.size
    }

    val scope: CoroutineScope = rememberCoroutineScope()

    HorizontalPager(state = pageState) { index ->
        OnBoardingGraphUI(
            onBoardingModel = pages[index],
            pageSize = pages.size,
            currentPage = index,
            onNextClicked = {scope.launch { pageState.animateScrollToPage(index + 1) }},
            onFinishedClicked = onFinishedClicked
        )
    }
}
