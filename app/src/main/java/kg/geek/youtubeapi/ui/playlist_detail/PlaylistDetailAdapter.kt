package kg.geek.youtubeapi.ui.playlist_detail

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import kg.geek.youtubeapi.databinding.PlaylistVideosItemBinding
import kg.geek.youtubeapi.extensions.load
import kg.geek.youtubeapi.model.Items
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList

class PlaylistDetailAdapter(private var videos: ArrayList<Items>) :
    RecyclerView.Adapter<PlaylistDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PlaylistVideosItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(videos[position])
    }

    override fun getItemCount(): Int = videos.size

    inner class ViewHolder(private val binding: PlaylistVideosItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun onBind(video: Items) {
            val date = OffsetDateTime.parse(
                video.contentDetails.videoPublishedAt,
                DateTimeFormatter.ISO_DATE_TIME
            )
            val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
            val formatDateTime = date.format(formatter)
            binding.ivVideo.load(video.snippet.thumbnails.medium.url)
            binding.tvVideoPublishedAt.text = formatDateTime
            binding.tvVideoTitle.text = video.snippet.title
        }
    }
}