package kg.geek.youtubeapi.core.network.result

import kg.geek.youtubeapi.core.network.result.Status.*

data class Resource<out T>(val status: Status, val data: T?, val message: String?, val code: Int?) {

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null, null)
        }

        fun <T> error(msg: String?, data: T?, code: Int?): Resource<T> {
            return Resource(ERROR, data, msg, code)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null, null)
        }
    }
}