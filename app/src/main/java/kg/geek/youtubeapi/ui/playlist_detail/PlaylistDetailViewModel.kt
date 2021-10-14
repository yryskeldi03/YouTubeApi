package kg.geek.youtubeapi.ui.playlist_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.geek.youtubeapi.BuildConfig.API_KEY
import kg.geek.youtubeapi.`object`.Constant
import kg.geek.youtubeapi.model.Items
import kg.geek.youtubeapi.model.PlayList
import kg.geek.youtubeapi.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistDetailViewModel : ViewModel() {

    private val youTubeApi = RetrofitClient.create()

    val loading = MutableLiveData<Boolean>()

    fun getPlaylistItems(playlistId: String): LiveData<PlayList> {
        return getVideos(playlistId)
    }

    private fun getVideos(playlistId: String): LiveData<PlayList> {
        loading.value = true
        val data = MutableLiveData<PlayList>()

        youTubeApi.getPlaylistItems(Constant.PART,API_KEY, Constant.MY_MAX_RESULT, playlistId)
            .enqueue(object : Callback<PlayList> {
                override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                    if (response.isSuccessful && response.body() != null) {
                        data.value = response.body()
                        loading.value = false
                    }
                }

                override fun onFailure(call: Call<PlayList>, t: Throwable) {
                    Log.e("ololo", "onFailure: ${t.stackTrace} - ${t.localizedMessage}")
                }

            })

        return data
    }
}