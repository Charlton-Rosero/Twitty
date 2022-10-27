package com.example.lib_data.domain.models.datastore

import kotlinx.coroutines.flow.Flow

/**
 *
 */
interface DataSource {
    /**
     *
     */
    fun getDataStore(): Flow<String>

    /**
     *
     */
    suspend fun setDataStore(token: String)
}
