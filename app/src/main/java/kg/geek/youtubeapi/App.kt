package kg.geek.youtubeapi

import android.app.Application
import kg.geek.youtubeapi.repository.Repository

class App: Application() {

    companion object {
        val repository = Repository()
    }
}