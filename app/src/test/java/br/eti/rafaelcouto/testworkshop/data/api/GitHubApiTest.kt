package br.eti.rafaelcouto.testworkshop.data.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.eti.rafaelcouto.testworkshop.BaseUnitTest
import br.eti.rafaelcouto.testworkshop.data.model.Profile
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import java.net.HttpURLConnection
import java.util.*

class GitHubApiTest : BaseUnitTest() {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: GitHubApi

    @Before
    fun start() {
        sut = buildApi()
    }

    @Test
    fun getProfileSuccessTest() = runBlocking {
        mockNetworkResponseWithFileContent("profile200.json", HttpURLConnection.HTTP_OK)

        val createdAt = Calendar.getInstance(TimeZone.getTimeZone("GMT")).apply {
            set(Calendar.YEAR, 2016)
            set(Calendar.MONTH, 3)
            set(Calendar.DAY_OF_MONTH, 27)
            set(Calendar.AM_PM, Calendar.AM)
            set(Calendar.HOUR, 1)
            set(Calendar.MINUTE, 23)
            set(Calendar.SECOND, 27)
            set(Calendar.MILLISECOND, 0)
        }.time

        val expected = Profile().apply {
            name = "Rafael Couto"
            bio = "Android | iOS Dev"
            blog = "rafaelcouto.eti.br"
            company = "Deliver IT"
            followers = 14
            following = 11
            publicRepos = 23
            twitterUsername = "rc0ut0"
            this.createdAt = createdAt
            location = "Brazil"
        }
        val actual = sut.getProfile("rafael")
        Truth.assertThat(actual.name).isEqualTo(expected.name)
        Truth.assertThat(actual.bio).isEqualTo(expected.bio)
        Truth.assertThat(actual.blog).isEqualTo(expected.blog)
        Truth.assertThat(actual.company).isEqualTo(expected.company)
        Truth.assertThat(actual.followers).isEqualTo(expected.followers)
        Truth.assertThat(actual.following).isEqualTo(expected.following)
        Truth.assertThat(actual.publicRepos).isEqualTo(expected.publicRepos)
        Truth.assertThat(actual.twitterUsername).isEqualTo(expected.twitterUsername)
        Truth.assertThat(actual.createdAt).isEqualTo(expected.createdAt)
        Truth.assertThat(actual.location).isEqualTo(expected.location)

        Truth.assertThat(actual.location).isNotInstanceOf(String::class.java)
    }

    @Test
    fun getSiroProfileSuccessTest() = runBlocking {
        mockNetworkResponseWithFileContent("siro200.json", HttpURLConnection.HTTP_OK)
        val createdAt = Calendar.getInstance(TimeZone.getTimeZone("GMT")).apply {
            set(Calendar.YEAR, 2015)
            set(Calendar.MONTH, 7)
            set(Calendar.DAY_OF_MONTH, 15)
            set(Calendar.AM_PM, Calendar.PM)
            set(Calendar.HOUR, 8)
            set(Calendar.MINUTE, 29)
            set(Calendar.SECOND, 2)
            set(Calendar.MILLISECOND, 0)
        }.time

        val expected = Profile().apply {
            name = "Siro Souza"
            bio =
                "Desenvolvedor Mobile em uma grande financeira e outros projetos pararelos como por exemplo: Bandnest."
            blog = "https://newsoftwarebrasil.com.br"
            company = "Realize CFI"
            followers = 7
            following = 39
            publicRepos = 36
            twitterUsername = null
            this.createdAt = createdAt
            location = "São Paulo"
        }
        val actual = sut.getProfile("rafael")
        Truth.assertThat(actual.name).isEqualTo(expected.name)
        Truth.assertThat(actual.bio).isEqualTo(expected.bio)
        Truth.assertThat(actual.blog).isEqualTo(expected.blog)
        Truth.assertThat(actual.company).isEqualTo(expected.company)
        Truth.assertThat(actual.followers).isEqualTo(expected.followers)
        Truth.assertThat(actual.following).isEqualTo(expected.following)
        Truth.assertThat(actual.publicRepos).isEqualTo(expected.publicRepos)
        Truth.assertThat(actual.twitterUsername).isNull()
        Truth.assertThat(actual.createdAt).isEqualTo(expected.createdAt)
        Truth.assertThat(actual.location).isEqualTo(expected.location)
    }

    @Test
    fun getEderProfileSuccessTest() = runBlocking {
        mockNetworkResponseWithFileContent("eder200.json", HttpURLConnection.HTTP_OK)
        val createdAt = Calendar.getInstance(TimeZone.getTimeZone("GMT")).apply {
            set(Calendar.YEAR, 2019)
            set(Calendar.MONTH, 1)
            set(Calendar.DAY_OF_MONTH, 5)
            set(Calendar.AM_PM, Calendar.PM)
            set(Calendar.HOUR, 6)
            set(Calendar.MINUTE, 39)
            set(Calendar.SECOND, 50)
            set(Calendar.MILLISECOND, 0)
        }.time

        val expected = Profile().apply {
            name = "Eder Martins"
            bio = "Software Specialist"
            blog = ""
            company = "Realize CFI"
            followers = 3
            following = 3
            publicRepos = 3
            twitterUsername = "edermdedeh"
            this.createdAt = createdAt
            location = "Brazil"
        }
        val actual = sut.getProfile("eder")
        Truth.assertThat(actual.name).isEqualTo(expected.name)
        Truth.assertThat(actual.bio).isEqualTo(expected.bio)
        Truth.assertThat(actual.blog).isEqualTo(expected.blog)
        Truth.assertThat(actual.company).isEqualTo(expected.company)
        Truth.assertThat(actual.followers).isEqualTo(expected.followers)
        Truth.assertThat(actual.following).isEqualTo(expected.following)
        Truth.assertThat(actual.publicRepos).isEqualTo(expected.publicRepos)
        Truth.assertThat(actual.twitterUsername).isEqualTo(expected.twitterUsername)
        Truth.assertThat(actual.createdAt).isEqualTo(expected.createdAt)
        Truth.assertThat(actual.location).isEqualTo(expected.location)
    }

    @Test
    fun getHelderProfileSuccessTest() = runBlocking {
        mockNetworkResponseWithFileContent("helder200.json", HttpURLConnection.HTTP_OK)
        val createdAt = Calendar.getInstance(TimeZone.getTimeZone("GMT")).apply {
            set(Calendar.YEAR, 2016)
            set(Calendar.MONTH, 2)
            set(Calendar.DAY_OF_MONTH, 28)
            set(Calendar.AM_PM, Calendar.PM)
            set(Calendar.HOUR, 10)
            set(Calendar.MINUTE, 8)
            set(Calendar.SECOND, 46)
            set(Calendar.MILLISECOND, 0)
        }.time

        val expected = Profile().apply {
            name = "hdrrocha"
            bio = "Mobile Developer\r\n"
            blog = "hediestrokes"
            company = null
            followers = 15
            following = 33
            publicRepos = 39
            twitterUsername = null
            this.createdAt = createdAt
            location = "Florianópolis, SC, Brasil"
        }
        val actual = sut.getProfile("eder")
        Truth.assertThat(actual.name).isEqualTo(expected.name)
        Truth.assertThat(actual.bio).isEqualTo(expected.bio)
        Truth.assertThat(actual.blog).isEqualTo(expected.blog)
        Truth.assertThat(actual.company).isNull()
        Truth.assertThat(actual.followers).isEqualTo(expected.followers)
        Truth.assertThat(actual.following).isEqualTo(expected.following)
        Truth.assertThat(actual.publicRepos).isEqualTo(expected.publicRepos)
        Truth.assertThat(actual.twitterUsername).isNull()
        Truth.assertThat(actual.createdAt).isEqualTo(expected.createdAt)
        Truth.assertThat(actual.location).isEqualTo(expected.location)
    }

    @Test(expected = HttpException::class)
    fun getProfileErrorTest() {
        runBlocking {
            mockNetworkResponseWithFileContent(
                "profile200.json",
                HttpURLConnection.HTTP_BAD_REQUEST
            )
            sut.getProfile("rafael")
        }
    }
}
