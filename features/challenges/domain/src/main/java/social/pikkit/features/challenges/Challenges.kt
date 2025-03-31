package social.pikkit.features.challenges

object Challenges {

    data class CurrentChallenge(
        val id: Int,
        val pictureUrl: String,
        val title: String,
        val endingTimestamp: Long,
        val numberOfSubmissions: Int,
        val reward: Int
    )

    data class FinishedChallenge(
        val id: Int,
        val pictureUrl: String,
        val title: String,
        val endTimestamp: Long,
        val numberOfSubmissions: Int,
        val numberOfLikes: Int,
        val reward: Int,
    )

    interface GetCurrentChallengeUseCase {
        suspend operator fun invoke(): CurrentChallenge
    }

    interface GetLastFinishedChallengesUseCase {
        suspend operator fun invoke(limit: Int): List<FinishedChallenge>
    }
}