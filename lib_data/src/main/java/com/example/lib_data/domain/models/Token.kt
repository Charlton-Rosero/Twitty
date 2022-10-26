package com.example.lib_data.domain.models


/**
 * Token.
 *
 * @property accessToken
 * @property refreshToken
 * @constructor Create empty Token.
 */
data class Token(
    val accessToken: String,
    val refreshToken: String
)
