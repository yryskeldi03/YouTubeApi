package kg.geek.youtubeapi.ui.playlist_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.geek.youtubeapi.core.network.result.Resource
import kg.geek.youtubeapi.model.PlayList
import kg.geek.youtubeapi.repository.Repository

class PlaylistDetailViewModel(private val repository: Repository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlayList>> {
        return repository.getVideos(playlistId)
    }

}