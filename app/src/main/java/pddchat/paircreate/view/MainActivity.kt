package pddchat.paircreate.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pddchat.paircreate.R
import pddchat.paircreate.model.Developer
import pddchat.paircreate.model.Pair


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpView()
    }

    private fun setUpView() {
        val adapter = PairListAdapter()

        val recyclerView = findViewById<RecyclerView>(R.id.pair_recycler_view)
        recyclerView.adapter = adapter.apply {
            submitList(listOf(
                // TODO 仮データのため、developer情報の登録導線を追加する
                Pair(teamNumber = 1, developer = listOf(Developer(name = "Dev1"), Developer(name = "Dev2"))),
                Pair(teamNumber = 2, developer = listOf(Developer(name = "Dev3"), Developer(name = "Dev4")))
            ))
        }
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 区切り線の追加
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }
}