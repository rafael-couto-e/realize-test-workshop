package br.eti.rafaelcouto.testworkshop.domain.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.eti.rafaelcouto.testworkshop.CoroutinesTestRule
import br.eti.rafaelcouto.testworkshop.domain.uimodel.ProfileUI
import br.eti.rafaelcouto.testworkshop.domain.usecase.abs.ProfileUseCaseAbs
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockkClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProfileViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule @ExperimentalCoroutinesApi
    var coroutineRule = CoroutinesTestRule()

    private lateinit var sut: ProfileViewModel
    private lateinit var mockUseCase: ProfileUseCaseAbs
    private var loadingLiveData = mutableListOf<Boolean>()
    private var errorLiveData = mutableListOf<Boolean>()

    @Before
    fun setUp() {
        mockUseCase = mockkClass(ProfileUseCaseAbs::class)
        sut = ProfileViewModel(mockUseCase)
        sut.hasError.observeForever { errorLiveData.add(it) }
        sut.isLoading.observeForever { loadingLiveData.add(it) }
        loadingLiveData.clear()
        errorLiveData.clear()
    }

    @Test
    fun loadProfileDataSuccessTest() {
        val userName = "rafael-couto-e"
        coEvery { mockUseCase.getUserProfile(userName) }.returns(dummyResponse())
        sut.loadData()
        val result = sut.userProfile.value
        coVerify { mockUseCase.getUserProfile(userName) }
        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result).isInstanceOf(ProfileUI::class.java)
        Truth.assertThat(result?.userName).isEqualTo("rafael-couto-e")
        Truth.assertThat(result?.userCompany).isEqualTo("")
        Truth.assertThat(result?.location).isEqualTo("")
        Truth.assertThat(result?.bio).isEqualTo("")
        Truth.assertThat(result?.repoCount).isEqualTo("5")
        Truth.assertThat(result?.followingCount).isEqualTo("5")
        Truth.assertThat(result?.followersCount).isEqualTo("5")
        Truth.assertThat(result?.creationDate).isEqualTo("2021-05-05")

        Truth.assertThat(loadingLiveData).containsExactly(true, false).inOrder()
        Truth.assertThat(errorLiveData).containsExactly(false).inOrder()
    }

    @Test
    fun loadProfileDataErrorTest() {
        val userName = "rafael-couto-e"
        coEvery { mockUseCase.getUserProfile(userName) }.throws(Exception())
        sut.loadData()
        val result = sut.userProfile.value
        coVerify { mockUseCase.getUserProfile(userName) }
        Truth.assertThat(result).isNull()
        Truth.assertThat(loadingLiveData).containsExactly(true, false).inOrder()
        Truth.assertThat(errorLiveData).containsExactly(false, true).inOrder()
    }

    private fun dummyResponse() = ProfileUI(
        "rafael-couto-e",
        "",
        "",
        "",
        "5",
        "5",
        "5",
        "2021-05-05"
    )
}
