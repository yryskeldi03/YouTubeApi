package kg.geek.youtubeapi.remote

import kg.geek.youtubeapi.model.PlayList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {

    @GET("playlists")
    suspend fun getPlayList(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResults: String,
        @Query("key") apiKey: String
    ): Response<PlayList>

    @GET("playlistItems")
    suspend fun getPlaylistItems(
        @Query("part") part: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: String,
        @Query("playlistId") playlistId: String
    ): Response<PlayList>
}