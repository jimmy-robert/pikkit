package social.pikkit.features.challenges.ui.internal.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil3.compose.AsyncImage
import social.pikkit.core.ui.PikkitDrawables
import social.pikkit.core.ui.theme.LocalDarkMode
import social.pikkit.features.challenges.Challenges

@Composable
internal fun FinishedChallenge(
    challenge: Challenges.FinishedChallenge,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(0.5.dp, Color.Black.copy(alpha = 0.16f)),
        colors = when {
            LocalDarkMode.current -> CardDefaults.cardColors()
            else -> CardDefaults.cardColors(containerColor = Color.White)
        }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
        ) {
            val (image, text) = createRefs()

            AsyncImage(
                modifier = Modifier
                    .constrainAs(image) {
                        width = 88.dp.asDimension()
                        height = Dimension.fillToConstraints

                        start.linkTo(parent.start)
                        end.linkTo(text.start)

                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .background(color = Color.Black.copy(alpha = 0.2f)),
                model = challenge.pictureUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .constrainAs(text) {
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent

                        start.linkTo(image.end)
                        end.linkTo(parent.end)

                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    modifier = Modifier
                        .alpha(0.5f)
                        .padding(horizontal = 12.dp),
                    text = "Terminé sam. 29 mars 2025 à 16h",
                    fontSize = 11.sp,
                    lineHeight = 12.sp
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 12.dp),
                    text = challenge.title,
                    lineHeight = 16.sp
                )

                Row(
                    modifier = Modifier
                        .padding(
                            horizontal = 12.dp,
                            vertical = 8.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(PikkitDrawables.icon_pikkit),
                        contentDescription = null
                    )

                    Spacer(Modifier.width(8.dp))

                    Text(
                        text = "15k+",
                        fontSize = 14.sp
                    )

                    Spacer(Modifier.width(20.dp))

                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null
                    )

                    Spacer(Modifier.width(8.dp))

                    Text(
                        text = "23k+",
                        fontSize = 14.sp
                    )

                    Spacer(Modifier.weight(1f))

                    Row(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.secondary,
                                shape = CircleShape
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(4.dp))

                        Icon(
                            modifier = Modifier.size(16.dp),
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSecondary
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = "${challenge.reward} points",
                            color = MaterialTheme.colorScheme.onSecondary,
                            fontSize = 11.sp,
                        )

                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }
    }
}