package com.example.helloworld


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp.*
import com.example.sampleapp.databinding.AlbumListBinding
import com.example.sampleapp.models.Album
import java.sql.DriverManager
import java.util.*
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
//import androidx.compose.runtime.*
import com.example.sampleapp.R

import com.example.sampleapp.fragment.BaseFragment
import androidx.navigation.fragment.findNavController
import com.example.sampleapp.viewModels.AlbumViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [AlbumFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlbumFragment() : BaseFragment() {
    //private var albumId : Int = albumId
    private var view1: View? = null
    private val baseUrl = "https://jsonplaceholder.typicode.com/"
    private var fragment: Fragment? = null
    private val thumbnailFragment : String = "Thumbnail Fragment"
    private var albums = mutableListOf<Album>()
    private val albumTitle = 2;

    private lateinit var albumViewModel: AlbumViewModel

    private val args : AlbumFragmentArgs by navArgs()

    //private var userViewModel : UserViewModel
    private var albumAdapter: AlbumAdapter? = null;
    private lateinit var binding: AlbumListBinding;

    //val args : AlbumFragmentArgs by navArgs<>()

    @SuppressLint("ResourceType")
    override fun getLayoutId(): Int {
        return R.layout.album_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inflate the layout for this fragment
        val albumId = args.position

        albumViewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)
        albumViewModel.init()

        binding = baseBinding as AlbumListBinding
        albumViewModel.getAlbumsLiveData().observe(viewLifecycleOwner, Observer { data ->
            for (item in data) {
                if (item.albumId == albumId) {
                    albums.add(item)
                }
            }
            albumAdapter = AlbumAdapter(context, R.layout.album_info, albums)
            binding.albumList.adapter = albumAdapter
            DriverManager.println("debug -- > ${data.toString()}")
        })

        Log.d("userAlbumAdapter", albumAdapter.toString())
        val albumInfo = binding.albumList
        albumInfo.adapter = albumAdapter

        //retro fit call should not be called on main thread
        //big system calls to a database etc. shoould be implemented with a viewModel
        //viewModel is like hiding the business logic

        binding.title = "Album ID: $albumId"
        //Log.d("Users list", "The length of the list of Users is" + userViewModel!!.getUsersLiveData().)
        albumInfo.setOnItemClickListener { adapterView: AdapterView<*>, view: View, i: Int, l: Long ->
            Log.e("position id", i.toString())
            val action = AlbumFragmentDirections.actionAlbumFragmentToThumbnailFragment(albums[i])
            findNavController().navigate(action)
        }
        Log.d("Album adapter set", "Album adapter set")
    }
}