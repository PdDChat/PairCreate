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
        _developerList.value?.forEach {
            registerData.add(it)
        }

        registerData.add(Developer(name = newName))
        _developerList.value = registerData

        // List<String>のみをSharedPreferenceに保存する
        val strList: ArrayList<String> = ArrayList()
        registerData.forEach {
            strList.add(it.name)
        }

        PreferenceUtil.putStringListGson(context, KEY_GSON, _developerList.value)
    }

    fun observeDeveloper(context: Context?) {
        val devList: ArrayList<Developer> = ArrayList()
        val preStrList = PreferenceUtil.getStringListGson(context, KEY_GSON)

        preStrList.forEach {
            devList.add(it)
        }

        _developerList.value = devList
    }
}