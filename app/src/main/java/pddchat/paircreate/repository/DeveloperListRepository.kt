package pddchat.paircreate.repository

import android.content.Context
import pddchat.paircreate.model.Developer
import pddchat.paircreate.util.PreferenceUtil

class DeveloperListRepository {

    fun update(context: Context?, newName: String, developerList: List<Developer>?) : List<Developer> {
        val registerData: ArrayList<Developer> = ArrayList()

        // 以前登録している名前があれば、先に追加しておかないと上書きされる
        developerList?.forEach {
            registerData.add(it)
        }
        registerData.add(Developer(name = newName))

        PreferenceUtil.putDeveloperListGson(context, PreferenceUtil.PreferenceKey.KEY_GSON, registerData)

        return registerData
    }

    fun delete(context: Context?, deleteName: String, developerList: List<Developer>?) : List<Developer> {
        val registerData: ArrayList<Developer> = ArrayList()

        // 以前登録している名前があれば、先に追加しておかないと上書きされる
        developerList?.forEach {
            if (it.name != deleteName) {
                registerData.add(it)
            }
        }
        PreferenceUtil.putDeveloperListGson(context, PreferenceUtil.PreferenceKey.KEY_GSON, registerData)

        return registerData
    }

    fun getDeveloperlist(context: Context?) : List<Developer> {
        val devList: ArrayList<Developer> = ArrayList()

        val preDevList = PreferenceUtil.getDeveloperListGson(context, PreferenceUtil.PreferenceKey.KEY_GSON)
        preDevList.forEach { devList.add(it) }

        return devList
    }
}