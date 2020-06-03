package android.igorkostenko.applicasterapp.model

data class EmployeeEntries(
    val author: Author = Author(),
    val entry: List<Entry> = listOf(),
    val id: String = "",
    val link: Link = Link(),
    val subtitle: String = "",
    val title: String = "",
    val type: Type = Type(),
    val updated: String = ""
)

data class Author(
    val name: String = ""
)

data class Entry(
    val author: Author = Author(),
    val content: Content = Content(),
    val id: String = "",
    val link: Link = Link(),
    val media_group: List<MediaGroup> = listOf(),
    val published: String = "",
    val summary: String = "",
    val title: String = "",
    val type: Type = Type(),
    val updated: String = ""
)

data class Content(
    val src: String = "",
    val content: String = "",
    val type: String = ""
)

data class Link(
    val href: String = "",
    val rel: String = "",
    val type: String = ""
)

data class MediaGroup(
    val media_item: List<MediaItem> = listOf(),
    val type: String = ""
)

data class Type(
    val value: String = ""
)

data class MediaItem(
    val key: String = "",
    val scale: String = "",
    val src: String = "",
    val type: String = ""
)