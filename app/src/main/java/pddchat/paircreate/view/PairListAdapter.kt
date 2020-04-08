package pddchat.paircreate.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pddchat.paircreate.R
import pddchat.paircreate.model.Pair
import pddchat.paircreate.view.PairListAdapter.PairListItemViewHolder

private val ITEM_CALLBACK = object : DiffUtil.ItemCallback<Pair>() {
    override fun areItemsTheSame(oldItem: Pair, newItem: Pair): Boolean =
        oldItem.teamNumber == newItem.teamNumber

    override fun areContentsTheSame(oldItem: Pair, newItem: Pair): Boolean =
        oldItem == newItem
}

internal class PairListAdapter : ListAdapter<Pair, PairListItemViewHolder>(ITEM_CALLBACK) {

    internal class PairListItemViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_pair,
                parent,
                false
            )
        ) {

        private val pairName1: TextView = itemView.findViewById(R.id.pair_name1)
        private val pairName2: TextView = itemView.findViewById(R.id.pair_name2)

        fun bind(pair: Pair) {
            pairName1.text = pair.developer[0].name
            pairName2.text = pair.developer[1].name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PairListItemViewHolder(parent)

    override fun onBindViewHolder(holder: PairListItemViewHolder, position: Int) = holder.bind(getItem(position))
}