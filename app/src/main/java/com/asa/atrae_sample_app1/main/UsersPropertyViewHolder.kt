package com.asa.atrae_sample_app1.main

import androidx.recyclerview.widget.RecyclerView
import com.asa.atrae_sample_app1.databinding.ListItemBinding
import com.asa.atrae_sample_app1.network.UsersProperties

class UsersPropertyViewHolder(private var binding: ListItemBinding):
    RecyclerView.ViewHolder(binding.root) {
    fun bind(usersProperties: UsersProperties) {
        binding.property = usersProperties
        binding.executePendingBindings()
    }
}