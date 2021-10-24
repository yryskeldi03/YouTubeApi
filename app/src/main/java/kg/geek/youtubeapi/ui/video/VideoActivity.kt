package kg.geek.youtubeapi.ui.video

import android.annotation.SuppressLint
import android.util.SparseArray
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import kg.geek.youtubeapi.core.ui.BaseActivity
import kg.geek.youtubeapi.databinding.ActivityVideoBinding
import kg.geek.youtubeapi.ui.playlist_detail.PlaylistDetailActivity
import kg.geek.youtubeapi.utils.Constant

class VideoActivity : BaseActivity<ActivityVideoBinding>() {

    private var player: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long = 0

    override fun setupObservers() {
    }

    override fun setUI() {
        binding.tvVideoTitle.text = intent.getStringExtra(PlaylistDetailActivity.TITLE_KEY)
        binding.tvVideoDescription.text =
            intent.getStringExtra(PlaylistDetailActivity.DESCRIPTION_KEY)
        initPlayer()
    }

    @SuppressLint("StaticFieldLeak")
    private fun initPlayer() {
        player = SimpleExoPlayer.Builder(this).build()
        binding.playerView.player = player

        val videoUrl =
            Constant.BASE_URL_VIDEO + intent.getStringExtra(PlaylistDetailActivity.VIDEO_ID_KEY)

        object : YouTubeExtractor(this) {
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?,
                videoMeta: VideoMeta?
            ) {
                if (ytFiles != null) {
                    val videoItag = 133
                    val audioItag = 140
                    val videoUri = ytFiles[videoItag].url
                    val audioUrl = ytFiles[audioItag].url

                    val audioSource =
                        ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
                            .createMediaSource(MediaItem.fromUri(audioUrl))

                    val videoSource =
                        ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
                            .createMediaSource(MediaItem.fromUri(videoUri))

                    player?.setMediaSource(
                        MergingMediaSource(true, videoSource, audioSource),
                        true
                    )
                    player?.prepare()
                    player?.playWhenReady = playWhenReady
                    player?.seekTo(currentWindow, playbackPosition)
                }
            }

        }.extract(videoUrl)
    }

    override fun initClickListener() {
        binding.tvBack.setOnClickListener {
            finish()
        }
    }

    override fun inflateViewBinding(): ActivityVideoBinding {
        return ActivityVideoBinding.inflate(layoutInflater)
    }

    override fun onStart() {
        super.onStart()
        initPlayer()
    }

    override fun onResume() {
        super.onResume()
        initPlayer()
    }

    override fun onPause() {
        releasePlayer()
        super.onPause()
    }

    override fun onStop() {
        releasePlayer()
        super.onStop()
        player?.stop()
    }

    private fun releasePlayer() {
        if (player != null) {
            playWhenReady = player?.playWhenReady!!
            playbackPosition = player?.currentPosition!!
            currentWindow = player?.currentWindowIndex!!
            player?.release()
            player = null
        }
    }
}