package com.fghilmany.mvvmstarterproject.utils

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.securepreferences.SecurePreferences

class PreferenceProvider(context: Context) {

    private var pref: SharedPreferences = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
        val spec = KeyGenParameterSpec.Builder(
            MasterKey.DEFAULT_MASTER_KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .build()
        val masterKey = MasterKey.Builder(context)
            .setKeyGenParameterSpec(spec)
            .build()
        EncryptedSharedPreferences
            .create(
                context,
                SHARED_PREFERENCE_FILE_NAME,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
    } else {
        SecurePreferences(context, SHARED_PREFERENCE_PASSWORD, SHARED_PREFERENCE_FILE_NAME)
    }

    private val editor : SharedPreferences.Editor = pref.edit()

    //save function
    private fun setString(key: String, value: String?){
        editor.putString(key, value)
        editor.apply()
    }

    private fun setInt(key: String, value: Int?){
        editor.putInt(key, value?:0)
        editor.apply()
    }

    private fun setBoolean(key: String, value: Boolean?){
        editor.putBoolean(key, value?:false)
        editor.apply()
    }

    //example save data
    fun setEmail(email: String?){
        setString(EMAIL_SP_KEY, email)
    }

    //example load data
    fun getEmail() = pref.getString(EMAIL_SP_KEY, null)

}