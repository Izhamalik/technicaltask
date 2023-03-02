package com.izhar.technical.maximus.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.izhar.technical.maximus.R
import com.izhar.technical.maximus.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    lateinit var bindingSplashFragment: FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val a = Intent(Intent.ACTION_MAIN)
                    a.addCategory(Intent.CATEGORY_HOME)
                    a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(a)
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingSplashFragment = FragmentSplashBinding.inflate(layoutInflater, container, false)
        Handler().postDelayed({
                              findNavController().navigate(R.id.mainFragment)
        },5000)
        return bindingSplashFragment.root
    }
}