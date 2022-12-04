package com.raychal.book.navigation

import androidx.annotation.StringRes
import com.raychal.book.R

sealed class Screen (val route: String, @StringRes val resourceId: Int) {
    object BookList: Screen("book_list", R.string.text_bookList)
    object Details: Screen("book_details", R.string.text_bookDetails)
    object Profile: Screen("profile", R.string.profile)
}