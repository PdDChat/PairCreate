package pddchat.paircreate.ui.view.developerlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pddchat.paircreate.R
import pddchat.paircreate.data.model.Developer
import pddchat.paircreate.databinding.ItemDeveloperListBindingImpl
import pddchat.paircreate.ui.view.developerlist.DeveloperListAdapter.DeveloperListItemViewHolder

private val ITEM_CALLBACK = object : DiffUtil.ItemCallback<Developer>() {
    override fun areItemsTheSame(oldItem: Developer, newItem: Developer): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Developer, newItem: Developer): Boolean =
        oldItem == newItem
}

internal class DeveloperListAdapter : ListAdapter<Developer, DeveloperListItemViewHolder>(ITEM_CALLBACK) {
    internal class DeveloperListItemViewHolder(
        private val binding: ItemDeveloperListBindingImpl
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(developer: Developer) {
            binding.developer = developer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DeveloperListItemViewHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_developer_list, parent, false)
    )

    override fun onBindViewHolder(holder: DeveloperListItemViewHolder, position: Int) = holder.bind(getItem(position))
}