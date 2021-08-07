package pddchat.paircreate.data.repository

import pddchat.paircreate.data.model.Developer
import pddchat.paircreate.util.PreferenceUtil

class DeveloperListRepository {

    fun update(newName: String, developerList: List<Developer>?): List<Developer> {
        val registerData = ArrayList<Developer>()

        // 以前登録している名前があれば、先に追加しておかないと上書きされる
        developerList?.forEach {
            registerData.add(it)
        }
        registerData.add(Developer(name = newName))

        PreferenceUtil().putDeveloperListGson(PreferenceUtil.PreferenceKey.KEY_GSON, registerData)

        return registerData
    }

    fun delete(deleteName: String, developerList: List<Developer>?): List<Developer> {
        val registerData = ArrayList<Developer>()

        // 削除対象以外を追加する
        developerList?.forEach {
            if (it.name != deleteName) {
                registerData.add(it)
            }
        }
        PreferenceUtil().putDeveloperListGson(PreferenceUtil.PreferenceKey.KEY_GSON, registerData)

        return registerData
    }

    fun getDeveloperList(): List<Developer> {
        val devList = ArrayList<Developer>()

        val preDevList = PreferenceUtil().getDeveloperListGson(PreferenceUtil.PreferenceKey.KEY_GSON)
        preDevList.forEach { devList.add(it) }

        return devList
    }
}