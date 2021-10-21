package kg.geek.youtubeapi.di

import kg.geek.youtubeapi.ui.playlist_detail.PlaylistDetailViewModel
import kg.geek.youtubeapi.ui.playlists.PlaylistsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModules: Module = module {
    viewModel { PlaylistsViewModel(get()) }
    viewModel { PlaylistDetailViewModel(get()) }
}