package pddchat.paircreate.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import pddchat.paircreate.R
import pddchat.paircreate.viewmodel.DeveloperListViewModel

class DeveloperListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_developer_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = DeveloperListViewModel()
        viewModel.observeDeveloper()

        val adapter = DeveloperListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.developer_recycler_view)
        recyclerView.adapter = adapter.apply {
            val developer = viewModel.developer.value
            submitList(developer)
        }

        // 区切り線の追加
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        val button = view.findViewById<Button>(R.id.register_button)
        button.setOnClickListener {
            viewModel.register()
            Toast.makeText(context, "register button push!", Toast.LENGTH_SHORT).show()
        }
    }
}