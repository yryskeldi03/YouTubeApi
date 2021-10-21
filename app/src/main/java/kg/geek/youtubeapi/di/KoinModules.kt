package kg.geek.youtubeapi.di

import kg.geek.youtubeapi.remote.networkModule

val koinModules = listOf(
    networkModule,
    repoModules,
    viewModules
)