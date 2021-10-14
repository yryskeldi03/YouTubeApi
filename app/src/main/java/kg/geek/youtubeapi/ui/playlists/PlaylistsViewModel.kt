package kg.geek.youtubeapi.ui.playlists

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.geek.youtubeapi.BuildConfig.API_KEY
import kg.geek.youtubeapi.`object`.Constant
import kg.geek.youtubeapi.model.PlayList
import kg.geek.youtubeapi.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistsActivityViewModel : ViewModel() {

    private val youTubeApi = RetrofitClient.create()

    val loading = MutableLiveData<Boolean>()

    fun getPlayList(): LiveData<PlayList> {
        return createCall()
    }

    fun checkNetworkInfoRealTime(context: Context): LiveData<Boolean?> {
        return NetworkConnectivityRealTime(context)
    }

    private fun createCall(): LiveData<PlayList> {
        loading.value = true
        val data = MutableLiveData<PlayList>()

        youTubeApi.getPlayList(Constant.PART, Constant.CHANNEL_ID, Constant.MY_MAX_RESULT, API_KEY)
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

class NetworkConnectivityRealTime(context: Context) : LiveData<Boolean?>() {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private var networkCallback = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }

        override fun onUnavailable() {
            super.onUnavailable()
            postValue(false)
        }

        override fun onLosing(network: Network, maxMsToLive: Int) {
            super.onLosing(network, maxMsToLive)
            postValue(false)
        }

    }

    override fun onActive() {
        super.onActive()
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}


