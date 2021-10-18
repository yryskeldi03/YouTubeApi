package kg.geek.youtubeapi.ui.playlists

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.geek.youtubeapi.App
import kg.geek.youtubeapi.model.PlayList
import kg.geek.youtubeapi.utils.NetworkConnectivityRealTime
import retrofit2.Response

class PlaylistsActivityViewModel : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getPlayList(): LiveData<Response<PlayList>> {
        return App.repository.getPlaylists()
    }

    fun checkNetworkInfoRealTime(context: Context): LiveData<Boolean?> {
        return NetworkConnectivityRealTime(context)
    }
}