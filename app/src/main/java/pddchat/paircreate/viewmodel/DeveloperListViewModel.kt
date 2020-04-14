package pddchat.paircreate.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pddchat.paircreate.model.Developer
import pddchat.paircreate.util.PreferenceUtil
import pddchat.paircreate.util.PreferenceUtil.PreferenceKey.KEY_GSON

class DeveloperListViewModel : ViewModel() {

    private val _developer: MutableLiveData<List<Developer>> = MutableLiveData()
    val developer: LiveData<List<Developer>> = _developer

    fun register(context: Context?, newName: String) {
        val registerData: ArrayList<Developer> = ArrayList()
        _developer.value?.forEach {
            registerData.add(it)
        }

        registerData.add(Developer(name = newName))
        _developer.value = registerData

        // List<String>のみをSharedPreferenceに保存する
        val strList: ArrayList<String> = ArrayList()
        registerData.forEach {
            strList.add(it.name)
        }
        
        PreferenceUtil.putStringListGson(context, KEY_GSON, strList)
    }

    fun observeDeveloper(context: Context?) {
        val devList: ArrayList<Developer> = ArrayList()
        val preStrList = PreferenceUtil.getStringListGson(context, KEY_GSON)

        preStrList.forEach {
            devList.add(Developer(name = it))
        }

        _developer.value = devList
    }
}