package com.example.lib_data.domain.models

/**
 * User.
 *
 * @property id
 * @property name
 * @property avatar
 * @property username
 * @property password
 * @constructor Create empty User.
 */

data class User(
val id: Long? = null,
val name: String = "",
val avatar: String = "",
val username: String = "",
val password: String = "",
)
