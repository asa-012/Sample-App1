package com.asa.atrae_sample_app1.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.asa.atrae_sample_app1.R
import com.asa.atrae_sample_app1.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    /**
     * Lazily initialize our [MainViewModel].
     */
    private val mainFragmentViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentMainBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the MainViewModel
        binding.viewModel = mainFragmentViewModel

        binding.listCard.adapter = MainAdapter(mainFragmentViewModel)

        val separateLine = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        binding.listCard.addItemDecoration(separateLine)

        return binding.root
    }
}