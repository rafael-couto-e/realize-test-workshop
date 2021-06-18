package br.eti.rafaelcouto.testworkshop.domain.mapper

import br.eti.rafaelcouto.testworkshop.data.model.Profile
import br.eti.rafaelcouto.testworkshop.domain.uimodel.ProfileUI
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test
import java.util.*

class ProfileMapperTest {
    private lateinit var sut: ProfileMapper

    @Before
    fun setUp() {
        sut = ProfileMapper()
    }

    @Test
    fun mapInputTest() {

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

        val input = Profile().apply {
            name = "Test"
            company = "Realize"
            location = "Porto Alegre"
            blog = "http://realize.io"
            bio = "Realize.io"
            publicRepos = 5
            following = 6
            followers = 7
            this.createdAt = createdAt
        }

        val expected = ProfileUI(
            "Test",
            "Realize",
            "Porto Alegre",
            "Realize.io",
            "5",
            "6",
            "7",
            "26/04/2016"
        )

        val result = sut.map(input)

        result.run {
            Truth.assertThat(userName).isEqualTo(expected.userName)
            Truth.assertThat(userCompany).isEqualTo(expected.userCompany)
            Truth.assertThat(bio).isEqualTo(expected.bio)
            Truth.assertThat(location).isEqualTo(expected.location)
            Truth.assertThat(repoCount).isEqualTo(expected.repoCount)
            Truth.assertThat(followersCount).isEqualTo(expected.followersCount)
            Truth.assertThat(followingCount).isEqualTo(expected.followingCount)
            Truth.assertThat(creationDate).isEqualTo(expected.creationDate)
        }

    }

    @Test
    fun mapInputNullCompanyTest() {

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

        val input = Profile().apply {
            name = "Test"
            company = null
            location = "Porto Alegre"
            blog = "http://realize.io"
            bio = "Realize.io"
            publicRepos = 5
            following = 6
            followers = 7
            this.createdAt = createdAt
        }

        val expected = ProfileUI(
            "Test",
            "",
            "Porto Alegre",
            "Realize.io",
            "5",
            "6",
            "7",
            "26/04/2016"
        )

        val result = sut.map(input)

        result.run {
            Truth.assertThat(userName).isEqualTo(expected.userName)
            Truth.assertThat(userCompany).isEqualTo(expected.userCompany)
            Truth.assertThat(bio).isEqualTo(expected.bio)
            Truth.assertThat(location).isEqualTo(expected.location)
            Truth.assertThat(repoCount).isEqualTo(expected.repoCount)
            Truth.assertThat(followersCount).isEqualTo(expected.followersCount)
            Truth.assertThat(followingCount).isEqualTo(expected.followingCount)
            Truth.assertThat(creationDate).isEqualTo(expected.creationDate)
        }
    }
}
