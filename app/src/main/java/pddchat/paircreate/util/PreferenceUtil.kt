package pddchat.paircreate.util

import android.content.Context
import android.text.TextUtils
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pddchat.paircreate.model.Developer

internal class PreferenceUtil  {

    enum class PreferenceKey {
        KEY_GSON
    }

    companion object {
        private val gson = Gson()

        fun putDeveloperListGson(context: Context?, key: PreferenceKey, value: List<Developer?>?) {
            val jsonValue = gson.toJson(value)
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putString(key.name, jsonValue)
            editor.apply()
        }

        fun getDeveloperListGson(context: Context?, key: PreferenceKey): List<Developer> {
            val jsonValue = PreferenceManager.getDefaultSharedPreferences(context).getString(key.name, "")
            // jsonValueが空文字の場合、fromJson()でnullになるのでnewして返す
            val type = object : TypeToken<List<Developer?>?>() {}.type
            return if (TextUtils.isEmpty(jsonValue)) {
                listOf()
            } else {
                gson.fromJson(jsonValue!!, type)
            }
        }
    }
}