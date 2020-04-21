package pddchat.paircreate.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pddchat.paircreate.data.model.Developer
import pddchat.paircreate.data.repository.DeveloperListRepository

class DeveloperListViewModel : ViewModel() {

    private val _developerList: MutableLiveData<List<Developer>> = MutableLiveData()
    val developerList: LiveData<List<Developer>> = _developerList

    private val repository: DeveloperListRepository = DeveloperListRepository()

    fun register(newName: String) {
        val devList = _developerList.value
        _developerList.value = repository.update(newName, devList)
    }

    fun delete(deleteName: String) {
        val devList = _developerList.value
        _developerList.value = repository.delete(deleteName, devList)
    }

    fun observeDeveloper() {
        _developerList.value = repository.getDeveloperlist()
    }
}