package br.eti.rafaelcouto.testworkshop.domain.usecase.abs

import br.eti.rafaelcouto.testworkshop.domain.uimodel.ProfileUI

interface ProfileUseCaseAbs {

    suspend fun getUserProfile(username: String): ProfileUI
}