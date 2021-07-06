package com.asa.atrae_sample_app1.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.asa.atrae_sample_app1.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        val binding = FragmentMainBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = mainFragmentViewModel

        binding.listCard.adapter = MainAdapter(mainFragmentViewModel)

        val separateLine = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        binding.listCard.addItemDecoration(separateLine)

        return binding.root
    }
}