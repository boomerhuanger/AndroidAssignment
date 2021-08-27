package com.example.sampleapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.sampleapp.databinding.ThumbnailInfoBinding
import com.example.sampleapp.databinding.UserListBinding
import com.example.sampleapp.fragment.BaseFragment
import com.example.sampleapp.models.Album
import com.squareup.picasso.Picasso

class ThumbnailFragment () : BaseFragment(){
    private var view1: View? = null
    private lateinit var binding:ThumbnailInfoBinding;
    private val photoTitle = 1;
    private val albumTitle = 2;

    private val args : ThumbnailFragmentArgs by navArgs()

    override fun getLayoutId(): Int {
        return R.layout.thumbnail_info
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val album = args.album
        super.onViewCreated(view, savedInstanceState)

        binding = baseBinding as ThumbnailInfoBinding

        Picasso.get().load(album.url).into(binding.thumbnail)
        binding.text = album.title
        binding.albumID = "Album ID: ${album.albumId}"
        binding.photoID = "Photo ID: ${album.id}"

    }
}