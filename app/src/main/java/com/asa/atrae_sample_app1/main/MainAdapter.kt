
package com.asa.atrae_sample_app1.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asa.atrae_sample_app1.databinding.ListItemBinding
import com.asa.atrae_sample_app1.network.UsersProperties

class MainAdapter(private val viewModel: MainViewModel) : RecyclerView.Adapter<UsersPropertyViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): UsersPropertyViewHolder {
        return UsersPropertyViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return viewModel.properties.value?.data?.size ?: 0
    }

    override fun onBindViewHolder(holder: UsersPropertyViewHolder, position: Int) {
        viewModel.properties.value?.data?.let {
            holder.bind(it[position])
        }
    }
}
