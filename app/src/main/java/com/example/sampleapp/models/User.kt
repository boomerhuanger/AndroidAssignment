package com.example.sampleapp.models

class User(var id: Int, var name: String, var email: String, var phone: String) {

    override fun toString(): String {
        return "User = [id=$id, name=$name, phone$phone, email=$email] "
    }
}