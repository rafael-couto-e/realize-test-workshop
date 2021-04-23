package br.eti.rafaelcouto.testworkshop.domain.usecase

import br.eti.rafaelcouto.testworkshop.data.model.Profile
import br.eti.rafaelcouto.testworkshop.data.repository.abs.GitHubRepositoryAbs
import br.eti.rafaelcouto.testworkshop.domain.mapper.abs.ProfileMapperAbs
import br.eti.rafaelcouto.testworkshop.domain.uimodel.ProfileUI
import br.eti.rafaelcouto.testworkshop.domain.usecase.abs.ProfileUseCaseAbs
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException
import retrofit2.Response

@RunWith(JUnit4::class)
class ProfileUseCaseTest {

    private lateinit var sut: ProfileUseCaseAbs
    private lateinit var repository: GitHubRepositoryAbs
    private lateinit var mapper: ProfileMapperAbs

    private val dummyProfile = ProfileUI(
        "Rafael Couto",
        "Deliver IT",
        "Brazil",
        "Android | iOS Dev",
        "23",
        "11",
        "14",
        "26/04/2016"
    )

    @Before
    fun setUp() {
        repository = mockkClass(GitHubRepositoryAbs::class)
        mapper = mockkClass(ProfileMapperAbs::class)
        sut = ProfileUseCase(repository, mapper)
    }

    @Test
    fun getProfileSuccessTest() = runBlocking {
        val expected = dummyProfile
        coEvery { repository.getProfile(any()) }.returns(Profile())
        coEvery { mapper.map(any()) }.returns(expected)
        val actual = sut.getUserProfile("rafael-couto-e")

        coVerify { repository.getProfile("rafael-couto-e") }

        assertThat(actual).isEqualTo(expected)
    }

    @Test(expected = HttpException::class)
    fun getProfileFailureTest() {
        runBlocking {
            coEvery { repository.getProfile(any()) }.throws(dummyException())
            every { mapper.map(any()) }.returns(dummyProfile)

            try {
                sut.getUserProfile("rafael-couto-e")
            } catch (e: Exception) {
                coVerify { repository.getProfile("rafael-couto-e") }
                verify(exactly = 0) { mapper.map(any()) }
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