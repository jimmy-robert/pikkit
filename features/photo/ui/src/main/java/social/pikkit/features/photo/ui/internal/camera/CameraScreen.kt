package social.pikkit.features.photo.ui.internal.camera

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.ujizin.camposer.CameraPreview
import com.ujizin.camposer.extensions.takePicture
import com.ujizin.camposer.state.CamSelector
import com.ujizin.camposer.state.rememberCamSelector
import com.ujizin.camposer.state.rememberCameraState
import kotlinx.coroutines.launch
import java.io.File

@Composable
@OptIn(ExperimentalPermissionsApi::class)
internal fun CameraScreen(
    onCapture: (String) -> Unit,
    onCancel: () -> Unit
) {
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )

    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            if (cameraPermissionState.status.isGranted) {
                Camera(
                    padding = padding,
                    onCapture = onCapture,
                    onCancel = onCancel
                )
            } else {
                RequestCameraPermission(
                    shouldShowRationale = cameraPermissionState.status.shouldShowRationale
                ) { cameraPermissionState.launchPermissionRequest() }
            }
        }
    }
}

@Composable
private fun Camera(
    padding: PaddingValues,
    onCapture: (String) -> Unit,
    onCancel: () -> Unit
) {
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()

    val cameraState = rememberCameraState()
    var camSelector by rememberCamSelector(CamSelector.Front)

    var zoomRatio by remember { mutableFloatStateOf(1f) }

    val temporaryFile = remember { File(context.filesDir, "current-capture") }

    CameraPreview(
        modifier = Modifier.fillMaxSize(),
        cameraState = cameraState,
        camSelector = camSelector,
        zoomRatio = zoomRatio,
        onZoomRatioChanged = { zoomRatio = it }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(bottom = 96.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(48.dp)
                        .clip(shape = CircleShape)
                        .clickable { onCancel() },
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color.White
                )

                Box(
                    modifier = Modifier
                        .size(96.dp)
                        .background(
                            color = Color.Black.copy(alpha = 0.2f),
                            shape = CircleShape
                        )
                        .padding(2.dp)
                        .border(
                            border = BorderStroke(width = 8.dp, color = Color.White),
                            shape = CircleShape
                        )
                        .clip(shape = CircleShape)
                        .clickable {
                            coroutineScope.launch {
                                cameraState.takePicture(temporaryFile)?.let {
                                    onCapture(it.toString())
                                }
                            }
                        }
                )

                Icon(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(48.dp)
                        .clip(shape = CircleShape)
                        .clickable {
                            camSelector = camSelector.inverse
                        },
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}

