package pddchat.paircreate.util

import android.text.TextUtils
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pddchat.paircreate.App.Companion.context
import pddchat.paircreate.data.model.Developer

class PreferenceUtil  {

    enum class PreferenceKey {
        KEY_GSON
    }

    private val gson = Gson()

    fun putDeveloperListGson(key: PreferenceKey, value: List<Developer?>?) {
        val jsonValue = gson.toJson(value)
        val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
        editor.putString(key.name, jsonValue)
        editor.apply()
    }

    fun getDeveloperListGson(key: PreferenceKey): List<Developer> {
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