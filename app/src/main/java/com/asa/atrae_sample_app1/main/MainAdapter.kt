
package com.asa.atrae_sample_app1.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.asa.atrae_sample_app1.databinding.ListItemBinding
import com.asa.atrae_sample_app1.network.UsersProperties

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 * @param onClick a lambda that takes the
 */
class MainAdapter(private val viewModel: MainViewModel) : RecyclerView.Adapter<MainAdapter.UsersPropertyViewHolder>()  {
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
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): UsersPropertyViewHolder {
        return UsersPropertyViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }
//
//    override fun onBindViewHolder(holder: UsersPropertyViewHolder, position: Int) {
//        val usersItem = getItem(position) as UsersProperties
//        holder.bind(usersItem)
//    }

    override fun getItemCount(): Int {
        return viewModel.properties.value?.data?.size ?: 0
    }

    override fun onBindViewHolder(holder: UsersPropertyViewHolder, position: Int) {
        viewModel.properties.value?.data?.let {
            holder.bind(it[position])
        }
    }
}
