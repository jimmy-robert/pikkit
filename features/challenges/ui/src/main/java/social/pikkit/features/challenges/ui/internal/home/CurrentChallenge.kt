package social.pikkit.features.challenges.ui.internal.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import social.pikkit.core.ui.PikkitDrawables
import social.pikkit.core.ui.theme.LocalDarkMode
import social.pikkit.features.challenges.Challenges

@Composable
internal fun CurrentChallenge(
    challenge: Challenges.CurrentChallenge,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        border = BorderStroke(0.5.dp, Color.Black.copy(alpha = 0.16f)),
        colors = when {
            LocalDarkMode.current -> CardDefaults.cardColors()
            else -> CardDefaults.cardColors(containerColor = Color.White)
        }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
                    .background(color = Color.Black.copy(alpha = 0.2f))
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = challenge.pictureUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                Text(
                    modifier = Modifier
                        .padding(12.dp)
                        .background(
                            color = MaterialTheme.colorScheme.secondary,
                            shape = CircleShape
                        )
                        .padding(horizontal = 8.dp)
                        .align(Alignment.BottomEnd),
                    text = "+${challenge.reward} points",
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 11.sp
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(color = Color.Black.copy(alpha = 0.32f))
            )

            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = challenge.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    modifier = Modifier.alpha(0.5f),
                    text = "Avant 16h • 2k+ participants",
                    fontSize = 12.sp,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 48.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.primary),
                    onClick = { onClick() }
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(PikkitDrawables.icon_pikkit),
                        contentDescription = null
                    )

                    Spacer(Modifier.width(8.dp))

                    Text(
                        text = "Participer à ce Pikkit",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}