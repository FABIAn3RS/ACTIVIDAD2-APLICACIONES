package com.example.bookshelf.fake

import com.example.bookshelf.model.Book
import com.example.bookshelf.network.BookItem
import com.example.bookshelf.network.BookSearchItem
import com.example.bookshelf.network.BookSearchResponse
import com.example.bookshelf.network.ImageLinks
import com.example.bookshelf.network.VolumeInfo

object FakeDataSource {
    const val idOne = "book1"
    const val idTwo = "book2"
    const val titleOne = "Jazz History Vol 1"
    const val titleTwo = "Jazz History Vol 2"
    const val httpUrlOne = "http://books.google.com/thumb1.jpg"
    const val httpUrlTwo = "http://books.google.com/thumb2.jpg"
    const val httpsUrlOne = "https://books.google.com/thumb1.jpg"
    const val httpsUrlTwo = "https://books.google.com/thumb2.jpg"

    val searchResponse = BookSearchResponse(
        items = listOf(
            BookSearchItem(id = idOne),
            BookSearchItem(id = idTwo)
        )
    )

    val bookItem1 = BookItem(
        id = idOne,
        volumeInfo = VolumeInfo(
            title = titleOne,
            imageLinks = ImageLinks(thumbnail = httpUrlOne)
        )
    )

    val bookItem2 = BookItem(
        id = idTwo,
        volumeInfo = VolumeInfo(
            title = titleTwo,
            imageLinks = ImageLinks(thumbnail = httpUrlTwo)
        )
    )

    val expectedBooks = listOf(
        Book(id = idOne, title = titleOne, thumbnailUrl = httpsUrlOne),
        Book(id = idTwo, title = titleTwo, thumbnailUrl = httpsUrlTwo)
    )
}
