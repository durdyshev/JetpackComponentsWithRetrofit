package com.example.jetpackcomponentswithretrofit.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackcomponentswithretrofit.adapters.JsonRecyclerViewAdapter
import com.example.jetpackcomponentswithretrofit.databinding.FragmentMainListBinding
import com.example.jetpackcomponentswithretrofit.model.PhotoData
import com.example.jetpackcomponentswithretrofit.viewmodel.MainListFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainListFragment : Fragment() {

    private lateinit var binding: FragmentMainListBinding
    private lateinit var jsonAdapter: JsonRecyclerViewAdapter
    private lateinit var mainListFragmentViewModel: MainListFragmentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainListBinding.inflate(inflater, container, false)

        mainListFragmentViewModel = ViewModelProvider(this)[MainListFragmentViewModel::class.java]
        mainListFragmentViewModel.loadPhoto()
        mainListFragmentViewModel.photoList.observe(requireActivity()) {
            initRecyclerView(requireContext(), it)
        }
        return binding.root


    }

    private fun initRecyclerView(context: Context, photoData: List<PhotoData>) {
        jsonAdapter= JsonRecyclerViewAdapter(context,photoData)
        binding.mainFragmentRecyclerview.adapter = jsonAdapter
        binding.mainFragmentRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        jsonAdapter.setOnClickItem {
            val action=MainListFragmentDirections.actionMainListFragmentToDetailsFragment(it.id)
            findNavController().navigate(action)
        }
    }
}