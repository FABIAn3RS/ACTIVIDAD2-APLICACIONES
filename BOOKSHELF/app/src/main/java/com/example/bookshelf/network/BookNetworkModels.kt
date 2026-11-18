package com.example.bookshelf.network

import com.google.gson.annotations.SerializedName

data class BookSearchResponse(
    @SerializedName("items") val items: List<BookSearchItem>? = null,
)

data class BookSearchItem(
    @SerializedName("id") val id: String,
    @SerializedName("volumeInfo") val volumeInfo: VolumeInfo? = null,
)

data class BookItem(
    @SerializedName("id") val id: String? = null,
    @SerializedName("volumeInfo") val volumeInfo: VolumeInfo? = null,
)

data class VolumeInfo(
    @SerializedName("title") val title: String? = null,
    @SerializedName("imageLinks") val imageLinks: ImageLinks? = null,
)

data class ImageLinks(
    @SerializedName("smallThumbnail") val smallThumbnail: String? = null,
    @SerializedName("thumbnail") val thumbnail: String? = null,
)
