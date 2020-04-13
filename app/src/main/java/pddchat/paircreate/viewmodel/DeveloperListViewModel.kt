package pddchat.paircreate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pddchat.paircreate.model.Developer

class DeveloperListViewModel : ViewModel() {

    private val _developer: MutableLiveData<List<Developer>> = MutableLiveData()
    val developer: LiveData<List<Developer>> = _developer

    private val registerData: ArrayList<Developer> = ArrayList()

    fun register(name: String) {
        registerData.add(Developer(name = name))
        _developer.value = registerData
    }

    fun observeDeveloper() {
        // TODO 現状ではデータを保存していないため、SharedPreference or ROOMに保存したデータを取得するようにする
    }
}