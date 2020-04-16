package pddchat.paircreate.repository

import android.content.Context
import pddchat.paircreate.model.Developer
import pddchat.paircreate.model.PairInfo
import pddchat.paircreate.util.PreferenceUtil
import java.util.ArrayList

class PairListRepository {

    fun createPairList(context: Context?): List<PairInfo> {
        val devList = PreferenceUtil.getDeveloperListGson(context, PreferenceUtil.PreferenceKey.KEY_GSON)
        val pairList: ArrayList<PairInfo> = arrayListOf()
        val groupA: ArrayList<String> = arrayListOf()
        val groupB: ArrayList<String> = arrayListOf()
        val shuffledDevList: ArrayList<Developer> = arrayListOf()
        shuffledDevList.addAll(devList.shuffled())

        val numList: MutableList<Pair<Int, Developer>> = mutableListOf()
        devList.indices.forEach { i -> numList.add((i to shuffledDevList[i])) }

        // 受け取ったListを2分割する
        numList.map {
            when {
                it.first % 2 == 0 -> groupA.add(it.second.name)
                else -> groupB.add(it.second.name)
            }
        }

        // 分割したListをそれぞれ順番に当てはめている。ソロ発生時はnullを返す
        groupA.indices.forEach { i ->
            val newGroupB: String? = if (i < groupB.size ) { groupB[i] } else { null }

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