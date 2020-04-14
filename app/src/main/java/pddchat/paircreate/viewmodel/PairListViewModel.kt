package pddchat.paircreate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pddchat.paircreate.model.Developer
import pddchat.paircreate.model.PairInfo

class PairListViewModel : ViewModel() {

    private val _pairList: MutableLiveData<List<PairInfo>> = MutableLiveData()
    val pairList: LiveData<List<PairInfo>> = _pairList

    fun observePairList() {
        // TODO 仮データのため、developer情報の登録導線を追加する
        _pairList.value = listOf(
            PairInfo(
                developers = listOf(Developer(name = "Dev1"), Developer(name = "Dev2"))
            ),
            PairInfo(
                developers = listOf(Developer(name = "Dev3"), Developer(name = "Dev4"))
            )
        )
    }
}