package br.eti.rafaelcouto.testworkshop.domain.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.eti.rafaelcouto.testworkshop.CoroutinesTestRule
import br.eti.rafaelcouto.testworkshop.domain.uimodel.ProfileUI
import br.eti.rafaelcouto.testworkshop.domain.usecase.abs.ProfileUseCaseAbs
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockkClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProfileViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule @ExperimentalCoroutinesApi
    val coroutineRule = CoroutinesTestRule()

    private lateinit var sut: ProfileViewModel
    private lateinit var useCase: ProfileUseCaseAbs

    private val errorSequence = mutableListOf<Boolean>()
    private val loadingSequence = mutableListOf<Boolean>()

    private val dummyProfileUI = ProfileUI(
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
        useCase = mockkClass(ProfileUseCaseAbs::class)

        sut = ProfileViewModel(useCase).apply {
            hasError.observeForever { errorSequence.add(it) }
            isLoading.observeForever { loadingSequence.add(it) }
            userProfile.observeForever {}
        }

        errorSequence.clear()
        loadingSequence.clear()
    }

    @Test
    fun loadProfileDataSuccessTest() {
        val expected = dummyProfileUI
        coEvery { useCase.getUserProfile(any()) }.returns(expected)

        sut.loadData()

        val actual = sut.userProfile.value

        assertThat(actual).isEqualTo(expected)
        assertThat(loadingSequence).isEqualTo(mutableListOf(true, false))
        assertThat(errorSequence).isEqualTo(mutableListOf(false))
    }

    @Test
    fun loadProfileDataFailureTest() {
        coEvery { useCase.getUserProfile(any()) }.throws(Exception())

        sut.loadData()

        assertThat(sut.userProfile.value).isNull()
        assertThat(loadingSequence).isEqualTo(mutableListOf(true, false))
        assertThat(errorSequence).isEqualTo(mutableListOf(false, true))
    }
}