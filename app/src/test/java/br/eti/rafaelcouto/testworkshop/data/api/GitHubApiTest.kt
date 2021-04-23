package br.eti.rafaelcouto.testworkshop.data.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.eti.rafaelcouto.testworkshop.BaseUnitTest
import br.eti.rafaelcouto.testworkshop.data.model.Profile
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException
import java.net.HttpURLConnection
import java.util.*

@RunWith(JUnit4::class)
class GitHubApiTest : BaseUnitTest() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: GitHubApi

    private val dummyProfile = Profile().apply {
        name = "Rafael Couto"
        company = "Deliver IT"
        blog = "rafaelcouto.eti.br"
        location = "Brazil"
        bio = "Android | iOS Dev"
        twitterUsername = "rc0ut0"
        publicRepos = 23
        following = 11
        followers = 14
        createdAt = Calendar.getInstance(TimeZone.getTimeZone("GMT")).apply {
            set(Calendar.YEAR, 2016)
            set(Calendar.MONTH, 3)
            set(Calendar.DAY_OF_MONTH, 27)
            set(Calendar.AM_PM, Calendar.AM)
            set(Calendar.HOUR, 1)
            set(Calendar.MINUTE, 23)
            set(Calendar.SECOND, 27)
            set(Calendar.MILLISECOND, 0)
        }.time
    }

    @Before
    fun start() {
        sut = buildApi()
    }

    @Test
    fun getProfileSuccessTest() = runBlocking {
        mockNetworkResponseWithFileContent("profile200.json", HttpURLConnection.HTTP_OK)

        val expected = dummyProfile
        val actual = sut.getProfile("rafael-couto-e")

        assertThat(actual.name).isEqualTo(expected.name)
        assertThat(actual.company).isEqualTo(expected.company)
        assertThat(actual.blog).isEqualTo(expected.blog)
        assertThat(actual.location).isEqualTo(expected.location)
        assertThat(actual.bio).isEqualTo(expected.bio)
        assertThat(actual.twitterUsername).isEqualTo(expected.twitterUsername)
        assertThat(actual.publicRepos).isEqualTo(expected.publicRepos)
        assertThat(actual.following).isEqualTo(expected.following)
        assertThat(actual.followers).isEqualTo(expected.followers)
        assertThat(actual.createdAt).isEqualTo(expected.createdAt)
    }

    @Test(expected = HttpException::class)
    fun getProfileFailureTest() {
        runBlocking {
            mockNetworkResponseWithFileContent("profile200.json", HttpURLConnection.HTTP_BAD_REQUEST)

            try {
                sut.getProfile("rafael-couto-e")
            } catch (e: Exception) {
                assertThat(e.message).contains(HttpURLConnection.HTTP_BAD_REQUEST.toString())
                throw e
            }
        }
    }
}