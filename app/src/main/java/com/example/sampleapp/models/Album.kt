package com.example.sampleapp.models

import java.io.Serializable

data class Album(
    var albumId: Int,
    var id: Int,
    var title: String,
    var url: String,
    var thumbnailUrl: String
) : Serializable{

    override fun toString(): String {
        return "Album [albumDd=$albumId, id=$id, title=$title, url=$url, thumbnailUrl=$thumbnailUrl]"
    }
}