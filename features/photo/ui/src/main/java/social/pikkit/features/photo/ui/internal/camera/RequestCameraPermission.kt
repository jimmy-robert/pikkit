package social.pikkit.features.photo.ui.internal.camera

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import social.pikkit.core.ui.PikkitDrawables

@Composable
fun RequestCameraPermission(
    shouldShowRationale: Boolean,
    onRequest: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            modifier = Modifier
                .fillMaxWidth(0.25f),
            painter = painterResource(PikkitDrawables.icon_pikkit),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = when {
                shouldShowRationale -> "Pour participer à Pikkit vous devez autoriser l'accès à la caméra."
                else -> "Cette fonctionnalité nécessite l'accès à la caméra."
            },
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier
                .defaultMinSize(minHeight = 48.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.primary),
            onClick = { onRequest() }
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(PikkitDrawables.icon_pikkit),
                contentDescription = null
            )

            Spacer(Modifier.width(8.dp))

            Text(
                text = "Autoriser l'accès à la caméra",
                fontSize = 16.sp
            )
        }
    }
}