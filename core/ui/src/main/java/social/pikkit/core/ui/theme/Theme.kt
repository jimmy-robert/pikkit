package social.pikkit.core.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.core.view.WindowCompat
import social.pikkit.core.ui.PikkitUI
import social.pikkit.core.ui.R.font.blinker_black
import social.pikkit.core.ui.R.font.blinker_bold
import social.pikkit.core.ui.R.font.blinker_extrabold
import social.pikkit.core.ui.R.font.blinker_extralight
import social.pikkit.core.ui.R.font.blinker_light
import social.pikkit.core.ui.R.font.blinker_regular
import social.pikkit.core.ui.R.font.blinker_semibold
import social.pikkit.core.ui.R.font.blinker_thin

@Composable
fun PikkitUI.Companion.Theme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val activity = LocalView.current.context as? Activity
    LaunchedEffect(activity, darkTheme) {
        activity?.window
            ?.let { WindowCompat.getInsetsController(it, it.decorView) }
            ?.apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
    }

    CompositionLocalProvider(LocalDarkMode provides darkTheme) {
        MaterialTheme(
            colorScheme = when {
                darkTheme -> darkColorScheme
                else -> lightColorScheme
            },
            typography = typography,
            content = content
        )
    }
}

private val lightColorScheme = lightColorScheme(
    primary = Color(0xFFFE851D),
    onPrimary = Color.White,
    secondary = Color(0xFFFEB728),
    onSecondary = Color.White,
    surfaceContainer = Color.White
)

private val darkColorScheme = darkColorScheme(
    primary = Color(0xFFFE851D),
    onPrimary = Color(0xFF462100),
    secondary = Color(0xFFFEB728),
    onSecondary = Color(0xFF493100),
)

private val blinker = FontFamily(
    Font(resId = blinker_thin, weight = FontWeight(100)),
    Font(resId = blinker_extralight, weight = FontWeight(200)),
    Font(resId = blinker_light, weight = FontWeight(300)),
    Font(resId = blinker_regular, weight = FontWeight(400)),
    Font(resId = blinker_semibold, weight = FontWeight(600)),
    Font(resId = blinker_bold, weight = FontWeight(700)),
    Font(resId = blinker_extrabold, weight = FontWeight(800)),
    Font(resId = blinker_black, weight = FontWeight(900)),
)

private val typography = Typography().run {
    copy(
        displayLarge = displayLarge.copy(fontFamily = blinker),
        displayMedium = displayMedium.copy(fontFamily = blinker),
        displaySmall = displaySmall.copy(fontFamily = blinker),
        headlineLarge = headlineLarge.copy(fontFamily = blinker),
        headlineMedium = headlineMedium.copy(fontFamily = blinker),
        headlineSmall = headlineSmall.copy(fontFamily = blinker),
        titleLarge = titleLarge.copy(fontFamily = blinker),
        titleMedium = titleMedium.copy(fontFamily = blinker),
        titleSmall = titleSmall.copy(fontFamily = blinker),
        bodyLarge = bodyLarge.copy(fontFamily = blinker),
        bodyMedium = bodyMedium.copy(fontFamily = blinker),
        bodySmall = bodySmall.copy(fontFamily = blinker),
        labelLarge = labelLarge.copy(fontFamily = blinker),
        labelMedium = labelMedium.copy(fontFamily = blinker),
        labelSmall = labelSmall.copy(fontFamily = blinker),
    )
}