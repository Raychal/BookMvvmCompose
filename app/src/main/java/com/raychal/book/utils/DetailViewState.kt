package com.raychal.book.utils

import com.raychal.book.model.BookItem

sealed class DetailViewState {
    object Empty: DetailViewState()
    object Loading : DetailViewState()
    data class Success(val data: BookItem) : DetailViewState()
    data class Error(val exception: Throwable) : DetailViewState()
}
