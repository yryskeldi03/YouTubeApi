package kg.geek.youtubeapi.ui.playlists

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.geek.youtubeapi.core.network.result.Resource
import kg.geek.youtubeapi.model.PlayList
import kg.geek.youtubeapi.repository.Repository
import kg.geek.youtubeapi.utils.NetworkConnectivityRealTime

class PlaylistsViewModel(private val repository: Repository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getPlayList(): LiveData<Resource<PlayList>> {
        return repository.getPlaylists()
    }

    fun checkNetworkInfoRealTime(context: Context): LiveData<Boolean?> {
        return NetworkConnectivityRealTime(context)
    }
}