package br.eti.rafaelcouto.testworkshop.domain.usecase

import br.eti.rafaelcouto.testworkshop.data.repository.abs.GitHubRepositoryAbs
import br.eti.rafaelcouto.testworkshop.domain.mapper.abs.ProfileMapperAbs
import br.eti.rafaelcouto.testworkshop.domain.usecase.abs.ProfileUseCaseAbs

class ProfileUseCase(
    private val repository: GitHubRepositoryAbs,
    private val mapper: ProfileMapperAbs
) : ProfileUseCaseAbs {

    override suspend fun getUserProfile(username: String) = mapper.map(
        repository.getProfile(username)
    )
}