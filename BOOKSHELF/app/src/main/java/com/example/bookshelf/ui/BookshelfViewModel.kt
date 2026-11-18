package com.example.bookshelf.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfApplication
import com.example.bookshelf.data.BookshelfRepository
import com.example.bookshelf.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface BooksUiState {
    data class Success(val books: List<Book>) : BooksUiState
    data class Error(val message: String) : BooksUiState
    object Loading : BooksUiState
}

class BookshelfViewModel(
    private val bookshelfRepository: BookshelfRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<BooksUiState>(BooksUiState.Loading)
    val uiState: StateFlow<BooksUiState> = _uiState.asStateFlow()

    init {
        getBooks()
    }

    fun getBooks(query: String = "jazz history") {
        viewModelScope.launch {
            _uiState.value = BooksUiState.Loading
            try {
                val books = bookshelfRepository.getBooks(query)
                _uiState.value = BooksUiState.Success(books)
            } catch (e: Exception) {
                Log.e("BookshelfViewModel", "Error al cargar libros para query='$query'", e)
                val detail = e.localizedMessage ?: e.message ?: e.javaClass.simpleName
                _uiState.value = BooksUiState.Error("Error de conexión: $detail")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfApplication)
                val bookshelfRepository = application.container.bookshelfRepository
                BookshelfViewModel(bookshelfRepository = bookshelfRepository)
            }
        }
    }
}
