package br.eti.rafaelcouto.testworkshop.domain.mapper.abs

import br.eti.rafaelcouto.testworkshop.data.model.Profile
import br.eti.rafaelcouto.testworkshop.domain.uimodel.ProfileUI

interface ProfileMapperAbs {

    fun map(input: Profile): ProfileUI
}