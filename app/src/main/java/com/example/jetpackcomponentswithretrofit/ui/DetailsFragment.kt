package com.example.jetpackcomponentswithretrofit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomponentswithretrofit.viewmodel.DetailFragmentViewModel
import com.example.jetpackcomponentswithretrofit.R
import com.example.jetpackcomponentswithretrofit.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var id: String
    private lateinit var detailsFragmentViewModel: DetailFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        id = requireArguments().getString("id", "") as String
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        detailsFragmentViewModel = ViewModelProvider(this)[DetailFragmentViewModel::class.java]
        detailsFragmentViewModel.loadPhoto(id)

        detailsFragmentViewModel.photo.observe(viewLifecycleOwner, Observer { photo ->
            photo?.let {
                binding.selectedPhoto = photo
            }
        })
        return binding.root
    }

}