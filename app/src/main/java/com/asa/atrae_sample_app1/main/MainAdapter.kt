
package com.asa.atrae_sample_app1.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.asa.atrae_sample_app1.databinding.ListItemBinding
import com.asa.atrae_sample_app1.network.UsersProperties

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 * @param onClick a lambda that takes the
 */
class MainAdapter() :
        ListAdapter<UsersProperties, MainAdapter.UsersPropertyViewHolder>(DiffCallback) {
    /**
     * The MarsPropertyViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [MarsProperty] information.
     */
    class UsersPropertyViewHolder(private var binding: ListItemBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(usersProperties: UsersProperties) {
            binding.property = usersProperties
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [MarsProperty]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<UsersProperties>() {
        override fun areItemsTheSame(oldItem: UsersProperties, newItem: UsersProperties): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: UsersProperties, newItem: UsersProperties): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): UsersPropertyViewHolder {
        return UsersPropertyViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: UsersPropertyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}
