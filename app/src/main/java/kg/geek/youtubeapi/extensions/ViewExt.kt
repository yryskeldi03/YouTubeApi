package kg.geek.youtubeapi.extensions

import android.view.View

var View.visible: Boolean
    get() = visibility == View.GONE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

var View.invisible: Boolean
    get() = visibility == View.INVISIBLE
    set(value) {
        visibility = if (value) View.INVISIBLE else View.VISIBLE
    }
