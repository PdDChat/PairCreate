package pddchat.paircreate.util

import android.content.Context
import android.preference.PreferenceManager
import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


internal class PreferenceUtil  {

    enum class PreferenceKey {
        KEY_GSON
    }

    companion object {
        private val gson = Gson()

        fun putStringListGson(
            context: Context?,
            key: PreferenceKey,
            value: List<String?>?
        ) {
            val jsonValue = gson.toJson(value)
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putString(key.name, jsonValue)
            editor.apply()
        }

        fun getStringListGson(context: Context?, key: PreferenceKey): List<String> {
            val jsonValue = PreferenceManager.getDefaultSharedPreferences(context).getString(key.name, "")
            // jsonValueが空文字の場合、fromJson()でnullになるのでnewして返す
            val type = object : TypeToken<List<String?>?>() {}.type
            return if (TextUtils.isEmpty(jsonValue)) {
                arrayListOf()
            } else {
                gson.fromJson(jsonValue!!, type)
            }
        }
    }
}