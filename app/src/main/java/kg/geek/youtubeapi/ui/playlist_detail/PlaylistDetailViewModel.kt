package kg.geek.youtubeapi.ui.playlist_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.geek.youtubeapi.App
import kg.geek.youtubeapi.model.PlayList
import retrofit2.Response

class PlaylistDetailViewModel : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getPlaylistItems(playlistId: String): LiveData<Response<PlayList>> {
        return App.repository.getVideos(playlistId)
    }

}