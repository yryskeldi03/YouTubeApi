package kg.geek.youtubeapi.playlist_videos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kg.geek.youtubeapi.R
import kg.geek.youtubeapi.extensions.showToast
import kg.geek.youtubeapi.playlists.PlaylistsActivity

class PlaylistVideosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist_videos)

        showToast(intent.getStringExtra(PlaylistsActivity.ID_KEY).toString())
    }

}