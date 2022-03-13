package com.eerick.dogos.utils

import android.content.Context
import android.content.SharedPreferences
import com.eerick.dogos.R
import com.eerick.dogos.database.DogsDB.Companion.getInstance

class SharedPreferencesUtils {
    companion object {
        /**
         * [save] Stores data into preferences, it only has support for Boolean, Int, Float
         * Long and String objects, otherwise it should return an exception
         * @param context
         * @param key
         * @param value
         * @return void
         */
        fun save(context: Context, key: String, value: Any) {
            val sharedPreferences =
                context.getSharedPreferences(context.getString(R.string.preference_file_key),
                    Context.MODE_PRIVATE)
            with(sharedPreferences.edit()) {
                when (value) {
                    is Boolean -> {
                        putBoolean(key, value)
                    }
                    is Int -> {
                        putInt(key, value)
                    }
                    is Float -> {
                        putFloat(key, value)
                    }
                    is Long -> {
                        putLong(key, value)
                    }
                    is String -> {
                        putString(key, value)
                    }
                    else -> {
                        throw UnsupportedOperationException()
                    }
                }
                apply()
            }
        }

        fun getBoolean(context: Context, key: String, defaultValue: Boolean) : Boolean {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences(context.getString(R.string.preference_file_key),
                    Context.MODE_PRIVATE)
            return sharedPreferences.getBoolean(key, defaultValue)
        }

        fun getInt(context: Context, key: String, defaultValue: Int) : Int {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences(context.getString(R.string.preference_file_key),
                    Context.MODE_PRIVATE)
            return sharedPreferences.getInt(key, defaultValue)
        }

        fun getFloat(context: Context, key: String, defaultValue: Float) : Float {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences(context.getString(R.string.preference_file_key),
                    Context.MODE_PRIVATE)
            return sharedPreferences.getFloat(key, defaultValue)
        }

        fun getLong(context: Context, key: String, defaultValue: Long) : Long {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences(context.getString(R.string.preference_file_key),
                    Context.MODE_PRIVATE)
            return sharedPreferences.getLong(key, defaultValue)
        }

        fun getString(context: Context, key: String, defaultValue: String) : String? {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences(context.getString(R.string.preference_file_key),
                    Context.MODE_PRIVATE)
            return sharedPreferences.getString(key, defaultValue)
        }
    }
}