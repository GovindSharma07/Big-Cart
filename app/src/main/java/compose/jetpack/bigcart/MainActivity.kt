package compose.jetpack.bigcart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import compose.jetpack.bigcart.ui.screens.Authentication.SignUp
import compose.jetpack.bigcart.ui.screens.onboarding.OnBoardingScreen
import compose.jetpack.bigcart.ui.theme.BigCartTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            BigCartTheme {
                val scope: CoroutineScope = rememberCoroutineScope()
                var screentest by remember { mutableStateOf(true) }

                if (screentest)
                    OnBoardingScreen {
                        scope.launch {
                            screentest = false
                        }
                    }
                else SignUp()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    BigCartTheme {
        Greeting("Android")
    }
}