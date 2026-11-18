package com.example.bookshelf.data

import android.util.Log
import com.example.bookshelf.model.Book
import com.example.bookshelf.network.BooksApiService
import com.example.bookshelf.network.VolumeInfo
import retrofit2.HttpException

interface BookshelfRepository {
    suspend fun getBooks(query: String = "jazz history"): List<Book>
}

class NetworkBookshelfRepository(
    private val booksApiService: BooksApiService,
    private val apiKey: String? = null,
) : BookshelfRepository {

    override suspend fun getBooks(query: String): List<Book> {
        val formattedQuery = query.trim().ifEmpty { "jazz history" }
        val effectiveApiKey = if (apiKey.isNullOrBlank()) null else apiKey

        val searchResponse = try {
            booksApiService.searchBooks(formattedQuery, apiKey = effectiveApiKey)
        } catch (e: HttpException) {
            if (e.code() == 429) {
                throw Exception("Límite de peticiones de IP alcanzado (HTTP 429). Desconecta y vuelve a conectar tu Wi-Fi o datos móviles para renovar tu IP, o usa una API Key gratuita de Google.", e)
            }
            throw e
        }

        val items = searchResponse.items ?: return emptyList()

        return items.map { searchItem ->
            val volumeInfo: VolumeInfo? = searchItem.volumeInfo ?: run {
                try {
                    booksApiService.getBook(searchItem.id, apiKey = effectiveApiKey).volumeInfo
                } catch (e: Exception) {
                    Log.w("BooksRepository", "No se pudo obtener detalle individual para ID=${searchItem.id}", e)
                    null
                }
            }

            val title = volumeInfo?.title ?: "Sin título"
            val rawThumbnail = volumeInfo?.imageLinks?.thumbnail
                ?: volumeInfo?.imageLinks?.smallThumbnail
                ?: ""
            val secureThumbnail = rawThumbnail.replace("http://", "https://")

            Book(
                id = searchItem.id,
                title = title,
                thumbnailUrl = secureThumbnail,
            )
        }
    }
}
