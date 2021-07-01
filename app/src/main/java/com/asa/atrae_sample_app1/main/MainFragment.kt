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
        binding.lifecycleOwner = viewLifecycleOwner

        // Giving the binding access to the MainViewModel
        binding.viewModel = mainFragmentViewModel


//        // Sets the adapter of the photosGrid RecyclerView with clickHandler lambda that
//        // tells the viewModel when our property is clicked
//        binding.photosGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
//            viewModel.displayPropertyDetails(it)
//        })

        // Observe the navigateToSelectedProperty LiveData and Navigate when it isn't null
        // After navigating, call displayPropertyDetailsComplete() so that the ViewModel is ready
        // for another navigation event.
        mainFragmentViewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                // Must find the NavController from the Fragment
                //TODO ここで詳細画面にitを渡してあげる必要がある
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToDetailFragment())
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                mainFragmentViewModel.displayPropertyDetailsComplete()
            }
        })

        mainFragmentViewModel.properties.observe(viewLifecycleOwner, Observer {
            if(null != it){
                binding.mainId.text = it[0].id.toString()
                binding.mainTitle.text = it[0].email
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.button_example)
        button.setOnClickListener{
            findNavController().navigate(
                R.id.action_mainFragment_to_detailFragment)
        }
    }
}