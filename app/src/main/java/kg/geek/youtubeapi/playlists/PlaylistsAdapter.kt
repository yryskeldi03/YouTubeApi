package kg.geek.youtubeapi.playlists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geek.youtubeapi.databinding.PlaylistItemBinding
import kg.geek.youtubeapi.extensions.load
import kg.geek.youtubeapi.model.Items

class PlaylistsAdapter : RecyclerView.Adapter<PlaylistsAdapter.ViewHolder>() {

    private var playlists = listOf<Items>()
    private lateinit var listener: OnItemClickListener

    fun setPlaylists(playlists: List<Items>) {
        this.playlists = playlists
        notifyDataSetChanged()
    }

    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PlaylistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(playlists[position])
    }

    override fun getItemCount(): Int = playlists.size

    inner class ViewHolder(private val binding: PlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(playList: Items) {
            binding.ivPlaylist.load(playList.snippet.thumbnails.medium.url)
            binding.tvPlaylistTitle.text = playList.snippet.title
            binding.tvPlaylistVideoCounter.text =
                String.format(playList.contentDetails.itemCount.toString() + " video series")

            binding.root.setOnClickListener {
                listener.onClick(playList.id)
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(id: String)
    }
}