package com.example.bookshelf.fake

import com.example.bookshelf.network.BookItem
import com.example.bookshelf.network.BookSearchResponse
import com.example.bookshelf.network.BooksApiService

class FakeBooksApiService : BooksApiService {
    override suspend fun searchBooks(query: String, apiKey: String?): BookSearchResponse {
        return FakeDataSource.searchResponse
    }

    override suspend fun getBook(id: String, apiKey: String?): BookItem {
        return when (id) {
            FakeDataSource.idOne -> FakeDataSource.bookItem1
            FakeDataSource.idTwo -> FakeDataSource.bookItem2
            else -> throw IllegalArgumentException("Unknown book id $id")
        }
    }
}
