package com.raychal.book.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raychal.book.model.BookItem
import com.raychal.book.model.Profile
import com.raychal.book.utils.DetailViewState
import com.raychal.book.utils.ProfileViewState
import com.raychal.book.utils.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MainViewModel: ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    private val _detailViewState = MutableStateFlow<DetailViewState>(DetailViewState.Loading)
    private val _profileViewState = MutableStateFlow<ProfileViewState>(ProfileViewState.Loading)

    val books = _viewState.asStateFlow()
    val bookDetails = _detailViewState.asStateFlow()
    val profile = _profileViewState.asStateFlow()

    val format = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
    }

    fun getAllBooks(context: Context) = viewModelScope.launch {
        try {
            val myJson = context.assets.open("books.json").bufferedReader().use {
                it.readText()
            }
            val bookList = format.decodeFromString<List<BookItem>>(myJson)
            _viewState.value = ViewState.Success(bookList)
        } catch (e: Exception) {
            _viewState.value = ViewState.Error(e)
        }
    }

    fun getBookByID(context: Context, isbnNo:String) = viewModelScope.launch {
        try {
            val myJson = context.assets.open("books.json").bufferedReader().use {
                it.readText()
            }
            val bookList = format.decodeFromString<List<BookItem>>(myJson) .filter { it.isbn.contentEquals(isbnNo)}.first()
            _detailViewState.value = DetailViewState.Success(bookList)
        } catch (e: Exception){
            _detailViewState.value = DetailViewState.Error(e)
        }
    }

    fun getProfile(context: Context) = viewModelScope.launch {
        try {
            val myJson = context.assets.open("profile.json").bufferedReader().use {
                it.readText()
            }
            val profile = format.decodeFromString<List<Profile>>(myJson)
            _profileViewState.value = ProfileViewState.Success(profile)
        } catch (e: Exception) {
            _profileViewState.value = ProfileViewState.Error(e)
        }
    }

}