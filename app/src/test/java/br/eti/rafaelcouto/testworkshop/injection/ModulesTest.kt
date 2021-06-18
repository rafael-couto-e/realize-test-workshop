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
import com.google.common.truth.Truth
import com.squareup.moshi.Moshi
import io.mockk.mockkClass
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.inject
import org.koin.test.KoinTest
import org.koin.test.inject
import retrofit2.Retrofit

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
    fun networkModulesTest() {
        val moshiExpected: Moshi by inject()
        val retrofitExpected: Retrofit by inject()

        Truth.assertThat(moshiExpected).isNotNull()
        Truth.assertThat(retrofitExpected).isNotNull()
    }

    @Test
    fun apiModulesTest() {
        val apiExpected: GitHubApi by inject()

        Truth.assertThat(apiExpected).isNotNull()
    }

    @Test
    fun repositoryModulesTest() {
        val repositoryExpected: GitHubRepositoryAbs by inject()

        Truth.assertThat(repositoryExpected).isInstanceOf(GitHubRepository::class.java)
    }

    @Test
    fun mapperModulesTest() {
        val mapperExpected: ProfileMapperAbs by inject()

        Truth.assertThat(mapperExpected).isInstanceOf(ProfileMapper::class.java)
    }

    @Test
    fun useCaseModulesTest() {
        val useCaseExpected: ProfileUseCaseAbs by inject()

        Truth.assertThat(useCaseExpected).isInstanceOf(ProfileUseCase::class.java)
    }

    @Test
    fun viewModelModulesTest() {
        val viewModelExpected: ProfileViewModel by inject()

        Truth.assertThat(viewModelExpected).isNotNull()
    }
}
