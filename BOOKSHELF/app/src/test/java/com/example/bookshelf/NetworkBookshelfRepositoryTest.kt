package com.example.bookshelf

import com.example.bookshelf.data.NetworkBookshelfRepository
import com.example.bookshelf.fake.FakeBooksApiService
import com.example.bookshelf.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class NetworkBookshelfRepositoryTest {

    @Test
    fun networkBookshelfRepository_getBooks_verifyBooksListAndHttpsUrls() = runTest {
        val repository = NetworkBookshelfRepository(
            booksApiService = FakeBooksApiService()
        )

        val books = repository.getBooks("jazz history")

        assertEquals(FakeDataSource.expectedBooks, books)
        assertEquals(2, books.size)
        assertTrue(books[0].thumbnailUrl.startsWith("https://"))
        assertTrue(books[1].thumbnailUrl.startsWith("https://"))
        assertEquals("https://books.google.com/thumb1.jpg", books[0].thumbnailUrl)
        assertEquals("https://books.google.com/thumb2.jpg", books[1].thumbnailUrl)
    }
}
