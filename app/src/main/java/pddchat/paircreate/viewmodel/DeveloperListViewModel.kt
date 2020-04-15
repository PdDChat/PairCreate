package pddchat.paircreate.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pddchat.paircreate.model.Developer
import pddchat.paircreate.util.PreferenceUtil
import pddchat.paircreate.util.PreferenceUtil.PreferenceKey.KEY_GSON

class DeveloperListViewModel : ViewModel() {

    private val _developerList: MutableLiveData<List<Developer>> = MutableLiveData()
    val developerList: LiveData<List<Developer>> = _developerList

    fun register(context: Context?, newName: String) {
        val registerData: ArrayList<Developer> = ArrayList()

        // 以前登録している名前があれば、先に追加しておかないと上書きされる
        _developerList.value?.forEach {
            registerData.add(it)
        }

        registerData.add(Developer(name = newName))
        _developerList.value = registerData

        PreferenceUtil.putDeveloperListGson(context, KEY_GSON, registerData)
    }

    fun observeDeveloper(context: Context?) {
        val devList: ArrayList<Developer> = ArrayList()
        val preDevList = PreferenceUtil.getDeveloperListGson(context, KEY_GSON)

        preDevList.forEach {
            devList.add(it)
        }

        _developerList.value = devList
    }
}