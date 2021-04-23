package br.eti.rafaelcouto.testworkshop.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.eti.rafaelcouto.testworkshop.domain.uimodel.ProfileUI
import br.eti.rafaelcouto.testworkshop.domain.usecase.abs.ProfileUseCaseAbs
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val useCase: ProfileUseCaseAbs
) : ViewModel() {

    private val profile = MutableLiveData<ProfileUI>()
    private val error = MutableLiveData<Boolean>()
    private val loading = MutableLiveData<Boolean>()

    val userProfile get() = profile as LiveData<ProfileUI>
    val hasError get() = error as LiveData<Boolean>
    val isLoading get() = loading as LiveData<Boolean>

    fun loadData() = viewModelScope.launch {
        loading.value = true
        error.value = false

        try {
            profile.value = useCase.getUserProfile("rafael-couto-e")
        } catch (e: Exception) {
            error.value = true
        } finally {
            loading.value = false
        }
    }
}