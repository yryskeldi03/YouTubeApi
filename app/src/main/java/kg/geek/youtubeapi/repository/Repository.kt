package kg.geek.youtubeapi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kg.geek.youtubeapi.BuildConfig
import kg.geek.youtubeapi.`object`.Constant
import kg.geek.youtubeapi.model.PlayList
import kg.geek.youtubeapi.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

class Repository {

    private val youTubeApi = RetrofitClient.create()

    fun getPlaylists(): LiveData<Response<PlayList>> = liveData(Dispatchers.IO) {
        val response = youTubeApi.getPlayList(
            Constant.PART, Constant.CHANNEL_ID, Constant.MY_MAX_RESULT, BuildConfig.API_KEY
        )
        emit(response)

    }

    fun getVideos(playlistId: String): LiveData<Response<PlayList>> = liveData(Dispatchers.IO) {
        val response = youTubeApi.getPlaylistItems(
            Constant.PART,
            BuildConfig.API_KEY, Constant.MY_MAX_RESULT, playlistId
        )
        emit(response)
    }

}