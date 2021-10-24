package kg.geek.youtubeapi.ui.playlist_detail

import android.annotation.SuppressLint
import android.content.Intent
import kg.geek.youtubeapi.R
import kg.geek.youtubeapi.core.network.result.Status.*
import kg.geek.youtubeapi.core.ui.BaseActivity
import kg.geek.youtubeapi.databinding.ActivityPlaylistDetailBinding
import kg.geek.youtubeapi.extensions.showToast
import kg.geek.youtubeapi.extensions.visible
import kg.geek.youtubeapi.model.Items
import kg.geek.youtubeapi.ui.playlists.PlaylistsActivity
import kg.geek.youtubeapi.ui.video.VideoActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistDetailActivity : BaseActivity<ActivityPlaylistDetailBinding>() {

    private val viewModel: PlaylistDetailViewModel by viewModel()
    private var videos = arrayListOf<Items>()
    private val adapter: PlaylistDetailAdapter by lazy {
        PlaylistDetailAdapter(
            videos,
            this::clickListener
        )
    }

    override fun setUI() {
        binding.tvPlaylistTitle.text = intent.getStringExtra(PlaylistsActivity.TITLE_KEY)

        if (intent.getStringExtra(PlaylistsActivity.DESCRIPTION_KEY).isNullOrEmpty()) {
            binding.tvPlaylistDescription.visible = false
        } else {
            binding.tvPlaylistDescription.text =
                intent.getStringExtra(PlaylistsActivity.DESCRIPTION_KEY)
        }

        binding.tvVideoCounter.text = String.format(
            intent.getStringExtra(PlaylistsActivity.ITEM_COUNT) + " " + getString(R.string.video_series)
        )

    }

    override fun setupObservers() {
        viewModel.getPlaylistItems(intent.getStringExtra(PlaylistsActivity.ID_KEY).toString())
            .observe(this) { response ->

                when (response.status) {
                    SUCCESS -> {
                        viewModel.loading.value = false
                        if (response.data?.items != null) {
                            videos = response.data.items
                        }
                        initRecycler()
                    }

                    LOADING -> viewModel.loading.value = true

                    ERROR -> {
                        viewModel.loading.value = false
                        showToast(response.message.toString())
                    }
                }
            }
        viewModel.loading.observe(this) {
            binding.progressBar.visible = it
        }
    }

    private fun clickListener(videoId: String, title: String, description: String) {
        Intent(this, VideoActivity::class.java).apply {
            putExtra(VIDEO_ID_KEY, videoId)
            putExtra(TITLE_KEY, title)
            putExtra(DESCRIPTION_KEY, description)
            startActivity(this)
        }
    }

    override fun initClickListener() {
        binding.tvBack.setOnClickListener {
            finish()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initRecycler() {
        binding.rvVideos.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun inflateViewBinding(): ActivityPlaylistDetailBinding {
        return ActivityPlaylistDetailBinding.inflate(layoutInflater)
    }

    companion object {
        const val VIDEO_ID_KEY = "video Id"
        const val TITLE_KEY = "title"
        const val DESCRIPTION_KEY = "description"
    }

}