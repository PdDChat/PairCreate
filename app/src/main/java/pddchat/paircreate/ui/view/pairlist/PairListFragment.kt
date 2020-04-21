package pddchat.paircreate.ui.view.pairlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.lifecycle.observe
import pddchat.paircreate.R
import pddchat.paircreate.databinding.FragmentPairListBinding
import pddchat.paircreate.ui.viewmodel.PairListViewModel

class PairListFragment : Fragment() {

    private lateinit var binding: FragmentPairListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPairListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = PairListViewModel()
        binding.viewModel = viewModel
        // 区切り線の追加
        binding.pairRecyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        val adapter = PairListAdapter()
        binding.pairRecyclerView.adapter = adapter

        viewModel.observePairList()
        viewModel.pairList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.settingButton.setOnClickListener{
            findNavController().navigate(R.id.pair_list_to_developer_list)
        }
    }
}