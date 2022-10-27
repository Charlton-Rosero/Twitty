package com.example.lib_data.domain.models

import com.google.gson.annotations.SerializedName

/**
 * Token.
 *
 * @property accessToken
 * @property refreshToken
 * @constructor Create empty Token.
 */
data class Token(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String
)
