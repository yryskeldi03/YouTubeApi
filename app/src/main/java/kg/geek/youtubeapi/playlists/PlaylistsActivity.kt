package kg.geek.youtubeapi.playlists

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import kg.geek.youtubeapi.MainViewModel
import kg.geek.youtubeapi.core.BaseActivity
import kg.geek.youtubeapi.databinding.ActivityPlaylistsBinding
import kg.geek.youtubeapi.extensions.invisible
import kg.geek.youtubeapi.playlist_videos.PlaylistVideosActivity

class PlaylistsActivity : BaseActivity<ActivityPlaylistsBinding>() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: PlaylistsAdapter

    override fun setUI() {
        adapter = PlaylistsAdapter()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun setupLiveData() {
        viewModel.getPlayList().observe(this, { response ->
            adapter.setPlaylists(response.items)
            binding.rvPlaylists.adapter = adapter
        })
    }

    override fun initClickListener() {
        binding.ivNoInternetConnectionLayout.btnTryAgain.setOnClickListener {
            checkInternet()
        }

        adapter.setListener(object : PlaylistsAdapter.OnItemClickListener {
            override fun onClick(id: String) {
                val intent = Intent(this@PlaylistsActivity, PlaylistVideosActivity::class.java)
                intent.putExtra(ID_KEY, id)
                startActivity(intent)
            }
        })
    }

    override fun checkInternet() {
        viewModel.checkNetworkInfo(this).observe(this, {
            binding.ivNoInternetConnectionLayout.root.invisible = it
        })
        setupLiveData()
    }

    override fun inflateViewBinding(): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    companion object {
        const val ID_KEY = "id"
    }
}