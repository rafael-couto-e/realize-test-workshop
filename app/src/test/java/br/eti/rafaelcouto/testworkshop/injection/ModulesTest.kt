package br.eti.rafaelcouto.testworkshop.injection

import android.content.Context
import br.eti.rafaelcouto.testworkshop.data.api.GitHubApi
import br.eti.rafaelcouto.testworkshop.data.repository.GitHubRepository
import br.eti.rafaelcouto.testworkshop.data.repository.abs.GitHubRepositoryAbs
import br.eti.rafaelcouto.testworkshop.domain.mapper.ProfileMapper
import br.eti.rafaelcouto.testworkshop.domain.mapper.abs.ProfileMapperAbs
import br.eti.rafaelcouto.testworkshop.domain.usecase.ProfileUseCase
import br.eti.rafaelcouto.testworkshop.domain.usecase.abs.ProfileUseCaseAbs
import br.eti.rafaelcouto.testworkshop.domain.viewmodel.ProfileViewModel
import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import io.mockk.mockkClass
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import retrofit2.Retrofit

@RunWith(JUnit4::class)
class ModulesTest : KoinTest {

    @Before
    fun setUp() {
        startKoin {
            androidContext(mockkClass(Context::class))
            modules(Modules.all)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun newtorkModulesTest() {
        val retrofit: Retrofit by inject()
        val moshi: Moshi by inject()

        assertThat(retrofit).isNotNull()
        assertThat(moshi).isNotNull()
    }

    @Test
    fun apiModulesTest() {
        val gitHubApi: GitHubApi by inject()

        assertThat(gitHubApi).isNotNull()
    }

    @Test
    fun repositoryModulesTest() {
        val gitHubRepository: GitHubRepositoryAbs by inject()

        assertThat(gitHubRepository).isNotNull()
        assertThat(gitHubRepository).isInstanceOf(GitHubRepository::class.java)
    }

    @Test
    fun mapperModulesTest() {
        val profileMapper: ProfileMapperAbs by inject()

        assertThat(profileMapper).isNotNull()
        assertThat(profileMapper).isInstanceOf(ProfileMapper::class.java)
    }

    @Test
    fun useCaseModulesTest() {
        val profileUseCase: ProfileUseCaseAbs by inject()

        assertThat(profileUseCase).isNotNull()
        assertThat(profileUseCase).isInstanceOf(ProfileUseCase::class.java)
    }

    @Test
    fun viewModelModulesTest() {
        val profileViewModel: ProfileViewModel by inject()

        assertThat(profileViewModel).isNotNull()
    }
}