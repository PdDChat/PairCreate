package pddchat.paircreate.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pddchat.paircreate.R
import pddchat.paircreate.model.Developer
import pddchat.paircreate.view.DeveloperListAdapter.DeveloperListItemViewHolder

private val ITEM_CALLBACK = object : DiffUtil.ItemCallback<Developer>() {
    override fun areItemsTheSame(oldItem: Developer, newItem: Developer): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Developer, newItem: Developer): Boolean =
        oldItem == newItem
}

internal class DeveloperListAdapter : ListAdapter<Developer, DeveloperListItemViewHolder>(ITEM_CALLBACK) {

    internal class DeveloperListItemViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_developer_list,
                parent,
                false
            )
        ) {

        private val devName: TextView = itemView.findViewById(R.id.developer_name)

        fun bind(developer: Developer) {
            devName.text = developer.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DeveloperListItemViewHolder(parent)

    override fun onBindViewHolder(holder: DeveloperListItemViewHolder, position: Int) = holder.bind(getItem(position))
}