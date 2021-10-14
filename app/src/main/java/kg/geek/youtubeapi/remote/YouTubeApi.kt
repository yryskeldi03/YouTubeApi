package kg.geek.youtubeapi.remote

import kg.geek.youtubeapi.model.PlayList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {

    @GET("playlists")
    fun getPlayList(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResults: String,
        @Query("key") apiKey: String
    ): Call<PlayList>

    @GET("playlistItems")
    fun getPlaylistItems(
        @Query("part") part: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: String,
        @Query("playlistId") playlistId: String
    ): Call<PlayList>
}