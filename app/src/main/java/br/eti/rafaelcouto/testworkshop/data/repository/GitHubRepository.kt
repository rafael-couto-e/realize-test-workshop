package br.eti.rafaelcouto.testworkshop.data.repository

import br.eti.rafaelcouto.testworkshop.data.api.GitHubApi
import br.eti.rafaelcouto.testworkshop.data.repository.abs.GitHubRepositoryAbs

class GitHubRepository(
    private val api: GitHubApi
) : GitHubRepositoryAbs {

    override suspend fun getProfile(userName: String) = api.getProfile(userName)
}