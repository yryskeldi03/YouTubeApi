package kg.geek.youtubeapi.model

data class PlayList(
    var etag: String,
    var items: ArrayList<Items>,
    var kind: String,
    var pageInfo: PageInfo,
)

data class Thumbnails(
    var default: Default,
    var medium: Medium,
    var high: High
)

data class Snippet(
    var publishedAt: String,
    var channelId: String,
    var title: String,
    var description: String,
    var thumbnails: Thumbnails,
    var channelTitle: String,
    var tags: List<String>,
    var categoryId: String,
    var liveBroadcastContent: String,
    var localized: Localized,
    var playlistId: String,
    var position: Int,
    var resourceId: ResourceId,
    var videoOwnerChannelId: String,
    var videoOwnerChannelTitle: String,

)

data class PageInfo(
    var maxResults: Int,
    var totalResults: Int,
    var resultsPerPage: Int
)

data class Medium(
    var url: String,
    var width: Int,
    var height: Int
)

data class Localized(
    var title: String,
    var description: String
)

data class Items(
    var contentDetails: ContentDetails,
    var etag: String,
    var id: String,
    var kind: String,
    var snippet: Snippet
)

data class High(
    var url: String,
    var width: Int,
    var height: Int
)

data class Default(
    var url: String,
    var width: Int,
    var height: Int
)

data class ContentRating(val name: String = "")

data class ContentDetails(
    var itemCount: Int,
    var duration: String,
    var dimension: String,
    var definition: String,
    var caption: String,
    var licensedContent: Boolean,
    var contentRating: ContentRating,
    var projection: String,
    var videoId: String,
    var videoPublishedAt: String,
    var endAt: String
)

data class ResourceId(
    var kind: String,
    var videoId: String
)

data class PlaylistItems(
    var kind: String,
    var etag: String,
    var id: String,
    var snippet: Snippet,
    var contentDetails: ContentDetails

)
