package com.example.demoalbumapp.ui.albums

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoalbumapp.R
import com.example.demoalbumapp.adapter.AlbumAdapter
import com.example.demoalbumapp.databinding.FragmentAlbumsBinding
import com.example.demoalbumapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsFragment:Fragment(R.layout.fragment_albums) {

    private lateinit var binding:FragmentAlbumsBinding
    private lateinit var albumAdapter:AlbumAdapter
    private val viewModel:AlbumsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAlbumsBinding.bind(view)

        viewModel.getAlbum()

        setupAlbumRecyclerview()

        setObserver()

        clickEvent()

    }

    private fun clickEvent(){
        binding.btnRetry.setOnClickListener{
            viewModel.getAlbum()
        }
    }

    private fun setObserver(){
        viewModel.albums.observe(viewLifecycleOwner){
            it?.let {
                when(it){
                    is Resource.Success -> {
                        binding.loading.isVisible = false
                        binding.btnRetry.isVisible = false
                        albumAdapter.submitList(it.data!!)
                    }
                    is Resource.Error -> {
                        binding.loading.isVisible = false
                        binding.btnRetry.isVisible = true
                        Toast.makeText(requireContext(), it.message?:"unknown error occurred", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading ->{
                        binding.loading.isVisible = true
                        binding.btnRetry.isVisible = false
                    }
                }
            }
        }
    }

    private fun setupAlbumRecyclerview(){
        binding.rvAlbums.apply {
            albumAdapter = AlbumAdapter{
                findNavController().navigate(AlbumsFragmentDirections.actionAlbumsFragmentToPhotoFragment(it))
            }
            adapter = albumAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

}