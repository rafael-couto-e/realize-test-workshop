package br.eti.rafaelcouto.testworkshop.domain.mapper

import br.eti.rafaelcouto.testworkshop.data.model.Profile
import br.eti.rafaelcouto.testworkshop.domain.mapper.abs.ProfileMapperAbs
import br.eti.rafaelcouto.testworkshop.domain.uimodel.ProfileUI
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class ProfileMapperTest {

    private lateinit var sut: ProfileMapperAbs

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
        sut = ProfileMapper()
    }

    @Test
    fun mapInputTest() {
        val input = dummyProfile
        val expected = dummyProfileUI
        val actual = sut.map(input)

        assertThat(actual).isEqualTo(expected)
    }
}