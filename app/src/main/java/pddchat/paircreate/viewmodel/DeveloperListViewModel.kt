package pddchat.paircreate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pddchat.paircreate.model.Developer
import pddchat.paircreate.model.PairInfo

class DeveloperListViewModel : ViewModel() {

    private val _developer: MutableLiveData<List<Developer>> = MutableLiveData()
    val developer: LiveData<List<Developer>> = _developer

    private var count = 0

    fun register() {
        count++
        // TODo　一旦、名前+countしたものをリストに追加表示させる
    }

    fun observeDeveloper() {
        // TODO 仮データのため、developer情報の登録情報を設定する
        _developer.value = listOf(
            Developer(name = "Dev1"),
            Developer(name = "Dev2")
        )
    }
}