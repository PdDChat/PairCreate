package pddchat.paircreate.ui.developerlist

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pddchat.paircreate.App.Companion.context
import pddchat.paircreate.data.model.Developer
import pddchat.paircreate.data.repository.DeveloperListRepository

class DeveloperListViewModel : ViewModel() {

    val name = MutableLiveData<String>()

    private val _developerList: MutableLiveData<List<Developer>> = MutableLiveData()
    val developerList: LiveData<List<Developer>> = _developerList

    private val repository: DeveloperListRepository = DeveloperListRepository()

    fun observeDeveloper() {
        _developerList.value = repository.getDeveloperList()
    }

    fun register() {
        val devList = _developerList.value
        val newName = name.value ?: ""

        if (newName.isEmpty()) {
            Toast.makeText(context, "登録する名前を入力してください", Toast.LENGTH_SHORT).show()
            return
        }

        _developerList.value = repository.update(newName, devList)
    }

    fun delete() {
        val devList = _developerList.value
        val deleteName = name.value ?: ""

        if (deleteName.isEmpty()) {
            Toast.makeText(context, "削除する名前を入力してください", Toast.LENGTH_SHORT).show()
            return
        }

        _developerList.value = repository.delete(deleteName, devList)
    }
}