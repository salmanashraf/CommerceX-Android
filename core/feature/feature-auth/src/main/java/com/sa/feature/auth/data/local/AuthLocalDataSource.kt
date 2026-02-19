package com.sa.feature.auth.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sa.feature.auth.domain.model.AuthUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.authDataStore by preferencesDataStore(name = "secure_auth_store")

class AuthLocalDataSource(
    private val context: Context,
    private val tokenCipher: TokenCipher
) {

    private val tokenKey = stringPreferencesKey("auth_token_encrypted")
    private val userIdKey = intPreferencesKey("auth_user_id")
    private val firstNameKey = stringPreferencesKey("auth_first_name")
    private val lastNameKey = stringPreferencesKey("auth_last_name")
    private val emailKey = stringPreferencesKey("auth_email")
    private val usernameKey = stringPreferencesKey("auth_username")
    private val imageUrlKey = stringPreferencesKey("auth_image_url")

    val tokenFlow: Flow<String?> = context.authDataStore.data.map { prefs ->
        prefs[tokenKey]?.let { encrypted ->
            runCatching { tokenCipher.decrypt(encrypted) }.getOrNull()
        }
    }

    suspend fun getToken(): String? = tokenFlow.first()

    suspend fun getCachedUser(): AuthUser? {
        val prefs = context.authDataStore.data.first()
        val userId = prefs[userIdKey] ?: return null
        val firstName = prefs[firstNameKey] ?: return null
        val lastName = prefs[lastNameKey] ?: return null
        val email = prefs[emailKey] ?: return null
        val username = prefs[usernameKey] ?: return null
        val imageUrl = prefs[imageUrlKey].orEmpty()

        return AuthUser(
            id = userId,
            firstName = firstName,
            lastName = lastName,
            email = email,
            username = username,
            imageUrl = imageUrl
        )
    }

    suspend fun saveAuth(token: String, user: AuthUser) {
        context.authDataStore.edit { prefs ->
            prefs[tokenKey] = tokenCipher.encrypt(token)
            prefs[userIdKey] = user.id
            prefs[firstNameKey] = user.firstName
            prefs[lastNameKey] = user.lastName
            prefs[emailKey] = user.email
            prefs[usernameKey] = user.username
            prefs[imageUrlKey] = user.imageUrl
        }
    }

    suspend fun clear() {
        context.authDataStore.edit { prefs ->
            prefs.remove(tokenKey)
            prefs.remove(userIdKey)
            prefs.remove(firstNameKey)
            prefs.remove(lastNameKey)
            prefs.remove(emailKey)
            prefs.remove(usernameKey)
            prefs.remove(imageUrlKey)
        }
    }
}
