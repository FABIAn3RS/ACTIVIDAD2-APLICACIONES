package com.example.bookshelf

import com.example.bookshelf.data.NetworkBookshelfRepository
import com.example.bookshelf.fake.FakeBooksApiService
import com.example.bookshelf.fake.FakeDataSource
import com.example.bookshelf.rules.TestDispatcherRule
import com.example.bookshelf.ui.BooksUiState
import com.example.bookshelf.ui.BookshelfViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class BookshelfViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun bookshelfViewModel_getBooks_verifyBooksUiStateSuccess() = runTest {
        val bookshelfViewModel = BookshelfViewModel(
            bookshelfRepository = NetworkBookshelfRepository(
                booksApiService = FakeBooksApiService()
            )
        )
        assertEquals(
            BooksUiState.Success(FakeDataSource.expectedBooks),
            bookshelfViewModel.uiState.value
        )
    }
}
