package pddchat.paircreate.data.repository

import pddchat.paircreate.data.model.Developer
import pddchat.paircreate.data.model.PairInfo
import pddchat.paircreate.util.PreferenceUtil

class PairListRepository {

    fun createPairList(): List<PairInfo> {
        val devList = PreferenceUtil().getDeveloperListGson(PreferenceUtil.PreferenceKey.KEY_GSON)
        val pairList = arrayListOf<PairInfo>()
        val groupA = arrayListOf<String>()
        val groupB = arrayListOf<String>()
        val shuffledDevList = arrayListOf<Developer>().apply {
            addAll(devList.shuffled())
        }

        val numList = mutableListOf<Pair<Int, Developer>>()
        devList.indices.forEach { i -> numList.add((i to shuffledDevList[i])) }

        // 受け取ったListを2分割する
        numList.map {
            if (it.first % 2 == 0) groupA.add(it.second.name)
            else groupB.add(it.second.name)
        }

        // 分割したListをそれぞれ順番に当てはめている。ソロ発生時はnullを返す
        groupA.indices.forEach { i ->
            val newGroupB: String? = if (i < groupB.size) groupB[i] else null

            pairList.add(
                PairInfo(
                    pairNo = i,
                    pairName1 = groupA[i],
                    pairName2 = newGroupB
                )
            )
        }

        return pairList
    }
}