package com.raychal.book.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val fullName: String = "",
    val nicName: String = "",
    val email: String = "",
    val photo: String = ""
)
