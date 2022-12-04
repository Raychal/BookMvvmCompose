package com.raychal.book.utils

import com.raychal.book.model.Profile

sealed class ProfileViewState {
    object Empty: ProfileViewState()
    object Loading: ProfileViewState()
    data class Success(val data: List<Profile>): ProfileViewState()
    data class Error(val exception: Throwable): ProfileViewState()
}