package social.pikkit.features.challenges.data.internal.usecases

import kotlinx.coroutines.delay
import org.koin.core.annotation.Singleton
import social.pikkit.features.challenges.Challenges
import java.util.Calendar

@Singleton
internal class ChallengesRepository {
    suspend fun getCurrentChallenge(): Challenges.CurrentChallenge {
        delay(1000) // Simulate request
        return randomChallenge()
    }

    suspend fun getFinishedChallenges(limit: Int): List<Challenges.FinishedChallenge> {
        delay(1000) // Simulate request
        return (0 until limit).map { randomFinishedChallenge(it) }
    }

    private fun randomChallenge(): Challenges.CurrentChallenge {
        val id = randomId()
        return Challenges.CurrentChallenge(
            id = id,
            pictureUrl = randomPictureUrl(),
            title = randomTitle(),
            endingTimestamp = randomEndingTimestamp(0),
            numberOfSubmissions = randomNumberOfSubmission(),
            reward = randomReward(),
        )
    }

    private fun randomFinishedChallenge(daysBefore: Int): Challenges.FinishedChallenge {
        val id = randomId()
        return Challenges.FinishedChallenge(
            id = id,
            pictureUrl = randomPictureUrl(),
            title = randomTitle(),
            endTimestamp = randomEndingTimestamp(daysBefore),
            numberOfSubmissions = randomNumberOfSubmission(),
            numberOfLikes = randomNumberOfLikes(),
            reward = randomReward(),
        )
    }

    private fun randomId() = (0..1000).random()
    private fun randomPictureUrl() = "https://picsum.photos/seed/${randomId()}/800/800"
    private fun randomTitle() = "Pikkit #${randomId()}: prends une photo de Lorem Ipsum"
    private fun randomEndingTimestamp(daysBefore: Int) = Calendar.getInstance().let {
        it.add(Calendar.DAY_OF_MONTH, -daysBefore)
        it.set(Calendar.HOUR_OF_DAY, (14..18).random())
        it.set(Calendar.MINUTE, 0)
        it.timeInMillis
    }
    private fun randomNumberOfSubmission() = (0..10000).random()
    private fun randomNumberOfLikes() = (0..10000).random()
    private fun randomReward() = (1..9).random() * 100
}