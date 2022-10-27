package com.example.lib_data.domain.models


/**
 * Post.
 *
 * @property id
 * @property content
 * @property createdAt
 * @property updatedAt
 * @property comments
 * @property username
 * @constructor Create empty Post.
 */

data class Post(
val id: Long? = null,
val content: String ,
val createdAt: String ,
val updatedAt: String ,
val comments: MutableList<Comment> = mutableListOf(),
val username: String ,
)
