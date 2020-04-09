package pddchat.paircreate.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import pddchat.paircreate.R
import pddchat.paircreate.model.Developer
import pddchat.paircreate.model.Pair

class PairListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pair_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PairListAdapter()

        val recyclerView = view.findViewById<RecyclerView>(R.id.pair_recycler_view)
        recyclerView.adapter = adapter.apply {
            submitList(listOf(
                // TODO 仮データのため、developer情報の登録導線を追加する
                Pair(teamNumber = 1, developer = listOf(Developer(name = "Dev1"), Developer(name = "Dev2"))),
                Pair(teamNumber = 2, developer = listOf(Developer(name = "Dev3"), Developer(name = "Dev4")))
            ))
        }

        // 区切り線の追加
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }
}