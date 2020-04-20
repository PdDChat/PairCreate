package pddchat.paircreate.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pddchat.paircreate.data.model.Developer
import pddchat.paircreate.data.repository.DeveloperListRepository

class DeveloperListViewModel : ViewModel() {

    private val _developerList: MutableLiveData<List<Developer>> = MutableLiveData()
    val developerList: LiveData<List<Developer>> = _developerList

    private val repository: DeveloperListRepository = DeveloperListRepository()

    fun register(context: Context?, newName: String) {
        val devList = _developerList.value
        _developerList.value = repository.update(context, newName, devList)
    }

    fun delete(context: Context?, deleteName: String) {
        val devList = _developerList.value
        _developerList.value = repository.delete(context, deleteName, devList)
    }

    fun observeDeveloper(context: Context?) {
        _developerList.value = repository.getDeveloperlist(context)
    }
}