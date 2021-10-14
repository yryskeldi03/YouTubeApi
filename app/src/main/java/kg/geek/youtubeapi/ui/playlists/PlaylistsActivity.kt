package kg.geek.youtubeapi.ui.playlists

import android.annotation.SuppressLint
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import kg.geek.youtubeapi.core.BaseActivity
import kg.geek.youtubeapi.databinding.ActivityPlaylistsBinding
import kg.geek.youtubeapi.extensions.visible
import kg.geek.youtubeapi.model.Items
import kg.geek.youtubeapi.ui.playlist_detail.PlaylistDetailActivity

class PlaylistsActivity : BaseActivity<ActivityPlaylistsBinding>() {

    private val viewModel: PlaylistsActivityViewModel by lazy { ViewModelProvider(this).get(PlaylistsActivityViewModel::class.java) }
    private var playlists = arrayListOf<Items>()
    private val adapter: PlaylistsAdapter by lazy { PlaylistsAdapter(this::clickListener, playlists) }
    private var isHaveNetworkConnect: Boolean? = null

    override fun setUI() {
    }

    override fun setupObservers() {

        viewModel.loading.observe(this){
            binding.progressBar.visible = it
        }

        viewModel.checkNetworkInfoRealTime(this).observe(this, {
            isHaveNetworkConnect = it
            checkInternet()
        })

        viewModel.getPlayList().observe(this, { response ->
            playlists = response.items
            initRecycler()
        })

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initRecycler() {
        binding.rvPlaylists.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun initClickListener() {
        binding.ivNoInternetConnectionLayout.btnTryAgain.setOnClickListener {
            if (isHaveNetworkConnect == true) {
                binding.rvPlaylists.visible = true
                binding.ivNoInternetConnectionLayout.root.visible = false
                setupObservers()
            }
        }
    }

    private fun clickListener(id: String, title: String, description: String, itemCount: String) {
        Intent(this, PlaylistDetailActivity::class.java).apply {
            putExtra(ID_KEY, id)
            putExtra(TITLE_KEY, title)
            putExtra(DESCRIPTION_KEY, description)
            putExtra(ITEM_COUNT, itemCount)
            startActivity(this)
        }
    }

    private fun checkInternet() {
        if (isHaveNetworkConnect == false || isHaveNetworkConnect == null) {
            binding.ivNoInternetConnectionLayout.root.visible = true
            binding.rvPlaylists.visible = false
        }
    }

    companion object {
        const val ID_KEY = "id"
        const val TITLE_KEY = "title"
        const val DESCRIPTION_KEY = "description"
        const val ITEM_COUNT = "itemCount"
    }

    override fun inflateViewBinding(): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }
}