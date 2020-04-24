package pddchat.paircreate.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pddchat.paircreate.data.model.PairInfo
import pddchat.paircreate.data.repository.PairListRepository

class PairListViewModel : ViewModel() {

    private val _pairList: MutableLiveData<List<PairInfo>> = MutableLiveData()
    val pairList: LiveData<List<PairInfo>> = _pairList

    private val repository: PairListRepository = PairListRepository()

    fun observePairList() {
        _pairList.value = repository.createPairList()
    }
}