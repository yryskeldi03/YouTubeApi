package kg.geek.youtubeapi.playlists

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import kg.geek.youtubeapi.MainViewModel
import kg.geek.youtubeapi.core.BaseActivity
import kg.geek.youtubeapi.databinding.ActivityPlaylistsBinding
import kg.geek.youtubeapi.extensions.visible
import kg.geek.youtubeapi.model.Items
import kg.geek.youtubeapi.playlist_videos.PlaylistVideosActivity

class PlaylistsActivity : BaseActivity<ActivityPlaylistsBinding>() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: PlaylistsAdapter
    private var isHaveNetworkConnect: Boolean? = null

    override fun setUI() {
        adapter = PlaylistsAdapter()
    }

    override fun setupLiveData() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun setupObservers() {

        viewModel.checkNetworkInfoRealTime(this).observe(this, {
            isHaveNetworkConnect = it
            checkInternet()
        })

        viewModel.getPlayList().observe(this, { response ->
            adapter.setPlaylists(response.items as ArrayList<Items>)
            binding.rvPlaylists.adapter = adapter
        })


    }

    override fun initClickListener() {
        binding.ivNoInternetConnectionLayout.btnTryAgain.setOnClickListener {
            if (isHaveNetworkConnect == true) {
                binding.rvPlaylists.visible = true
                binding.ivNoInternetConnectionLayout.root.visible = false
                setupObservers()
            }
        }

        adapter.setListener(object : PlaylistsAdapter.OnItemClickListener {
            override fun onClick(id: String) {
                val intent = Intent(this@PlaylistsActivity, PlaylistVideosActivity::class.java)
                intent.putExtra(ID_KEY, id)
                startActivity(intent)
            }
        })
    }

    private fun checkInternet() {
        if (isHaveNetworkConnect == false || isHaveNetworkConnect == null) {
            binding.ivNoInternetConnectionLayout.root.visible = true
            binding.rvPlaylists.visible = false
        }
    }

    companion object {
        const val ID_KEY = "id"
    }

    override fun inflateViewBinding(): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }
}