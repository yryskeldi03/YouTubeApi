package kg.geek.youtubeapi.extensions

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun ImageView.load(uri: String) {
    Glide.with(this)
        .load(uri)
        .centerCrop()
        .into(this)
}