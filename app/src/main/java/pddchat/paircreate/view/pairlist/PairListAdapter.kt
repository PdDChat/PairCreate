package pddchat.paircreate.view.pairlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pddchat.paircreate.R
import pddchat.paircreate.model.PairInfo
import pddchat.paircreate.view.pairlist.PairListAdapter.PairListItemViewHolder

private val ITEM_CALLBACK = object : DiffUtil.ItemCallback<PairInfo>() {
    override fun areItemsTheSame(oldItem: PairInfo, newItem: PairInfo): Boolean =
        oldItem.teamNumber == newItem.teamNumber

    override fun areContentsTheSame(oldItem: PairInfo, newItem: PairInfo): Boolean =
        oldItem == newItem
}

internal class PairListAdapter : ListAdapter<PairInfo, PairListItemViewHolder>(ITEM_CALLBACK) {

    internal class PairListItemViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_pair_list,
                parent,
                false
            )
        ) {

        private val pairName1: TextView = itemView.findViewById(R.id.pair_name1)
        private val pairName2: TextView = itemView.findViewById(R.id.pair_name2)

        fun bind(pairInfo: PairInfo) {
            pairName1.text = pairInfo.developer[0].name
            pairName2.text = pairInfo.developer[1].name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PairListItemViewHolder(parent)

    override fun onBindViewHolder(holder: PairListItemViewHolder, position: Int) = holder.bind(getItem(position))
}