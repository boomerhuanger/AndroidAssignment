package com.example.helloworld

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.R
import com.example.sampleapp.databinding.AlbumInfoBinding
import com.example.sampleapp.models.Album
import com.squareup.picasso.Picasso

class AlbumAdapter(private val mContext: Context?, private val resource: Int, albums: List<Album>?) :
    ArrayAdapter<Album?>(mContext!!, resource, albums!!) {
    private var holder: AlbumViewHolder? = null
    private lateinit var binding : AlbumInfoBinding;

    //getting the view and attach it to the ListView
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //var convertView = convertView
        val id = getItem(position)!!.id
        val albumId = getItem(position)!!.albumId
        val title = getItem(position)!!.title
        val url = getItem(position)!!.url
        val thumbnailUrl = getItem(position)!!.thumbnailUrl
        Integer.toString(id)
        Integer.toString(albumId)
        Log.d("Image url", url)
        val inflater = LayoutInflater.from(mContext)

        binding = DataBindingUtil.inflate(inflater, R.layout.album_info, parent, false)



        /*if (convertView == null) {
            convertView = inflater.inflate(resource, parent, false)
            holder = AlbumViewHolder(convertView)
            if (convertView != null) {
                convertView.tag = holder
            }
        } else {
            holder = convertView.tag as AlbumViewHolder
        }*/

        Picasso.get().load(thumbnailUrl).into(binding.albumImage)
        binding.text = title

        return binding.root
    }
}

internal class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var image: ImageView
    var text: TextView

    init {
        image = view.findViewById<View>(R.id.albumImage) as ImageView
        text = view.findViewById<View>(R.id.albumText) as TextView
    }
}