package social.pikkit.features.challenges.data.internal.usecases

import org.koin.core.annotation.Factory
import social.pikkit.features.challenges.Challenges

@Factory
internal class GetCurrentChallengeUseCase(
    private val challengesRepository: ChallengesRepository
) : Challenges.GetCurrentChallengeUseCase {

    override suspend fun invoke() = challengesRepository.getCurrentChallenge()
}

@Factory
internal class GetLastFinishedChallengesUseCase(
    private val challengesRepository: ChallengesRepository
) : Challenges.GetLastFinishedChallengesUseCase {

    override suspend fun invoke(limit: Int) = challengesRepository.getFinishedChallenges(limit)
}