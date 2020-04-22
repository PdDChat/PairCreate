package pddchat.paircreate.ui.view.developerlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import pddchat.paircreate.databinding.FragmentDeveloperListBinding
import pddchat.paircreate.ui.viewmodel.DeveloperListViewModel

class DeveloperListFragment : Fragment() {

    private lateinit var binding: FragmentDeveloperListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDeveloperListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = DeveloperListViewModel()
        binding.viewModel = viewModel
        binding.developerRecyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        val adapter = DeveloperListAdapter()
        binding.developerRecyclerView.adapter = adapter

        viewModel.observeDeveloper()
        viewModel.developerList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        fetchDeveloperList(viewModel, binding.developerRecyclerView)
    }

    private fun fetchDeveloperList(viewModel: DeveloperListViewModel, recyclerView: RecyclerView) {
        viewModel.developerList.observe(viewLifecycleOwner, Observer {
            val adapter = DeveloperListAdapter()
            recyclerView.adapter = adapter.apply {
                val developers = viewModel.developerList.value
                submitList(developers)
            }
        })
    }
}