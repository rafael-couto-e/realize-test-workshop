package br.eti.rafaelcouto.testworkshop.data.model

import com.squareup.moshi.Json
import java.util.*

class Profile {
    var name: String = ""
    var company: String? = ""
    var blog: String = ""
    var location: String = ""
    var bio: String = ""

    @field:Json(name = "twitter_username")
    var twitterUsername: String? = ""

    @field:Json(name = "public_repos")
    var publicRepos: Int = 0
    var following: Int = 0
    var followers: Int = 0

    @field:Json(name = "created_at")
    var createdAt: Date = Date()
}