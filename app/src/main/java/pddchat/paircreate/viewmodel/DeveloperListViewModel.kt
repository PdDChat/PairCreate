package pddchat.paircreate.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pddchat.paircreate.model.Developer
import pddchat.paircreate.repository.DeveloperListRepository

class DeveloperListViewModel : ViewModel() {

    private val _developerList: MutableLiveData<List<Developer>> = MutableLiveData()
    val developerList: LiveData<List<Developer>> = _developerList

    private val repository: DeveloperListRepository = DeveloperListRepository()

    fun register(context: Context?, newName: String) {
        val devList = _developerList.value
        _developerList.value = repository.update(context, newName, devList)
    }

    fun observeDeveloper(context: Context?) {
        _developerList.value = repository.getDeveloperlist(context)
    }
}