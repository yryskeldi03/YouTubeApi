package kg.geek.youtubeapi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kg.geek.youtubeapi.BuildConfig
import kg.geek.youtubeapi.core.network.result.Resource
import kg.geek.youtubeapi.utils.Constant
import kg.geek.youtubeapi.model.PlayList
import kg.geek.youtubeapi.remote.YouTubeApi
import kotlinx.coroutines.Dispatchers

class Repository(private val youTubeApi: YouTubeApi) {

    fun getPlaylists(): LiveData<Resource<PlayList>> = liveData(Dispatchers.IO) {

        Resource.loading(null)

        val response = youTubeApi.getPlayList(
            Constant.PART, Constant.CHANNEL_ID, Constant.MY_MAX_RESULT, BuildConfig.API_KEY
        )
        emit(
            if (response.isSuccessful) Resource.success(response.body()) else Resource.error(
                response.message(), response.body(), response.code()
            )
        )
    }

    fun getVideos(playlistId: String): LiveData<Resource<PlayList>> = liveData(Dispatchers.IO) {

        Resource.loading(null)

        val response = youTubeApi.getPlaylistItems(
            Constant.PART,
            BuildConfig.API_KEY, Constant.MY_MAX_RESULT, playlistId
        )
        emit(
            if (response.isSuccessful) Resource.success(response.body()) else Resource.error(
                response.message(), response.body(), response.code()
            )
        )
    }
}