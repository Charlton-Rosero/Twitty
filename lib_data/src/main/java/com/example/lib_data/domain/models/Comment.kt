package com.example.lib_data.domain.models


/**
 * Comment.
 *
 * @property id
 * @property content
 * @property createdAt
 * @property updatedAt
 * @property postId
 * @property username
 * @constructor Create empty Comment.
 */
data class Comment(

    val id: Long? = null,
    val content: String ,
    val createdAt: String,
    val updatedAt: String ,
    val postId: Long?,
    val username: String ,
)
