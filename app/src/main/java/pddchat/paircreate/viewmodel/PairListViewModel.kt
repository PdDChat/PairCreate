package pddchat.paircreate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pddchat.paircreate.model.Developer
import pddchat.paircreate.model.PairInfo

class PairListViewModel : ViewModel() {

    private val _pairInfo: MutableLiveData<List<PairInfo>> = MutableLiveData()
    val PairInfo: LiveData<List<PairInfo>> = _pairInfo

    fun observePairInfo() {
        // TODO 仮データのため、developer情報の登録導線を追加する
        _pairInfo.value = listOf(
            PairInfo(
                teamNumber = 1,
                developer = listOf(Developer(name = "Dev1"), Developer(name = "Dev2"))
            ),
            PairInfo(
                teamNumber = 2,
                developer = listOf(Developer(name = "Dev3"), Developer(name = "Dev4"))
            )
        )
    }
}