package br.eti.rafaelcouto.testworkshop.data.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.eti.rafaelcouto.testworkshop.BaseUnitTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException

class GitHubApiTest : BaseUnitTest() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun start() {

    }

    @Test
    fun getProfileSuccessTest() {

    }

    @Test(expected = HttpException::class)
    fun getProfileErrorTest() {

    }
}
