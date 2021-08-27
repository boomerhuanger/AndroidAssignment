package com.example.sampleapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.sampleapp.models.Album
import com.example.sampleapp.repository.AlbumRepository
import com.example.sampleapp.viewModels.AlbumViewModel
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MockTest {

    @Rule @JvmField
    public var rule: TestRule = InstantTaskExecutorRule()

    private var albums = mutableListOf<Album>()





    //@Mock
    //private lateinit var albumsLiveData: MutableLiveData<List<Album>>

    @Mock
    private lateinit var albumModel : AlbumViewModel

    @Mock
    private lateinit var repo : AlbumRepository

    @Test
    fun albumModelTest()  {


        var album1 = Album(3, 100, "First", "url1", "thumbnailurl")
        var album2 = Album(5, 140, "Second", "url2", "thumbnailurl2")

        albums.add(album1)
        albums.add(album2)

        var albumsLiveData = MutableLiveData<List<Album>>()

        albumsLiveData.value = albums


        //Mockito.`when`(albumModel.getAlbums()).thenReturn(albumsLiveData)


        albumModel = AlbumViewModel()

        Mockito.`when`(repo.getAlbumsLiveData()).thenReturn(albumsLiveData)

        Assert.assertTrue(albumModel.getAlbumsLiveData().value?.size == 2)
        Assert.assertTrue(albumModel.getAlbumsLiveData().value?.get(0) == album1)
        Assert.assertTrue(albumModel.getAlbumsLiveData().value?.get(1) == album2)
        albumModel.getAlbumRepository()



    }
}