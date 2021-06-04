package br.eti.rafaelcouto.testworkshop.domain.mapper

import br.eti.rafaelcouto.testworkshop.data.model.Profile
import br.eti.rafaelcouto.testworkshop.domain.mapper.abs.ProfileMapperAbs
import br.eti.rafaelcouto.testworkshop.domain.uimodel.ProfileUI
import java.text.SimpleDateFormat
import java.util.*

class ProfileMapper : ProfileMapperAbs {

    override fun map(input: Profile) = ProfileUI(
        input.name,
        input.company.orEmpty(),
        input.location,
        input.bio,
        input.publicRepos.toString(),
        input.following.toString(),
        input.followers.toString(),
        dateToString(input.createdAt)
    )

    private fun dateToString(date: Date): String {
        return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
    }
}