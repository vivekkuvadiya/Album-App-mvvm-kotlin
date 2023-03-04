package com.example.demoalbumapp.ui.photo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.demoalbumapp.R
import com.example.demoalbumapp.databinding.FragmentPhotoBinding
import com.squareup.picasso.Picasso

class PhotoFragment:Fragment(R.layout.fragment_photo) {

    private lateinit var binding:FragmentPhotoBinding
    private val photoArgs:PhotoFragmentArgs by navArgs()
    private val viewModel:PhotoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhotoBinding.bind(view)

        val photo = photoArgs.photo

        Picasso.get().load(photo.url).placeholder(R.drawable.loading).into(binding.ivHeader)
        binding.tvTitle.text = photo.title
        binding.tvAlbum.text = photo.albumId.toString()


    }

}