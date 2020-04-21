package pddchat.paircreate.ui.view.pairlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pddchat.paircreate.R
import pddchat.paircreate.data.model.PairInfo
import pddchat.paircreate.databinding.ItemPairListBindingImpl
import pddchat.paircreate.ui.view.pairlist.PairListAdapter.PairListItemViewHolder

private val ITEM_CALLBACK = object : DiffUtil.ItemCallback<PairInfo>() {
    override fun areItemsTheSame(oldItem: PairInfo, newItem: PairInfo): Boolean =
        oldItem.pairNo == newItem.pairNo

    override fun areContentsTheSame(oldItem: PairInfo, newItem: PairInfo): Boolean =
        oldItem == newItem
}

internal class PairListAdapter : ListAdapter<PairInfo, PairListItemViewHolder>(ITEM_CALLBACK) {
    internal class PairListItemViewHolder(
        private val binding: ItemPairListBindingImpl
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pairInfo: PairInfo) {
            binding.pairInfo = pairInfo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PairListItemViewHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_pair_list, parent, false)
    )

    override fun onBindViewHolder(holder: PairListItemViewHolder, position: Int) = holder.bind(getItem(position))
}