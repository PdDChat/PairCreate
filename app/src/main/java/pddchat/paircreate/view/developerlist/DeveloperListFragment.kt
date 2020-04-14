package pddchat.paircreate.view.developerlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import pddchat.paircreate.R
import pddchat.paircreate.viewmodel.DeveloperListViewModel

class DeveloperListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

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
        viewModel.observeDeveloper(context)

        val adapter = DeveloperListAdapter()
        recyclerView = view.findViewById<RecyclerView>(R.id.developer_recycler_view)
        recyclerView.adapter = adapter.apply {
            val developers = viewModel.developerList.value
            submitList(developers)
        }

        // 区切り線の追加
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

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
            viewModel.register(context, registerName)

            // 登録名の初期化
            registerName = ""
            editText.text.clear()
        }

        fetchDeveloperList(viewModel)
    }

    private fun fetchDeveloperList(viewModel: DeveloperListViewModel) {
        viewModel.developerList.observe(viewLifecycleOwner, Observer {
            val adapter = DeveloperListAdapter()
            recyclerView.adapter = adapter.apply {
                val developers = viewModel.developerList.value
                submitList(developers)
            }
        })
    }
}