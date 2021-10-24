package kg.geek.youtubeapi.di

import kg.geek.youtubeapi.core.network.networkModule

val koinModules = listOf(
    networkModule,
    repoModules,
    viewModules
)