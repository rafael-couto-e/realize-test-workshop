package br.eti.rafaelcouto.testworkshop.domain.uimodel

data class ProfileUI(
    val userName: String,
    val userCompany: String,
    val location: String,
    val bio: String,
    val repoCount: String,
    val followingCount: String,
    val followersCount: String,
    val creationDate: String,
)