package social.pikkit.features.settings.ui.internal.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import social.pikkit.core.ui.PikkitDrawables
import social.pikkit.core.ui.theme.LocalDarkMode
import social.pikkit.features.settings.Settings
import social.pikkit.features.settings.ui.internal.SettingsRoutes

@Composable
internal fun SettingsHomeScreen(
    viewModel: SettingsHomeViewModel = koinViewModel(),
    navController: NavController
) {
    val currentTheme by viewModel.themeState.collectAsStateWithLifecycle()

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(it)
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Image(
                modifier = Modifier.align(Alignment.CenterHorizontally).size(120.dp),
                painter = painterResource(PikkitDrawables.icon_pikkit),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
            )

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Pikkit",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .alpha(0.5f),
                text = "Version 0.0.0",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(24.dp))

            SingleChoiceSegmentedButtonRow(
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {

                viewModel.themeEntries.forEachIndexed { index, theme ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = viewModel.themeEntries.size
                        ),
                        selected = currentTheme == theme,
                        onClick = { viewModel.setTheme(theme) }
                    ) {
                        Text(
                            text = when (theme) {
                                Settings.Theme.Light -> "Clair"
                                Settings.Theme.Dark -> "Sombre"
                                Settings.Theme.System -> "Système"
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Mon compte",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(0.5.dp, Color.Black.copy(alpha = 0.16f)),
                colors = when {
                    LocalDarkMode.current -> CardDefaults.cardColors()
                    else -> CardDefaults.cardColors(containerColor = Color.White)
                }
            ) {
                Column {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate(SettingsRoutes.WorkInProgress) }
                            .padding(16.dp),
                        text = "Changer mon adresse e-mail"
                    )

                    HorizontalDivider(thickness = 0.5.dp)

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate(SettingsRoutes.WorkInProgress) }
                            .padding(16.dp),
                        text = "Changer mon mot de passe"
                    )

                    HorizontalDivider(thickness = 0.5.dp)

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate(SettingsRoutes.WorkInProgress) }
                            .padding(16.dp),
                        text = "Me déconnecter"
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "L'application",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(0.5.dp, Color.Black.copy(alpha = 0.16f)),
                colors = when {
                    LocalDarkMode.current -> CardDefaults.cardColors()
                    else -> CardDefaults.cardColors(containerColor = Color.White)
                }
            ) {
                Column {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate(SettingsRoutes.WorkInProgress) }
                            .padding(16.dp),
                        text = "Voir l'application sur Google Play"
                    )

                    HorizontalDivider(thickness = 0.5.dp)

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate(SettingsRoutes.WorkInProgress) }
                            .padding(16.dp),
                        text = "Accessibilité"
                    )

                    HorizontalDivider(thickness = 0.5.dp)

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate(SettingsRoutes.WorkInProgress) }
                            .padding(16.dp),
                        text = "Traitement des données personnelles"
                    )

                    HorizontalDivider(thickness = 0.5.dp)

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate(SettingsRoutes.WorkInProgress) }
                            .padding(16.dp),
                        text = "Conditions générales d'utilisation"
                    )
                }
            }
        }
    }
}