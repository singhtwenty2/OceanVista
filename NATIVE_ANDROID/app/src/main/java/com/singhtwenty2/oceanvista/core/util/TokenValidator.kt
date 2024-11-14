package com.singhtwenty2.oceanvista.core.util

import android.util.Base64
import org.json.JSONObject
import java.nio.charset.Charset

object TokenValidator {
    fun isTokenExpired(token: String): Boolean {
        return try {
            val parts = token.split(".")
            if (parts.size < 2) return true

            val payload = String(Base64.decode(parts[1], Base64.DEFAULT), Charset.defaultCharset())
            val json = JSONObject(payload)
            val exp = json.getLong("exp")
            val now = System.currentTimeMillis() / 1000
            exp < now
        } catch (e: Exception) {
            true
        }
    }
}