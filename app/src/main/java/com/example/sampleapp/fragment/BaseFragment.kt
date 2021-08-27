package com.example.sampleapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.sampleapp.R

abstract class BaseFragment() : Fragment() {

    //private lateinit var viewModel : ViewModel
    lateinit var baseBinding: ViewDataBinding
    private val userTitle : String = "User Info"
    private val photoTitle = 1;
    private val albumTitle = 2;
    lateinit var viewModel: ViewModel

    @LayoutRes
    abstract fun getLayoutId() : Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("Layout id", getLayoutId().toString())
        baseBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return baseBinding.root
    }
}