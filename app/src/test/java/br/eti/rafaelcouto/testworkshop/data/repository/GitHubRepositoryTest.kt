package br.eti.rafaelcouto.testworkshop.data.repository

import br.eti.rafaelcouto.testworkshop.data.api.GitHubApi
import br.eti.rafaelcouto.testworkshop.data.model.Profile
import br.eti.rafaelcouto.testworkshop.data.repository.abs.GitHubRepositoryAbs
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockkClass
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GitHubRepositoryTest {
    private lateinit var mockApi: GitHubApi
    private lateinit var sut: GitHubRepositoryAbs

    @Before
    fun start() {
        mockApi = mockkClass(GitHubApi::class)
        sut = GitHubRepository(mockApi)
    }


    @Test
    fun getProfile() = runBlocking {
        val userName = "userName"
        val expected = Profile().apply {
            name = "Test"
            company = ""
            blog = ""
            location = ""
            followers = 5
            following = 5
        }

        coEvery { mockApi.getProfile(any()) }.returns(expected)
        val result = sut.getProfile(userName)
        coVerify { mockApi.getProfile(userName) }

        Truth.assertThat(result.name).isEqualTo(expected.name)
    }
}
