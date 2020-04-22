package pddchat.paircreate.ui.view.developerlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import pddchat.paircreate.R
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

        var registerName = ""
        val editText = view.findViewById<EditText>(R.id.edit_developer_name)
        editText.setOnClickListener {
            // TODO キーボード制御
            if (editText.text != null) {
                registerName = editText.text.toString()
            }
        }

        val registerButton = view.findViewById<Button>(R.id.register_button)
        registerButton.setOnClickListener {
            if (registerName.isEmpty()) {
                Toast.makeText(context, "登録する名前を入力してください", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.register(registerName)

            // 登録名の初期化
            registerName = ""
            editText.text.clear()
        }

        val deleteButton = view.findViewById<Button>(R.id.delete_button)
        deleteButton.setOnClickListener {
            if (registerName.isEmpty()) {
                Toast.makeText(context, "削除する名前を入力してください", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.delete(registerName)

            // 削除名の初期化
            registerName = ""
            editText.text.clear()
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