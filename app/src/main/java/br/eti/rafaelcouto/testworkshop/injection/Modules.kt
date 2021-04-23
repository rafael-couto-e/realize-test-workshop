package br.eti.rafaelcouto.testworkshop.injection

import br.eti.rafaelcouto.testworkshop.BuildConfig
import br.eti.rafaelcouto.testworkshop.data.api.GitHubApi
import br.eti.rafaelcouto.testworkshop.data.repository.GitHubRepository
import br.eti.rafaelcouto.testworkshop.data.repository.abs.GitHubRepositoryAbs
import br.eti.rafaelcouto.testworkshop.domain.mapper.ProfileMapper
import br.eti.rafaelcouto.testworkshop.domain.mapper.abs.ProfileMapperAbs
import br.eti.rafaelcouto.testworkshop.domain.usecase.ProfileUseCase
import br.eti.rafaelcouto.testworkshop.domain.usecase.abs.ProfileUseCaseAbs
import br.eti.rafaelcouto.testworkshop.domain.viewmodel.ProfileViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

object Modules {

    private val network = module {
        single {
            Moshi.Builder()
                .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                .build()
        }

        single {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(get()))
                .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
                .build()
        }
    }

    private val api = module {
        single {
            val retrofit: Retrofit = get()
            retrofit.create(GitHubApi::class.java)
        }
    }

    private val repository = module {
        single<GitHubRepositoryAbs> {
            GitHubRepository(
                api = get()
            )
        }
    }

    private val mapper = module {
        single<ProfileMapperAbs> {
            ProfileMapper()
        }
    }

    private val useCase = module {
        single<ProfileUseCaseAbs> {
            ProfileUseCase(
                mapper = get(),
                repository = get()
            )
        }
    }

    private val viewModel = module {
        viewModel {
            ProfileViewModel(
                useCase = get()
            )
        }
    }

    var all = listOf(
        network,
        api,
        repository,
        mapper,
        useCase,
        viewModel
    )
}