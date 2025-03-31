package social.pikkit.features.challenges.ui.internal

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import social.pikkit.core.ui.PikkitDrawables
import social.pikkit.features.challenges.ui.internal.home.CurrentChallenge
import social.pikkit.features.challenges.ui.internal.home.FinishedChallenge

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun ChallengesScreen(
    viewModel: ChallengesViewModel = koinViewModel(),
    onStart: () -> Unit
) {
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val currentChallenge by viewModel.currentChallenge.collectAsStateWithLifecycle()
    val lastFinishedChallenges by viewModel.lastFinishedChallenges.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.refresh(force = false)
    }

    Scaffold { padding ->
        PullToRefreshBox(
            isRefreshing = isLoading,
            onRefresh = { viewModel.refresh(force = true) }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    Box(
                        modifier = Modifier.size(56.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp)
                                .clip(CircleShape)
                                .background(color = Color.Red),
                            painter = painterResource(PikkitDrawables.user_profile_picture),
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxSize(),
                            trackColor = Color.Transparent,
                            progress = { 0.75f }
                        )
                    }
                }

                AnimatedContent(
                    targetState = currentChallenge,
                ) { challenge ->
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (challenge != null) {
                            Spacer(modifier = Modifier.height(20.dp))

                            Text(
                                text = "En ce moment",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.SemiBold
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            CurrentChallenge(
                                modifier = Modifier.fillMaxWidth(),
                                challenge = challenge,
                                onClick = { onStart() }
                            )
                        }
                    }
                }

                AnimatedContent(
                    targetState = lastFinishedChallenges,
                ) { challenges ->
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (!challenges.isNullOrEmpty()) {
                            Spacer(modifier = Modifier.height(36.dp))

                            Text(
                                text = "Pikkits terminés",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.SemiBold
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            challenges.forEachIndexed { index, challenge ->
                                if (index > 0) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                }

                                FinishedChallenge(
                                    modifier = Modifier.fillMaxWidth(),
                                    challenge = challenge,
                                    onClick = {}
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .clickable { }
                                    .defaultMinSize(minHeight = 48.dp)
                                    .padding(horizontal = 16.dp, vertical = 4.dp)
                                    .align(Alignment.CenterHorizontally),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    modifier = Modifier.alpha(0.5f),
                                    text = "VOIR PLUS",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}