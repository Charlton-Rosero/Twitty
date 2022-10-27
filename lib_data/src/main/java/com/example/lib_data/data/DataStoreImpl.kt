package com.example.lib_data.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.lib_data.domain.models.datastore.DataSource
import com.example.lib_data.util.Const
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(
    name = Const.DATASTORE_PREFERENCES
)


/**
 *
 */
class DataStoreImpl @Inject constructor(private val context:Context): DataSource {
    override fun getDataStore(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[stringPreferencesKey("authToken")] ?: ""
        }
    }

   override suspend fun setDataStore(token: String){
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey("authToken")] = token
        }
    }

}
