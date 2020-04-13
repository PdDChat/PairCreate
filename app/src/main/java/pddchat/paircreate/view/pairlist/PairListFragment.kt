package pddchat.paircreate.view.pairlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import pddchat.paircreate.R
import pddchat.paircreate.viewmodel.PairListViewModel

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

        val viewModel = PairListViewModel()
        viewModel.observePairInfo()

        val adapter = PairListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.pair_recycler_view)
        recyclerView.adapter = adapter.apply {
            val pairInfo = viewModel.PairInfo.value
            submitList(pairInfo)
        }

        // 区切り線の追加
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        val button = view.findViewById<Button>(R.id.setting_button)
        button.setOnClickListener {
            val navController = this.findNavController()
            navController.navigate(R.id.pair_list_to_developer_list)
        }
    }
}