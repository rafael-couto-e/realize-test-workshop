package br.eti.rafaelcouto.testworkshop.domain.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.eti.rafaelcouto.testworkshop.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProfileViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule @ExperimentalCoroutinesApi
    var coroutineRule = CoroutinesTestRule()

    @Before
    fun setUp() {

    }

    @Test
    fun loadProfileDataSuccessTest() {

    }

    @Test
    fun loadProfileDataErrorTest() {

    }
}
