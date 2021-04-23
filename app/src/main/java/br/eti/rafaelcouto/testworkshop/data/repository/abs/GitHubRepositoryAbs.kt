package br.eti.rafaelcouto.testworkshop.data.repository.abs

import br.eti.rafaelcouto.testworkshop.data.model.Profile

interface GitHubRepositoryAbs {

    suspend fun getProfile(userName: String): Profile
}