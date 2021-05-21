package br.eti.rafaelcouto.testworkshop.domain.usecase

import br.eti.rafaelcouto.testworkshop.data.model.Profile
import br.eti.rafaelcouto.testworkshop.data.repository.abs.GitHubRepositoryAbs
import br.eti.rafaelcouto.testworkshop.domain.mapper.abs.ProfileMapperAbs
import br.eti.rafaelcouto.testworkshop.domain.uimodel.ProfileUI
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockkClass
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

class ProfileUseCaseTest {
    private lateinit var sut: ProfileUseCase
    private lateinit var repository: GitHubRepositoryAbs
    private lateinit var mapper: ProfileMapperAbs

    @Before
    fun setUp() {
        repository = mockkClass(GitHubRepositoryAbs::class)
        mapper = mockkClass(ProfileMapperAbs::class)
        sut = ProfileUseCase(repository, mapper)
    }

    @Test
    fun getProfileSuccessTest() = runBlocking {
        val userName = "userName"
        val input = Profile().apply {
            name = "Test"
            company = ""
            blog = ""
            location = ""
            followers = 5
            following = 5
        }

        val expected = ProfileUI(
                "Test",
                "",
                "",
                "",
                "5",
                "5",
                "5",
                "2021-05-05"
        )

        coEvery { repository.getProfile(any()) }.returns(input)
        every { mapper.map(any()) }.returns(expected)

        val result = sut.getUserProfile(userName)
        coVerify { repository.getProfile(userName) }

        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun getProfileErrorTest() {
        val userName = "userName"
        coEvery { repository.getProfile(any()) }.throws(Exception())
        runBlocking {
            sut.getUserProfile(userName)
        }
    }
}
