package kg.geek.youtubeapi.di

import kg.geek.youtubeapi.repository.Repository
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModules: Module = module {
    single { Repository(get()) }
}