package com.example.circuler.data.datasource

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.example.circuler.BuildConfig
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

object GoogleDataSource {
    suspend fun signInWithGoogle(context: Context): String? {
        val credentialManager = CredentialManager.create(context)

        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(false)
            .setServerClientId(BuildConfig.GOOGLE_CLIENT_ID)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        return withContext(Dispatchers.IO) {
            runCatching {
                val result: GetCredentialResponse = credentialManager.getCredential(
                    request = request,
                    context = context
                )

                val googleIdCredential = GoogleIdTokenCredential.createFrom(result.credential.data)
                googleIdCredential.idToken
            }.onFailure { e ->
                Timber.tag("GoogleLogin").e(e, "Google login failed")
            }.getOrNull()
        }
    }
}
