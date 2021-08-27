package com.example.sampleapp

import com.example.helloworld.AlbumAdapter
import com.example.sampleapp.models.Album
import org.junit.Test

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.helloworld.AlbumViewHolder
import com.example.helloworld.UserFragment
import com.example.sampleapp.models.User
import com.example.sampleapp.network.NetworkFactory
import com.example.sampleapp.network.UserService
import com.example.sampleapp.repository.AlbumRepository
import com.example.sampleapp.viewModels.AlbumViewModel
import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito.`when`

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {



    @Test
    fun albumTest() {
        var album = Album(1,  2,  "AlbumTitle",
            "https://image.shutterstock.com/image-vector/url-letter-logo-design-on-260nw-1987470008.jpg"
        , "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSDf5FzvIbvJ-xHi0l_961iylf2TDO9VDB0RfHXeu4OOOS3hzyJ7oiXYggfJtLo1WyCCpM&usqp=CAU")

        assertTrue(album.albumId.toString() == "1")
        assertTrue(album.id.toString() == "2")
        assertTrue(album.title == "AlbumTitle")
        assertTrue(album.thumbnailUrl == "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSDf5FzvIbvJ-xHi0l_961iylf2TDO9VDB0RfHXeu4OOOS3hzyJ7oiXYggfJtLo1WyCCpM&usqp=CAU")
        assertTrue(album.url == "https://image.shutterstock.com/image-vector/url-letter-logo-design-on-260nw-1987470008.jpg")

        album.id = 100
        album.albumId = 200
        album.url = "url"
        album.thumbnailUrl = "thumbnail"

        assertTrue(album.id.toString() == "100")
        assertTrue(album.albumId.toString() == "200")
        assertTrue(album.url == "url")
        assertTrue(album.thumbnailUrl == "thumbnail")
    }

    @Test
    fun userTest() {
        var user = User(5, "William", "wh@optus.com", "000")

        assertTrue(user.id.toString() == "5")
        assertTrue(user.name == "William")
        assertTrue(user.email == "wh@optus.com")
        assertTrue(user.phone == "000")

        user.id = 144
        user.name = "NotWilliam"
        user.email = "NotWh@optus.com"
        user.phone = "911"

        assertTrue(user.id.toString() == "144")
        assertTrue(user.name == "NotWilliam")
        assertTrue(user.email == "NotWh@optus.com")
        assertTrue(user.phone == "911")

    }

    @Test
    fun networkFactoryTest() {
        var network = NetworkFactory()
        var networkService = network.getNetworkService()
        assertTrue(networkService is UserService)
    }





}