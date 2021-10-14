package kg.geek.youtubeapi.ui.playlists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geek.youtubeapi.R
import kg.geek.youtubeapi.databinding.PlaylistItemBinding
import kg.geek.youtubeapi.extensions.load
import kg.geek.youtubeapi.model.Items

class PlaylistsAdapter(
    private val clickListener: (id: String, title: String, description: String, itemCount: String) -> Unit,
    private var playlists: ArrayList<Items>
) :
    RecyclerView.Adapter<PlaylistsAdapter.ViewHolder>() {

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
                String.format(
                    playList.contentDetails.itemCount.toString() + " "
                            + itemView.context.getString(R.string.video_series)
                )

            binding.root.setOnClickListener {
                clickListener(
                    playList.id,
                    playList.snippet.title,
                    playList.snippet.description,
                    playList.contentDetails.itemCount.toString()
                )
            }
        }
    }
}