package com.example.sampleapp.viewModels

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapp.models.Album
import com.example.sampleapp.repository.AlbumRepository

class AlbumViewModel : ViewModel() {

    private lateinit var albumRepository : AlbumRepository

    private lateinit var albumsLiveData : LiveData<List<Album>>

    fun init() {
        albumRepository = AlbumRepository()
        getAlbums()
        albumsLiveData = albumRepository.getAlbumsLiveData()
    }

    fun getAlbums() {
        albumRepository.getAlbums()
    }

    fun getAlbumsLiveData() : LiveData<List<Album>> {
        return albumsLiveData
    }

    fun getAlbumRepository() : AlbumRepository {
        return albumRepository
    }
}