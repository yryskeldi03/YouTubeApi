package kg.geek.youtubeapi.ui.playlist_detail

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import kg.geek.youtubeapi.R
import kg.geek.youtubeapi.core.BaseActivity
import kg.geek.youtubeapi.databinding.ActivityPlaylistDetailBinding
import kg.geek.youtubeapi.extensions.visible
import kg.geek.youtubeapi.model.Items
import kg.geek.youtubeapi.ui.playlists.PlaylistsActivity

class PlaylistDetailActivity : BaseActivity<ActivityPlaylistDetailBinding>() {
    private val viewModel: PlaylistDetailViewModel by lazy {
        ViewModelProvider(this).get(
            PlaylistDetailViewModel::class.java
        )
    }
    private var videos = arrayListOf<Items>()
    private val adapter: PlaylistDetailAdapter by lazy { PlaylistDetailAdapter(videos) }

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
                videos = response.items
                initRecycler()
            }
    }


    override fun initClickListener() {

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initRecycler() {
        binding.rvVideos.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun inflateViewBinding(): ActivityPlaylistDetailBinding {
        return ActivityPlaylistDetailBinding.inflate(layoutInflater)
    }

}