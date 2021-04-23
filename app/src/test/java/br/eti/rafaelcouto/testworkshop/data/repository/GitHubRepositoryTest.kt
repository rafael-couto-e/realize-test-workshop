package br.eti.rafaelcouto.testworkshop.data.repository

import br.eti.rafaelcouto.testworkshop.data.api.GitHubApi
import br.eti.rafaelcouto.testworkshop.data.model.Profile
import br.eti.rafaelcouto.testworkshop.data.repository.abs.GitHubRepositoryAbs
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockkClass
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException
import retrofit2.Response

@RunWith(JUnit4::class)
class GitHubRepositoryTest {

    private lateinit var sut: GitHubRepositoryAbs
    private lateinit var mockApi: GitHubApi

    private val dummyProfile = Profile()

    @Before
    fun setUp() {

        mockApi = mockkClass(GitHubApi::class)
        sut = GitHubRepository(mockApi)
    }

    @Test
    fun getProfileSuccessTest() = runBlocking {
        val expected = dummyProfile
        coEvery { mockApi.getProfile(any()) }.returns(expected)

        val actual = sut.getProfile("rafael-couto-e")

        coVerify { mockApi.getProfile("rafael-couto-e") }

        assertThat(actual).isEqualTo(expected)
    }

    @Test(expected = HttpException::class)
    fun getProfileFailureTest() {
        runBlocking {
            coEvery { mockApi.getProfile(any()) }.throws(dummyException())

            try {
                sut.getProfile("dummy")
            } catch (e: Exception) {
                assertThat(e.message).isEqualTo("HTTP 404 dummy")
                throw e
            }
        }
    }

    private fun dummyException(): HttpException {
        val response = mockkClass(Response::class)
        every { response.code() }.returns(404)
        every { response.message() }.returns("dummy")
        return HttpException(response)
    }
}