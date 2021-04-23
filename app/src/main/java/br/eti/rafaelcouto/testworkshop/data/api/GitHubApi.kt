package br.eti.rafaelcouto.testworkshop.data.api

import br.eti.rafaelcouto.testworkshop.data.model.Profile
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    @GET("users/{username}")
    suspend fun getProfile(@Path("username") userName: String): Profile
}