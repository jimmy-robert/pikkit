package social.pikkit.features.challenges.ui.internal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import social.pikkit.features.challenges.Challenges

@KoinViewModel
internal class ChallengesViewModel(
    private val getCurrentChallengeUseCase: Challenges.GetCurrentChallengeUseCase,
    private val getLastFinishedChallengesUseCase: Challenges.GetLastFinishedChallengesUseCase,
) : ViewModel() {

    private val mutableIsLoading = MutableStateFlow(false)
    val isLoading = mutableIsLoading.asStateFlow()

    private val mutableCurrentChallenge = MutableStateFlow<Challenges.CurrentChallenge?>(null)
    val currentChallenge = mutableCurrentChallenge.asStateFlow()

    private val mutableLastFinishedChallenges = MutableStateFlow<List<Challenges.FinishedChallenge>?>(null)
    val lastFinishedChallenges = mutableLastFinishedChallenges.asStateFlow()

    fun refresh(force: Boolean) = viewModelScope.launch {
        if (force || mutableCurrentChallenge.value == null || mutableLastFinishedChallenges.value == null) {
            try {
                mutableIsLoading.value = true

                val currentDeferred = async { getCurrentChallengeUseCase() }
                val lastFinishedDeferred = async { getLastFinishedChallengesUseCase(5) }

                val currentChallenge = currentDeferred.await()
                val lastFinishedChallenges = lastFinishedDeferred.await()

                mutableCurrentChallenge.value = currentChallenge
                mutableLastFinishedChallenges.value = lastFinishedChallenges
            } finally {
                mutableIsLoading.value = false
            }
        }
    }
}